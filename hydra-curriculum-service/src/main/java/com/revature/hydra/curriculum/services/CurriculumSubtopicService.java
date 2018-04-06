package com.revature.hydra.curriculum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.Curriculum;
import com.revature.hydra.curriculum.beans.CurriculumSubtopic;
import com.revature.hydra.curriculum.repositories.CurriculumSubtopicRepository;

/**
 * A Service class for retrieving and modifying curriculum subtopic data.
 */
@Service
public class CurriculumSubtopicService {

	@Autowired
	CurriculumSubtopicRepository curriculumSubtopic;
	
	/**
	 * Finds subtopics of specified curriculum
	 * 
	 * @param c The curriculum who's subtopics are to be found.
	 * 
	 * @return List of curriculum subtopics that contain the curriculum specified.
	 */
	public List<CurriculumSubtopic> getCurriculumSubtopicForCurriculum(Curriculum c){
		return curriculumSubtopic.findByCurriculum(c);
	}
	
	/**
	 * Saves curriculum subtopic to the database.
	 * 
	 * @param cs The curriculum subtopic to be saved to the database.
	 */
	public void saveCurriculumSubtopic(CurriculumSubtopic cs){
		curriculumSubtopic.save(cs);
	}

	/**
	 * Finds all curriculum subtopics of the specified curriculum and day.
	 * 
	 * @param c The specified curriculum. 
	 * @param day The specified day.
	 * 
	 * @return List of curriculum subtopics the correspond to the specified curriculum and day.
	 */
	public List<CurriculumSubtopic> getCurriculumSubtopicsForDay(Curriculum c, int day) {
		List<CurriculumSubtopic> curriculumSubtopics = curriculumSubtopic.findByCurriculumAndCurriculumSubtopicDay(c, day);
		return curriculumSubtopics;
	}
	
}
