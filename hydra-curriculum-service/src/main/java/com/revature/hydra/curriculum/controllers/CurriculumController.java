package com.revature.hydra.curriculum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.hydra.curriculum.beans.Curriculum;
import com.revature.hydra.curriculum.beans.remote.Subtopic;
import com.revature.hydra.curriculum.exceptions.BadRequestException;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.exceptions.ServiceUnavailableException;
import com.revature.hydra.curriculum.services.CurriculumService;

/**
 * This class establishes REST endpoints for retrieval and modification of curriculum data.
 */
@RestController
@RequestMapping("/curriculums")
public class CurriculumController {
	@Autowired
	CurriculumService curriculumService;
	
	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Olayinka Ewumi (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Retrieves all curriculums.
	 * 	HttpStatus.OK: At least 1 curriculum found.
	 *  HttpStatus.NO_CONTENT: No curriculums found.
	 *  
	 * @return The list of all curriculums.
	 * @throws NoContentException Thrown when given list is empty or null. (HttpStatus.NO_CONTENT)
	 */
	@GetMapping
	public List<Curriculum> getAllCurriculums() throws NoContentException {
		return curriculumService.getAllCurriculums();
	}
	
	@GetMapping("/{ids}")
	public List<Curriculum> getCurriculums(@RequestParam("ids") List<Integer> curriculumIds) {
		return curriculumService.getCurriculums(curriculumIds);
	}
	
	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Olayinka Ewumi (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin) 
	 * 
	 * Retrieves the curriculum specified by the ID cId.
	 * 	HttpStatus.OK: Curriculum found.
	 *  HttpStatus.NO_CONTENT: No curriculum with the ID cId found. 
	 *  HttpStatus.BAD_REQUEST: Parameters missing.
	 *  
	 * @param cId The curriculum ID to search for.
	 * 
	 * @return The curriculum specified by the given ID.
	 * @throws BadRequestException There are missing parameters.
	 * @throws NoContentException No curriculum found for the given ID.
	 */
	@GetMapping("/{id}")
	public Curriculum getCurriculumById(@PathVariable int id) throws NoContentException {
		return curriculumService.getCurriculumById(id);
	}

	
	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Olayinka Ewumi (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Retrieves a list of subtopics in the specified curriculum.
	 * 	HttpStatus.OK: Found at least 1 subtopic for the specified curriculum.
	 *  HttpStatus.NO_CONTENT: No subtopics found for the specified curriculum.
	 *  
	 * @param id The curriculum ID.
	 * @return A list of curriculum subtopics belonging to the given curriculum.
	 */
	@HystrixCommand(fallbackMethod="serviceUnavailable")
	@GetMapping("/{cid}/subtopics")
	public List<Subtopic> getAllCurriculumSubtopics(@PathVariable int id) throws NoContentException {
		return curriculumService.getAllSubtopicsForCurriculum(id);
	}
	
	
	/**
	 * Hystrix fallback method for when an endpoint using a remote service can't access the service. 
	 */
	public void serviceUnavailable() throws ServiceUnavailableException {
		throw new ServiceUnavailableException("Service is currently unavailable.");
	}
	
	
//	/**
//	 * @author Jordan DeLong
//	 * @author Carter Taylor (1712-Steve)
//	 * @author Stephen Negron (1801-Trevin)
//	 * @author Rafael Sanchez (1801-Trevin)
//	 * 
//	 * Marks the curriculum with the given ID cId as the master version.
//	 * 	HttpStatus.BAD_REQUEST: Could not find a curriculum with the provided ID.
//	 * 
//	 * @param cId The ID of the curriculum to mark as the master version.
//	 * @return The updated master curriculum data.
//	 * 
//	 * @throws BadRequestException Could not find a curriculum with the provided ID.
//	 * @throws NoContentException Could not find the curriculum with the provided ID.
//	 */
//	@PatchMapping("/{id}/master")
//	public Curriculum markCurriculumAsMaster(@PathVariable int id) throws BadRequestException {
//		return curriculumService.markCurriculumAsMaster(id);
//	}

	@PostMapping
	public Curriculum addCurriculum(@RequestBody Curriculum newCurriculum) throws BadRequestException {
		return curriculumService.addCurriculum(newCurriculum);
	}
	
	
	@ResponseStatus(code=HttpStatus.OK)
	@DeleteMapping("/{cId}/subtopics")
	public void deleteSubtopics(@PathVariable("cId") Integer curriculumId, 
			@RequestParam("ids") List<Integer> subtopicIds) {
		curriculumService.deleteSubtopics(curriculumId, subtopicIds);
	}
	
	@ResponseStatus(code=HttpStatus.OK)
	@DeleteMapping
	public void deleteCurriculums(@RequestParam("ids") List<Integer> curriculumIds) {
		curriculumService.deleteCurriculums(curriculumIds);
	}
	
	@PatchMapping
	public Curriculum updateCurriculum(@RequestBody Curriculum curriculum) throws NoContentException {
		return curriculumService.updateCurriculum(curriculum);
	}
	
	@HystrixCommand(fallbackMethod="serviceUnavailable")
	@ResponseStatus(code=HttpStatus.OK)
	@PutMapping("/{id}")
	public void insertSubtopicsToCurriculum(@PathVariable Integer id,
			@RequestParam("subIds") List<Integer> subtopicIds) throws BadRequestException {
		curriculumService.insertSubtopicsToCurriculum(id, subtopicIds);
	}
}
