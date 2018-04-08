package com.revature.hydra.curriculum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.Schedule;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.repositories.ScheduleRepository;

@Service
public class ScheduleService {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
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
}
