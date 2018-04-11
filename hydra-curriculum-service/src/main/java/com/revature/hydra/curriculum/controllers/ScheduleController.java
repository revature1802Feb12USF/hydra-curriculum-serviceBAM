package com.revature.hydra.curriculum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hydra.curriculum.beans.Schedule;
import com.revature.hydra.curriculum.exceptions.BadRequestException;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.services.ScheduleService;

/**
 * This class establishes REST endpoints for retrieval and modification of Schedule data.
 */
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	/**
	 * Returns all schedules in a JSON array 
	 * 
	 * @author Seth Maize (Matt 1802)
	 * @author Ricky Baker (Matt 1802)
	 * 
	 * @return all schedules in a JSON array
	 * @throws NoContentException
	 */
	
	@GetMapping
	public List<Schedule> getAllSchedules() throws NoContentException {
		return scheduleService.getAll();
	}
	
	/**
	 * Retrieves schedule of the given id as a JSON
	 * 
	 * @author Seth Maize (Matt 1802)
	 * @author Ricky Baker (Matt 1802)
	 * 
	 * @param id The id of the schedule to be retrieved
	 * @return schedule of the given id
	 * @throws NoContentException
	 */
	@GetMapping("/{id}")
	public Schedule getScheduleById(@PathVariable Integer id) throws NoContentException {
		return scheduleService.getById(id);
	}
	
	/**
	 * Retrieves a schedule specified by the given id, with an ordered list of subtopics based on start time as a JSON
	 * 
	 * @author Seth Maize (Matt 1802)
	 * @author Ricky Baker (Matt 1802)
	 * 
	 * @param id The id of the schedule to be retrieved
	 * @return a schedule specified by the given id, with an ordered list of subtopics based on start time
	 * @throws NoContentException
	 */
	@GetMapping("/ordered/{id}")
	public Schedule getOrderedSchedule(@PathVariable Integer id) throws NoContentException {
		return scheduleService.getByIdOrdered(id);
	}
	
    /**
     * Adds schedule to database
     * 
     * @author Seth Maize (Matt 1802)
	 * @author Ricky Baker (Matt 1802)
     * 
     * @param schedule Schedule to add to the database
     * 
     * @throws NoContentException
     * @throws BadRequestException
     */
	@PostMapping
	public void addSchedule(@RequestBody Schedule schedule) throws NoContentException, BadRequestException {
		scheduleService.add(schedule);
	}
	
	/**
	 * Delete schedule, specified by id, from the database
	 * 
	 * @author Seth Maize (Matt 1802)
	 * @author Ricky Baker (Matt 1802)
	 * 
	 * @param id The id of the schedule to delete
	 */
	@DeleteMapping("/{id}")
	public void deleteScheduleById(@PathVariable int id) {
		scheduleService.deleteById(id);
	}

}
