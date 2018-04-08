package com.revature.hydra.curriculum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hydra.curriculum.beans.Schedule;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.services.ScheduleService;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	/**
	 * Returns all schedules in a JSON array 
	 * @return all schedules in a JSON array
	 * @throws NoContentException
	 */
	
	@GetMapping
	public List<Schedule> getAllSchedules() throws NoContentException {
		return scheduleService.getAll();
	}
	
	@GetMapping("/{id}")
	public Schedule getScheduleById(@PathVariable Integer id) throws NoContentException {
		return scheduleService.getById(id);
	}
	
}
