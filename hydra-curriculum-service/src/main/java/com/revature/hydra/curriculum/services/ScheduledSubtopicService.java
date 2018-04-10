package com.revature.hydra.curriculum.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.ScheduledSubtopic;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.repositories.ScheduledSubtopicRepository;

/**
 * A Service class for retrieving and modifying ScheduledSubtopic data.
 */
@Service
public class ScheduledSubtopicService {

	@Autowired
	ScheduledSubtopicRepository scheduledSubtopicRepoistory;
	
	/**
	 * Retrieve all scheduled subtopics from the database, to be used by the ScheduledSubtopicController
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @return List of all Subtopics in database
	 * 
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
	
	/**
	 * Retrieve ScheduledSubtopics based off of the list of ids given
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @param ids A list of id's of the ScheduledSubtopics to be returned
	 * 
	 * @return A list of ScheduledSubtopics of the given id's
	 * 
	 * @throws NoContentException
	 */
	public List<ScheduledSubtopic> getScheduledSubtopicsById(List<Integer> ids) throws NoContentException{
		List<ScheduledSubtopic> subtopics = scheduledSubtopicRepoistory.findAllByIdIn(ids);
		
		if(subtopics != null && !subtopics.isEmpty()) {
			return subtopics;
		}
		else {
			throw new NoContentException("Unable to find specified schedules");
		}
	}
	
	/**
	 * Add list of ScheduledSubtopics to the database
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @param subtopics A list of ScheduledSubtopics to be added to the database
	 */
	public void add(List<ScheduledSubtopic> subtopics) {
		scheduledSubtopicRepoistory.save(subtopics);
	}
	
	/**
	 * Delete list of ScheduledSubtopics from the database
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @param ids The id's of the ScheduledSubtopics to be deleted
	 */
	public void delete(List<Integer> ids) {
		scheduledSubtopicRepoistory.deleteByIdIn(ids);
	}
	
	/**
	 * Update list of ScheduledSubtopics in the database, same functionality as add
	 * but should only be used for updating
	 * 
	 * @author Seth Maize (Matt 1802)
	 * 
	 * @param subtopics A list of ScheduledSubtopics to be updated
	 */
	public void update(List<ScheduledSubtopic> subtopics) {
		scheduledSubtopicRepoistory.save(subtopics);
	}
}
