package com.revature.hydra.curriculum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.ScheduledSubtopic;
import com.revature.hydra.curriculum.repositories.ScheduledSubtopicRepository;

@Service
public class ScheduledSubtopicService {

	@Autowired
	ScheduledSubtopicRepository scheduledSubtopicRepo;
	
	/**
	 * Retrieve all scheduled subtopics from the database
	 * 
	 * @return List of all Subtopics in database
	 */
	public ScheduledSubtopic getAll() {
		return (ScheduledSubtopic) scheduledSubtopicRepo.findAll();
	}
	
	/**
	 * Retrieve scheduled subtopic from database by the given id
	 * 
	 * @param id The id of the scheduled subtopic to retrieve
	 * 
	 * @return scheduled subtopic from database by the given id
	 */
	public ScheduledSubtopic getById(int id) {
		return scheduledSubtopicRepo.findScheduledSubtopicById(id);
	}
	
}
