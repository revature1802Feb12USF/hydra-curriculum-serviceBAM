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
	ScheduledSubtopicRepository scheduledSubtopicRepoistory;
	
	/**
	 * Retrieve all scheduled subtopics from the database
	 * 
	 * @return List of all Subtopics in database
	 * @throws NoContentException 
	 */
	public List<ScheduledSubtopic> getAll() throws NoContentException {
		List<ScheduledSubtopic> scheduledSubtopicList = scheduledSubtopicRepoistory.findAll();
		
		if(scheduledSubtopicList != null && !scheduledSubtopicList.isEmpty()) {
			return (List<ScheduledSubtopic>) scheduledSubtopicList;
		}
		else {
			throw new NoContentException("No scheduled subtopics found.");
		}
	}
	
	
	public List<ScheduledSubtopic> getScheduledSubtopicsById(List<Integer> ids) throws NoContentException{
		List<ScheduledSubtopic> subtopics = scheduledSubtopicRepoistory.findAllByIdIn(ids);
		
		if(subtopics != null && !subtopics.isEmpty()) {
			return subtopics;
		}
		else {
			throw new NoContentException("Unable to find specified schedules");
		}
	}
	
	public void add(List<ScheduledSubtopic> subtopics) {
		scheduledSubtopicRepoistory.save(subtopics);
	}
	
	public void delete(List<Integer> ids) {
		scheduledSubtopicRepoistory.deleteByIdIn(ids);
	}
	
	public void update(List<ScheduledSubtopic> subtopics) {
		scheduledSubtopicRepoistory.save(subtopics);
	}
}
