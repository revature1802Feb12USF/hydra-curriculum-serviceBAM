package com.revature.hydra.curriculum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hydra.curriculum.beans.ScheduledSubtopic;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.services.ScheduledSubtopicService;

/**
 * This class establishes REST endpoints for retrieval and modification of ScheduledSubtopic data.
 * Endpoint: /api/v2/curricula/scheduled-subtopics
 */
@RestController
@RequestMapping("/scheduled-subtopics")
public class ScheduledSubtopicController {
	
	@Autowired
	ScheduledSubtopicService scheduledSubtopicService;

	/**
	 * Returns a JSON array containing the specified Subtopics
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @param ids The id's of the ScheduledSubtopics to return
	 * 
	 * @return A JSON array containing the specified Subtopics
	 * 
	 * @throws NoContentException
	 */
	@GetMapping
	public List<ScheduledSubtopic> getSubtopics(@RequestParam("ids") List<Integer> ids) throws NoContentException{
		return scheduledSubtopicService.getScheduledSubtopicsById(ids);
	}
	
	/**
	 * Add ScheduledSubtopic to the database
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @param subtopics The subtpic to add to the database
	 */
	@PostMapping
	public void addSubtopics(@RequestBody List<ScheduledSubtopic> subtopics){
		scheduledSubtopicService.add(subtopics);
	}
	
	/**
	 * Update subtopics in the database
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @param subtopics A list of ScheduledSubtopics to update
	 */
	@PutMapping
	public void updateSubtopics(@RequestBody List<ScheduledSubtopic> subtopics) {
		scheduledSubtopicService.update(subtopics);
	}
	
	/**
	 * Delete ScheduledSubtopics from the database based on the given id's
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @param ids The id's of the ScheduledSubtopics to be deleted
	 */
	@DeleteMapping
	public void deleteSubtopicsById(@RequestParam("ids") List<Integer> ids) {
		scheduledSubtopicService.delete(ids);
	}
	
}
