package com.revature.hydra.curriculum.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.ScheduledSubtopic;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.repositories.ScheduledSubtopicRepository;

@Service
public class ScheduledSubtopicService {

	@Autowired
	ScheduledSubtopicRepository scheduledSubtopicRepo;
	
	/**
	 * Retrieve all scheduled subtopics from the database
	 * 
	 * @return List of all Subtopics in database
	 * @throws NoContentException 
	 */
	public List<ScheduledSubtopic> getAll() throws NoContentException {
		List<ScheduledSubtopic> scheduledSubtopicList = scheduledSubtopicRepo.findAll();
		
		if(scheduledSubtopicList != null && !scheduledSubtopicList.isEmpty()) {
			return (List<ScheduledSubtopic>) scheduledSubtopicList;
		}
		else {
			throw new NoContentException("No scheduled subtopics found.");
		}
	}
	
	/**
	 * Retrieve scheduled subtopic from database by the given id
	 * 
	 * @param id The id of the scheduled subtopic to retrieve
	 * 
	 * @return scheduled subtopic from database by the given id
	 * @throws NoContentException 
	 */
	public ScheduledSubtopic getById(int id) throws NoContentException {
		ScheduledSubtopic  scheduledSubtopic = scheduledSubtopicRepo.findScheduledSubtopicById(id);
		
		if(scheduledSubtopic != null) {
			return scheduledSubtopicRepo.findScheduledSubtopicById(id);
		}
		else {
			throw new NoContentException("Scheduled subtopic by id: " + id + " was not found");
		}
	}
	
	
	
	public List<ScheduledSubtopic> getScheduledSubtopicsById(Integer scheduleId) throws NoContentException{
		List<ScheduledSubtopic> scheduledSubtopicList = scheduledSubtopicRepo.findAllByParentScheduleIdOrderByScheduledDateStartTimeAsc(scheduleId);
		
		if(scheduledSubtopicList != null && !scheduledSubtopicList.isEmpty()) {
			return (List<ScheduledSubtopic>) scheduledSubtopicList;
		}
		else {
			throw new NoContentException("No scheduled subtopics found.");
		}
	}
}
