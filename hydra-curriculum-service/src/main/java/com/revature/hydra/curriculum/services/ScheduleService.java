package com.revature.hydra.curriculum.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.Curriculum;
import com.revature.hydra.curriculum.beans.Schedule;
import com.revature.hydra.curriculum.beans.ScheduledSubtopic;
import com.revature.hydra.curriculum.exceptions.BadRequestException;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.repositories.ScheduleRepository;
import com.revature.hydra.curriculum.repositories.ScheduledSubtopicRepository;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private RemoteTopicService remoteTopicService;
	
	@Autowired
	private CurriculumService curriculumService;
	
	@Autowired
	private ScheduledSubtopicService scheduledSubtopicService;
	
	@Autowired
	private ScheduledSubtopicRepository scheduledSubtopicRepository;
	
	/**
	 * Retrieve all schedules from the database
	 * @return A list of all schedules in the database
	 * @throws NoContentException 
	 */
	public List<Schedule> getAll() throws NoContentException{
		List<Schedule> scheduleList = (List<Schedule>) scheduleRepository.findAll();
		
		if(scheduleList != null && !scheduleList.isEmpty()) {
			return scheduleList;
		}
		else {
			throw new NoContentException("No schedules found");
		}
	}
	
	/**
	 * Retrieves schedule by id from database
	 * @param id The id that identifies which schedule to grab
	 * @return Schedule specified by the id given
	 * @throws NoContentException 
	 */
	public Schedule getById(Integer id) throws NoContentException {
		Schedule schedule = scheduleRepository.findById(id);
		
		if(schedule != null) {
			return schedule;
		}
		else {
			throw new NoContentException("Schedule by id: " + id + " was not found");
		}
	}
	
	public Schedule getByIdOrdered(Integer id) throws NoContentException {
		Schedule schedule = scheduleRepository.findById(id);
		
		if(schedule != null) {
			schedule.getSubtopics().sort((a, b) ->
				a.getDate().getStartTime().compareTo(b.getDate().getStartTime())
			);
			return schedule;
		}
		else {
			throw new NoContentException("Schedule by id: " + id + " was not found");
		}
	}
	
	
	/**
	 * Registers a new schedule into the system.
	 * @param schedule Adds schedule to the database
	 * @return The added schedule.
	 * @throws BadRequestException Non-existent subtopics exist within the schedule.
	 * @throws NoContentException Non-existent curriculum specified.
	 */
	@Transactional
	public Schedule add(Schedule schedule) throws NoContentException, BadRequestException {
		schedule.setId(null);
		
		Curriculum curriculum = curriculumService.getCurriculumById(schedule.getCurriculum().getId());
		
		//make sure that curriculum is valid
		if(curriculum == null) {
			throw new BadRequestException("Non-existent curriculum is associated with the schedule.");
		}
		else {
			schedule.setCurriculum(curriculum);
		}
		
		return scheduleRepository.save(schedule);
		
	}
	
	@Transactional
	public void deleteById(Integer id) {
		scheduleRepository.delete(id);
	}
	
	@Transactional
	public void deleteSubtopic(int scheduleId, int subtopicId) throws NoContentException {
		
		ScheduledSubtopic subtopic =  scheduledSubtopicService.getById(subtopicId);
		
		//verify that we did not get a null value, and that the subtopic belongs to the subtopic id
		if(subtopic != null && scheduleId == subtopic.getParentSchedule().getId()) {
			scheduledSubtopicRepository.delete(subtopicId);
		}
		else {
			throw new NoContentException("The subtopic does not belong to the specified schedule, or does not exist at all");
		}
	}
	
	@Transactional
	public void updateSubtopic() {
		
	}
	
}
