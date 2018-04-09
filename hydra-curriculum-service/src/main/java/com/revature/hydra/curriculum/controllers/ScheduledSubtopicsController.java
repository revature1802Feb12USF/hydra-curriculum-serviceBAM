package com.revature.hydra.curriculum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hydra.curriculum.beans.ScheduledSubtopic;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.services.ScheduledSubtopicService;

@RestController
@RequestMapping("/scheduledSubtopics")
public class ScheduledSubtopicsController {

	@Autowired
	private ScheduledSubtopicService scheduledSubtopicsService;
	
	@GetMapping
	public List<ScheduledSubtopic> getAllScheduledSubtopics() throws NoContentException {
		return scheduledSubtopicsService.getAll();
	}
	
	@GetMapping("/{id}")
	public ScheduledSubtopic getScheduledSubtopicById(@PathVariable Integer id) throws NoContentException {
		return scheduledSubtopicsService.getById(id);
	}
	

}
