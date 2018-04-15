package com.revature.hydra.curriculum.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * This class establishes REST endpoints for retrieval and modification of curriculum data. <br>
 * Handles Zuul Endpoint: /curricula <br>
 * 
 * <pre style="margin:0;border:0;padding:0;font-size:14">
 * "/all"  - GET    - {@link #getAllCurriculums()}
 *
 * "/" - GET - {@link #getCurriculums(Set)}
 *     - POST   - {@link #addCurriculum(Curriculum)}
 *     - PUT    - {@link #replaceCurriculum(Curriculum)}
 *     - PATCH  - {@link #updateCurriculum(Curriculum)}
 *     - DELETE - {@link #deleteCurriculums(Set)}
 * 
 * "/{cid}" - PUT - {@link #insertSubtopicsToCurriculum(Integer, Set)}
 * 
 * "/{cid}/master" - PATCH - {@link #markCurriculumAsMaster(Integer)}
 * 
 * "/{cid}/subtopics" - GET    - {@link #getAllCurriculumSubtopics(int)}
 *                    - DELETE - {@link #deleteSubtopics(Integer, Set)}
 * </pre>
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 *  <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
 * 
 * @author Carter Taylor (1712-Steve)
 * @author Olayinka Ewumi (1712-Steve)
 * @author Stephen Negron (1801-Trevin)
 * @author Rafael Sanchez (1801-Trevin)
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 * 
 * @version 2.0
 */
@RestController
@RequestMapping
public class CurriculumController {
    @Autowired
    CurriculumService curriculumService;
    
    /**
     * Hystrix fallback method for when an endpoint using a remote service can't access the service.
     * 
     * @author Ricky Baker (1802-Matt)
     */
    public void serviceUnavailable() throws ServiceUnavailableException {
        throw new ServiceUnavailableException("Service is currently unavailable.");
    }
    
    /**
     * Retrieves all curriculums.
     * <ul>
     *     <li>HttpStatus.OK: At least 1 curriculum found.</li>
     *  <li>HttpStatus.NO_CONTENT: No curriculums found.</li>
     * </ul>
     * @return The list of all curriculums.
<<<<<<< HEAD
     * @throws NoContentException Thrown when given list is empty or null. ({@link HttpStatus#NO_CONTENT})
=======
     * @throws NoContentException Thrown when given list is empty or null. 
     *          ({@link HttpStatus#NO_CONTENT})
>>>>>>> 32ed02ded26393edc86b82b913d10586a4a39a81
     * 
     * @author Carter Taylor (1712-Steve)
     * @author Olayinka Ewumi (1712-Steve)
     * @author Stephen Negron (1801-Trevin)
     * @author Rafael Sanchez (1801-Trevin)
     */

    @GetMapping("/all")
    public List<Curriculum> getAllCurriculums() throws NoContentException {
        return curriculumService.getAllCurriculums();
    }
    
    /**
     * Gets all requested curriculums by the given set of ids.<br>
     *     Request endpoint: {@code <host>/<path>?ids=<id csl>}<br>
     * 
     * 
     * @param curriculumIds A set of curriculum IDs specified in the query string
     *                      in a comma-separated list format.
     * @return A response body containing a list of curriculums and one of the 
     *          following status codes:
     *                 <ul>
     *                 <li>OK: all IDs found</li>
     *                 <li>PARTIAL_CONTENT: only some IDs were found</li>
     *                <li>NO_CONTENT: no IDs found or no IDs specified</li>
     *                </ul>
     *
     * @author Ricky Baker (1802-Matt)
     */
    @GetMapping("/")
    public ResponseEntity<List<Curriculum>> getCurriculums(@RequestParam("ids") Set<Integer> curriculumIds) {
        // curriculums/?ids=<csl>
        List<Curriculum> requestedCurriculums = curriculumService.getCurriculums(curriculumIds);
        ResponseEntity<List<Curriculum>> response;
        HttpStatus status;
        
        if(requestedCurriculums.isEmpty()) {
            status = HttpStatus.NO_CONTENT;
        } else if(requestedCurriculums.size() < curriculumIds.size()) {
            status = HttpStatus.PARTIAL_CONTENT;
        } else {
            status = HttpStatus.OK;
        }
        
        response = new ResponseEntity<>(requestedCurriculums, status);
        
        return response;
    }

    /**
     * Retrieves a list of subtopics in the specified curriculum.<br>
     * <ul>
     *     <li>HttpStatus.OK: Found at least 1 subtopic for the specified curriculum.</li>
     *  <li>HttpStatus.NO_CONTENT: No subtopics found for the specified curriculum.</li>
     * </ul>
     * 
     * <b>LastModified:</b>
     * <pre style="margin:0;border:0;padding:0;font-size:15">    13 April 2018</pre>
     *  
     * @param cid The curriculum ID.
     * @return A list of curriculum subtopics belonging to the given curriculum.
     * 
     * @throws NoContentException Thrown when there is no subtopics found.
     * 
     * @author Carter Taylor (1712-Steve)
     * @author Olayinka Ewumi (1712-Steve)
     * @author Stephen Negron (1801-Trevin)
     * @author Rafael Sanchez (1801-Trevin)
     * @author Ricky Baker (1802-Matt)
     */
//    @HystrixCommand(fallbackMethod="serviceUnavailable")
    @GetMapping("/{cid}/subtopics")
    public List<Subtopic> getAllCurriculumSubtopics(@PathVariable int cid) throws NoContentException {
        return curriculumService.getAllSubtopicsForCurriculum(cid);
    }
    
    /**
     * @author Jordan DeLong
     * @author Carter Taylor (1712-Steve)
     * @author Stephen Negron (1801-Trevin)
     * @author Rafael Sanchez (1801-Trevin)
     * 
     * Marks the curriculum with the given ID cId as the master version.
     *     HttpStatus.BAD_REQUEST: Could not find a curriculum with the provided ID.
     * 
     * @param cId The ID of the curriculum to mark as the master version.
     * @return The updated master curriculum data.
     * 
     * @throws BadRequestException Could not find a curriculum with the provided ID.
     * @throws NoContentException Could not find the curriculum with the provided ID.
     */
    @PatchMapping("/{cid}/master")
    public Curriculum markCurriculumAsMaster(@PathVariable Integer cid) throws BadRequestException {
        return curriculumService.markCurriculumAsMaster(cid);
    }

    
    @PostMapping("/")
    public Curriculum addCurriculum(@RequestBody Curriculum newCurriculum) throws BadRequestException {
        return curriculumService.addCurriculum(newCurriculum);
    }
    
    @ResponseStatus(code=HttpStatus.OK)
    @DeleteMapping("/{cid}/subtopics")
    public void deleteSubtopics(@PathVariable("cid") Integer curriculumId, 
            @RequestParam("ids") Set<Integer> subtopicIds) {
        curriculumService.deleteSubtopics(curriculumId, subtopicIds);
    }
    
    @ResponseStatus(code=HttpStatus.OK)
    @DeleteMapping("/")
    public void deleteCurriculums(@RequestParam("ids") Set<Integer> curriculumIds) {
        curriculumService.deleteCurriculums(curriculumIds);
    }
    

    @PatchMapping("/")
    public Curriculum updateCurriculum(@RequestBody Curriculum curriculum) throws NoContentException {
        return curriculumService.updateCurriculum(curriculum);
    }
    
    @PutMapping("/")
    public Curriculum replaceCurriculum(@RequestBody Curriculum curriculum) throws NoContentException {
        return curriculumService.replaceCurriculum(curriculum);
    }
    
    @HystrixCommand(fallbackMethod="serviceUnavailable")
    @ResponseStatus(code=HttpStatus.OK)
    @PutMapping("/{cid}")
    public void insertSubtopicsToCurriculum(@PathVariable Integer cid,
            @RequestParam("subIds") Set<Integer> subtopicIds) throws BadRequestException {
        curriculumService.insertSubtopicsToCurriculum(cid, subtopicIds);
    }
}
