package com.revature.hydra.curriculum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hydra.curriculum.bean.Curriculum;
import com.revature.hydra.curriculum.service.CurriculumService;

/**
 * A RestController that is used to manipulate, store, retrieve, and delete curriculums.
 */
@RestController
@RequestMapping(value = "/")
public class InternalCurriculumController {

	@Autowired
	CurriculumService curriculumService;

	/**
	 * Deletes the specified curriculum from the database.
	 * 
	 * @param curriculum The curriculum to delete.
	 */
	@PostMapping("deleteCurriculum")
	public void deleteCurriculum(@RequestBody Curriculum curriculum){
		curriculumService.deleteCurriculum(curriculum);
	}
	
	/**
	 * Deletes curriculum subtopics if they contain the curriculum given.
	 * 
	 * @param curriculum Determines which curriculum subtopics will be deleted.
	 */
	@PostMapping("deleteCurriculumSubtopics")
	public void deleteCurriculumSubtopics(@RequestBody Curriculum curriculum){
		curriculumService.deleteCurriculumSubtopics(curriculum);
	}
	
	/**
	 * Find all curriculums by name
	 * 
	 * @param name The name of the curriculum
	 * 
	 * @return A list of curriculums of the given name.
	 */
	@PostMapping("findAllCurriculumByName/{name}")
	public List<Curriculum> findAllCurriculumByName(@PathVariable String name){
		return curriculumService.findAllCurriculumByName(name);
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
	@PostMapping("findAllCurriculumByNameAndIsMaster/{name}")
	public List<Curriculum> findAllCurriculumByNameAndIsMaster(@PathVariable String name, @RequestBody Integer isMaster){
		return curriculumService.findAllCurriculumByNameAndIsMaster(name, isMaster);
	}
	
	/**
	 * Retrieve all curriculums from the database.
	 * 
	 * @return A list of all curriculums in the database.
	 */
	@PostMapping("getAllCurriculum")
	public List<Curriculum> getAllCurriculum(){
		return curriculumService.getAllCurriculum();
	}
	
	/**
	 * Retrieves curriculum of the specified id.
	 * 
	 * @param id The id of the curriculum to retrieve.
	 * 
	 * @return The curriculum of the given id.
	 */
	@PostMapping("getCuricullumById/{id}")
	public Curriculum getCuricullumById(@PathVariable Integer id){
		return curriculumService.getCuricullumById(id);
	}
	
	/**
	 * Retrieves curriculum of the specified id.
	 * 
	 * @param id The id of the curriculum to retrieve.
	 * 
	 * @return The curriculum of the given id.
	 */
	@PostMapping("getCuricullumByIdKeepPwd/{id}")
	public Curriculum getCuricullumByIdKeepPwd(@PathVariable Integer id){
		return curriculumService.getCuricullumByIdKeepPwd(id);
	}
	
	/**
	 * Saves curriculum to database.
	 * 
	 * @param curriculum The curriculum to save to the database.
	 * 
	 * @return The curriculum saved to the database.
	 */
	@PostMapping("save")
	public Curriculum save(@RequestBody Curriculum curriculum){
		return curriculumService.save(curriculum);
	}

}
