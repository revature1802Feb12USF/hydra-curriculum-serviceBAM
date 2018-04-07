package com.revature.hydra.curriculum.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.Curriculum;
import com.revature.hydra.curriculum.beans.CurriculumSubtopic;
import com.revature.hydra.curriculum.exceptions.BadRequestException;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.repositories.CurriculumRepository;
import com.revature.hydra.curriculum.repositories.CurriculumSubtopicRepository;

/**
 * A Service class for retrieving and modifying curriculum data.
 */
@Service("curriculumService")
public class CurriculumService {

	@Autowired
	CurriculumRepository curriculumRepository;

	@Autowired
	CurriculumSubtopicRepository curriculumSubtopicRepository;
	
	@Autowired
	CurriculumSubtopicService curriculumSubtopicService;
	
	/**
	 * Creates a curriculum service with default parameters.
	 */
	public CurriculumService() {
		super();
	}

	/**
	 * Creates a curriculum service utilizing the provided repositories.
	 * 
	 * @param curriculumRepository DAO for retrieving and modifying curriculum data.
	 * @param curriculumSubtopicRepository DAO for retrieving and modifying curriculum subtopic data.
	 */
	public CurriculumService(CurriculumRepository curriculumRepository,
			CurriculumSubtopicRepository curriculumSubtopicRepository) {
		super();
		this.curriculumRepository = curriculumRepository;
		this.curriculumSubtopicRepository = curriculumSubtopicRepository;
	}

	/**
	 * Returns all curriculums.
	 * @return A list of all the curriculums in the database.
	 */
	public List<Curriculum> getAllCurriculums() throws NoContentException {
		List<Curriculum> curriculumList = curriculumRepository.findAll();
		
		if (curriculumList != null && !curriculumList.isEmpty()) {
			return curriculumList;
		} else {
			throw new NoContentException("No curriculums found");
		}
	}
	
	
	/**
	 * Find a curriculum by id.
	 * 
	 * @param id The id of the curriculum to find.
	 *            
	 * @return The curriculum with the given id if it exists; otherwise, null is returned.
	 */
	public Curriculum getCurriculumById(Integer id) throws NoContentException {
		Curriculum curriculum = curriculumRepository.findCurriculumById(id);
		
		if(curriculum != null) {
			return curriculum;
		} else {
			throw new NoContentException("Curriculum by id: " + id + " was not found");
		}
	}

	/**
	 * Saves a curriculum to the database
	 *    
	 * @param c The curriculum to save to the database.
	 *    
	 * @return The new state of the curriculum that was saved to the database.
	 */
	public Curriculum save(Curriculum c) {
		return curriculumRepository.save(c);
	}

	/**
	 * Finds all curriculums by name.
	 * 
	 * @param name The curriculum's name.
	 * 
	 * @return List of Curriculum objects of the given name.
	 */
	public List<Curriculum> findAllCurriculumByName(String name) {
		return curriculumRepository.findCurriculumByName(name);
	}

	/**
	 * Find all curriculums by name and whether or not it is the master version.
	 * 
	 * @param name The name of the curriculum to find.
	 * @param isMaster 1 if the curriculum to find is the master version.
	 * 				   0 if the curriculum(s) to find is the non-master version.
	 * 
	 * @return List of curriculums found with the provided constraints.
	 */
	// Modify appropriately. Find Master version of curriculum by name.
	public List<Curriculum> findAllCurriculumByNameAndIsMaster(String name, Integer isMaster) {
		List<Curriculum> master = curriculumRepository.findCurriculumByNameAndIsMasterVersion(name, isMaster);
		return master;
	}

	/**
	 * @author Carter Taylor
	 * @author James Holzer (1712-Steve)
	 * 
	 * Delete a curriculum by version.
	 * 
	 * @param version The curriculum version to delete.
	 */
	@Transactional
	public void deleteCurriculum(Curriculum version) {
		deleteCurriculumSubtopics(version);
		curriculumRepository.delete(version);
	}

	/**
	 * @author Carter Taylor
	 * @author James Holzer (1712-Steve)
	 * 
	 * Delete curriculum subtopics by version.
	 * 
	 * @param version The curriculum version of the subtopic to delete.
	 */
	@Transactional
	public void deleteCurriculumSubtopics(Curriculum version) {
		curriculumSubtopicRepository.deleteByCurriculum(version);
	}
	
	/**
	 * Gets all the schedules for the curriculum specified by the ID.
	 * @param id The ID of the curriculum.
	 * @return A list of subtopics for the specified curriculum.
	 * @throws NoContentException No topics found.
	 * @throws BadRequestException No curriculum found with given ID.
	 */
	public List<CurriculumSubtopic> getAllCurriculumSchedulesForCurriculum(int id) throws NoContentException, BadRequestException {
		Curriculum c = getCurriculumById(id);
		
		if(c == null) {
			throw new BadRequestException("No curriculum found with ID: " + id + ".");
		}
		
		List<CurriculumSubtopic> result = curriculumSubtopicService.getCurriculumSubtopicForCurriculum(c);
		
		if(result != null && !result.isEmpty()) 
			return result; 
		else 
			throw new NoContentException("No schedules by curriculum id: " + id + " were found.");
	}
}