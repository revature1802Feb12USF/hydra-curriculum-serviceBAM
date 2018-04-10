package com.revature.hydra.curriculum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hydra.curriculum.beans.ScheduledSubtopic;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.services.ScheduledSubtopicService;

@RestController
@RequestMapping("/ScheduledSubtopics")
public class ScheduledSubtopicController {
	
	@Autowired
	ScheduledSubtopicService scheduledSubtopicService;

	@GetMapping("/{ids}")
	public List<ScheduledSubtopic> getSubtopics(@PathVariable List<Integer> ids) throws NoContentException{
		return scheduledSubtopicService.getScheduledSubtopicsById(ids);
	}
	
	@PostMapping
	public void addSubtopics(List<ScheduledSubtopic> subtopics){
		scheduledSubtopicService.add(subtopics);
	}
	
	@PutMapping
	public void updateSubtopics(List<ScheduledSubtopic> subtopics) {
		scheduledSubtopicService.update(subtopics);
	}
	
	@DeleteMapping
	public void deleteSubtopicsById(List<Integer> ids) {
		scheduledSubtopicService.delete(ids);
	}
	
}
