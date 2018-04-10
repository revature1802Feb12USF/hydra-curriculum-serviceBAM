package com.revature.hydra.curriculum.services;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.Curriculum;
import com.revature.hydra.curriculum.beans.CurriculumSubtopic;
import com.revature.hydra.curriculum.beans.remote.Subtopic;
import com.revature.hydra.curriculum.exceptions.BadRequestException;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.exceptions.UnknownException;
import com.revature.hydra.curriculum.repositories.CurriculumRepository;
import com.revature.hydra.curriculum.repositories.CurriculumSubtopicRepository;

/**
 * A Service class for retrieving and modifying curriculum data.
 */
@Service("curriculumService")
public class CurriculumService {

	@Autowired
	private CurriculumRepository curriculumRepository;

	@Autowired
	private CurriculumSubtopicRepository curriculumSubtopicRepository;
	
	@Autowired
	private RemoteTopicService remoteTopicService;
	
	
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
	public List<Curriculum> findAllCurriculumsByNameAndIsMaster(String name, Integer isMaster) {
		List<Curriculum> master = curriculumRepository.findCurriculumByNameAndIsMasterVersion(name, isMaster);
		
		return master;
	}
	
	/**
	 * Acquire all subtopics from the topic service that belong to the given curriculum.
	 * @param curriculumId The ID of the curriculum.
	 * @return A list of all subtopics that belong to the service.
	 * @throws NoContentException 
	 */
	public List<Subtopic> getAllSubtopicsForCurriculum(int curriculumId) throws NoContentException {
		List<CurriculumSubtopic> curriculumSubtopics = curriculumSubtopicRepository.findAllByCurriculumId(curriculumId);
		
		if(curriculumSubtopics == null || curriculumSubtopics.isEmpty()) {
			throw new NoContentException("No subtopics found.");
		}
		
		List<Integer> subtopicIds = new ArrayList<>();
		
		curriculumSubtopics.forEach(currSubtopic -> {
			subtopicIds.add(currSubtopic.getSubtopicId());
		});
		
		List<Subtopic> subtopics = remoteTopicService.requestSubtopics(subtopicIds);
		
		if(subtopics == null || subtopics.isEmpty()) {
			throw new NoContentException("No subtopics found.");
		}
		
		return subtopics;
	}
	
	
	@Transactional
	public Curriculum markCurriculumAsMaster(int id) throws BadRequestException {
		Curriculum targetCurriculum = null;
		
		try {
			targetCurriculum = getCurriculumById(id);
		} catch(NoContentException ex) {}
		
		if(targetCurriculum == null) {
			throw new BadRequestException("Curriculum with ID=" + id + " does not exist.");
		}
		
		List<Curriculum> curriculumList = findAllCurriculumsByNameAndIsMaster(targetCurriculum.getName(), 1);
		
		if(curriculumList != null && !curriculumList.isEmpty()) {
			curriculumList.forEach(masterCurriculum -> {
				masterCurriculum.setMasterVersion(false);
				save(masterCurriculum);
			});
		}
		
		targetCurriculum.setMasterVersion(true);
		save(targetCurriculum);
		
		return targetCurriculum;
	}
	
	
	@Transactional
	public Curriculum addCurriculum(Curriculum curriculum) throws BadRequestException {
		curriculum.setId(null);
		return save(curriculum);
	}
	
	@Transactional
	public void deleteSubtopics(Integer curriculumId, List<Integer> subtopicIds) {
		curriculumSubtopicRepository.deleteSubtopicsByCurriculumIdAndSubtopicIdIn(curriculumId, subtopicIds);
	}

	@Transactional
	public void deleteCurriculums(List<Integer> curriculumIds) {
		curriculumRepository.deleteSubtopicsByIdIn(curriculumIds);
	}
	
	
	public List<Curriculum> getCurriculums(List<Integer> curriculumIds) {
		return curriculumRepository.findCurriculumsByIdIn(curriculumIds);
	}

	@Transactional
	public Curriculum updateCurriculum(Curriculum curriculum) throws NoContentException {
		Curriculum existing = getCurriculumById(curriculum.getId());
		
		if(existing == null)
			return null;
		
		Field[] fields = curriculum.getClass().getDeclaredFields();
		
		for(Field f : fields) {
			String fieldName = f.getName();
			fieldName = fieldName.replaceFirst(".", "" + Character.toUpperCase(fieldName.charAt(0)));
			String getMethodName = "get" + fieldName;
			String setMethodName = "set" + fieldName;
			
			
			try {
				Method getMethod = curriculum.getClass().getMethod(getMethodName);
				Method setMethod = curriculum.getClass().getMethod(setMethodName, f.getType());
				
				Object getResult;
				setMethod.invoke(existing, 
					(getResult = getMethod.invoke(curriculum)) == null ? 
						getMethod.invoke(existing) : getResult 
				);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException 
					| IllegalArgumentException | InvocationTargetException e) {
				throw new UnknownException("Unknown error occurred.");
			}
		} // end for
		
		curriculum = curriculumRepository.save(existing);

		return curriculum;
		
	}

	@Transactional
	public void insertSubtopicsToCurriculum(Integer id, List<Integer> subtopicIds) throws BadRequestException {
		Curriculum curriculum;
		try {
			curriculum = getCurriculumById(id);
		} catch(NoContentException ex) {
			throw new BadRequestException("Curriculum (id=" + id + ") does not exist.");
		}
		
		if(!remoteTopicService.allSubtopicsExist(subtopicIds))
			throw new BadRequestException("Non-existent subtopic ids submitted.");
		
		List<CurriculumSubtopic> curriculumSubtopics = new ArrayList<>(subtopicIds.size());
		Curriculum targetCurriculum = curriculum;
		
		subtopicIds.forEach(subtopicId -> 
			curriculumSubtopics.add(new CurriculumSubtopic(null, targetCurriculum, subtopicId))
		);
		
		curriculumSubtopicRepository.save(curriculumSubtopics);
	}
	
}























