package com.revature.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.beans.Curriculum;
import com.revature.beans.Schedule;
import com.revature.beans.remote.Subtopic;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.NoContentException;
import com.revature.services.CurriculumService;
import com.revature.services.RemoteTopicService;

/**
 * This class establishes REST endpoints for retrieval and modification of
 * curriculum data. <br>
 * Handles Zuul Endpoint: /curricula <br>
 * 
 * <pre style="margin:0;border:0;padding:0;font-size:14">
 * "/all"  - GET    - {@link #getAllCurriculums()}
 *
 * "/" - GET    - {@link #getCurriculums(Set)}
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
 *                    
 * "/{cid}/schedules" - GET - {@link #getAllCurriculumSchedules(Integer)}
 * </pre>
 * 
 * 
 * @author Carter Taylor (1712-Steve)
 * @author Olayinka Ewumi (1712-Steve)
 * @author Stephen Negron (1801-Trevin)
 * @author Rafael Sanchez (1801-Trevin)
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
@RestController
@RequestMapping
public class CurriculumController {
    private final CurriculumService curriculumService;

    @Autowired
    public CurriculumController(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    /**
     * Get all schedules belonging to a particular curriculum.
     * <ul>
     * <li>{@link HttpStatus#OK}: At least 1 schedule was found.</li>
     * <li>{@link HttpStatus#NO_CONTENT}: No schedules were found.</li>
     * </ul>
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param cid
     *            The curriculum ID of the schedules.
     * @return A list of schedules which belong to the specified curriculum.
     */
    @GetMapping("/{cid}/schedules")
    public ResponseEntity<List<Schedule>> getAllCurriculumSchedules(
                    @PathVariable Integer cid) {
        List<Schedule> schedules = curriculumService
                        .getAllSchedulesByCurriculumId(cid);

        if (schedules == null) {
            schedules = new ArrayList<>();
        }

        HttpStatus status = schedules.isEmpty() ? HttpStatus.NO_CONTENT
                        : HttpStatus.OK;

        ResponseEntity<List<Schedule>> response = new ResponseEntity<>(
                        schedules, status);

        return response;
    }

    /**
     * Retrieves all curriculums.
     * <ul>
     * <li>HttpStatus.OK: At least 1 curriculum found.</li>
     * <li>HttpStatus.NO_CONTENT: No curriculums found.</li>
     * </ul>
     * 
     * 
     * @author Carter Taylor (1712-Steve)
     * @author Olayinka Ewumi (1712-Steve)
     * @author Stephen Negron (1801-Trevin)
     * @author Rafael Sanchez (1801-Trevin)
     * @author Ricky Baker (1802-Matt)
     * 
     * @return The list of all curriculums.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Curriculum>> getAllCurriculums() {
        List<Curriculum> curriculumList;
        HttpStatus status = HttpStatus.OK;

        try {
            curriculumList = curriculumService.getAllCurriculums();

            if (curriculumList == null) {
                curriculumList = new ArrayList<>();
            }

            if (curriculumList.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            }
        } catch (NoContentException ex) {
            curriculumList = new ArrayList<>();
            status = HttpStatus.NO_CONTENT;
        }

        ResponseEntity<List<Curriculum>> response = new ResponseEntity<>(
                        curriculumList, status);
        
        return response;
    }

    /**
     * Gets all requested curriculums by the given set of ids.
     * <ul>
     * <li>{@link HttpStatus#OK}: All IDs were found.</li>
     * <li>{@link HttpStatus#PARTIAL_CONTENT}: Only some IDs were found.</li>
     * <li>{@link HttpStatus#NO_CONTENT}: No IDs found or no IDs specified.</li>
     * </ul>
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculumIds
     *            A set of curriculum IDs specified in the query string in a
     *            comma-separated list format.
     * @return A response body containing a list of curriculums and one of the
     *         following status codes.
     */
    @GetMapping("/")
    public ResponseEntity<List<Curriculum>> getCurriculums(
                    @RequestParam("ids") Set<Integer> curriculumIds) {
        List<Curriculum> requestedCurriculums = curriculumService
                        .getCurriculums(curriculumIds);
        ResponseEntity<List<Curriculum>> response;
        HttpStatus status;

        if (requestedCurriculums.isEmpty()) {
            status = HttpStatus.NO_CONTENT;
        } else if (requestedCurriculums.size() < curriculumIds.size()) {
            status = HttpStatus.PARTIAL_CONTENT;
        } else {
            status = HttpStatus.OK;
        }

        response = new ResponseEntity<>(requestedCurriculums, status);

        return response;
    }

    /**
     * Retrieves a list of subtopics in the specified curriculum.
     * <ul>
     * <li>{@link HttpStatus#OK}: Found at least 1 subtopic for the specified
     * curriculum.</li>
     * <li>{@link HttpStatus#NO_CONTENT}: No subtopics found for the specified
     * curriculum.</li>
     * </ul>
     * 
     * 
     * @see RemoteTopicService
     * 
     * @author Carter Taylor (1712-Steve)
     * @author Olayinka Ewumi (1712-Steve)
     * @author Stephen Negron (1801-Trevin)
     * @author Rafael Sanchez (1801-Trevin)
     * @author Ricky Baker (1802-Matt)
     * 
     * @param cid
     *            The curriculum ID.
     * @return A list of curriculum subtopics belonging to the given curriculum.
     */
    @HystrixCommand(fallbackMethod = "getAllCurriculumSubtopicsFallback")
    // @GetMapping("/{cid}/subtopics")
    public ResponseEntity<List<Subtopic>> getAllCurriculumSubtopics(
                    @PathVariable Integer cid) {
        ResponseEntity<List<Subtopic>> response;
        HttpStatus status = HttpStatus.OK;
        List<Subtopic> subtopics;

        try {
            subtopics = curriculumService.getAllSubtopicsForCurriculum(cid);

            if (subtopics == null) {
                subtopics = new ArrayList<>();
            }

            if (subtopics.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            }
        } catch (NoContentException ex) {
            subtopics = new ArrayList<>();
            status = HttpStatus.NO_CONTENT;
        }

        response = new ResponseEntity<>(subtopics, status);

        return response;
    }

    /**
     * Hystrix fallback method for {@link #getAllCurriculumSubtopics(Integer)}.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param cid
     *            The curriculum ID.
     * @return A response entity containing
     *         {@link HttpStatus#SERVICE_UNAVAILABLE}.
     */
    public ResponseEntity<List<Subtopic>> getAllCurriculumSubtopicsFallback(
                    Integer cid) {
        ResponseEntity<List<Subtopic>> response = new ResponseEntity<>(
                        HttpStatus.SERVICE_UNAVAILABLE);

        return response;
    }

    /**
     * Retrieves a list of subtopic IDs in the specified curriculum.
     * <ul>
     * <li>{@link HttpStatus#OK}: Curriculum has at least 1 subtopics.</li>
     * <li>{@link HttpStatus#NO_CONTENT}: Curriculum has no subtopics.</li>
     * </ul>
     * 
     * 
     * @see #getAllCurriculumSubtopics(Integer)
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param cid
     *            The curriculum ID.
     * @return The list of subtopics the specified curriculum covers.
     */
    @GetMapping("/{cid}/subtopics")
    public ResponseEntity<List<Integer>> getAllCurriculumSubtopicIds(
                    @PathVariable Integer cid) {
        ResponseEntity<List<Integer>> response;
        List<Integer> subtopicIds;
        HttpStatus status = HttpStatus.OK;

        try {
            subtopicIds = curriculumService.getAllSubtopicIdsForCurriculum(cid);

            if (subtopicIds == null) {
                subtopicIds = new ArrayList<>();
            }

            if (subtopicIds.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            }
        } catch (NoContentException ex) {
            subtopicIds = new ArrayList<>();
            status = HttpStatus.NO_CONTENT;
        }

        response = new ResponseEntity<>(subtopicIds, status);

        return response;
    }
    

    /**
     * Marks the curriculum with the given ID cId as the master version.
     * <ul>
     * <li>{@link HttpStatus#OK}: Curriculum successfully marked as master
     * version.</li>
     * <li>{@link HttpStatus#BAD_REQUEST}: Could not find a curriculum with the
     * provided ID.</li>
     * </ul>
     * 
     * 
     * @author Jordan DeLong
     * @author Carter Taylor (1712-Steve)
     * @author Stephen Negron (1801-Trevin)
     * @author Rafael Sanchez (1801-Trevin)
     * @author Ricky Baker (1802-Matt)
     * 
     * @param cId
     *            The ID of the curriculum to mark as the master version.
     * @return The updated master curriculum data.
     */
    @PatchMapping("/{cid}/master")
    public ResponseEntity<Curriculum> markCurriculumAsMaster(
                    @PathVariable Integer cid) {
        ResponseEntity<Curriculum> response;
        HttpStatus status = HttpStatus.OK;
        Curriculum master;

        try {
            master = curriculumService.markCurriculumAsMaster(cid);
        } catch (BadRequestException ex) {
            master = null;
            status = HttpStatus.BAD_REQUEST;
        }

        response = new ResponseEntity<>(master, status);

        return response;
    }

    /**
     * Adds a curriculum.
     * <ul>
     * <li>{@link HttpStatus#OK}: Successfully added.</li>
     * <li>{@link HttpStatus#INTERNAL_SERVER_ERROR}: Failed to add the
     * curriculum.</li>
     * </ul>
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param newCurriculum
     *            The curriculum to add.
     * @return The added curriculum if the add was successful; otherwise,
     *         {@literal null}.
     */
    @PostMapping("/")
    public ResponseEntity<Curriculum> addCurriculum(
                    @RequestBody Curriculum newCurriculum) {
        ResponseEntity<Curriculum> response;
        HttpStatus status = HttpStatus.OK;

        newCurriculum = curriculumService.addCurriculum(newCurriculum);

        if (newCurriculum == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response = new ResponseEntity<>(newCurriculum, status);

        return response;
    }

    /**
     * Deletes the specified curriculum subtopics. If subtopics are specified
     * which do not belong to the given curriculum, they are not deleted.
     * <ul>
     * <li>{@link HttpStatus#OK}: Always returned.</li>
     * </ul>
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculumId
     *            The ID of the curriculum the selected subtopics belong to.
     * @param subtopicIds
     *            The IDs of the subtopics to delete.
     * @return Always {@literal null}.
     */
    @DeleteMapping("/{cid}/subtopics")
    public ResponseEntity<Void> deleteSubtopics(
                    @PathVariable("cid") Integer curriculumId,
                    @RequestParam("ids") Set<Integer> subtopicIds) {
        curriculumService.deleteSubtopics(curriculumId, subtopicIds);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Deletes curriculums.
     * <ul>
     * <li>{@link HttpStatus#OK}: Always returned.</li>
     * </ul>
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculumIds
     *            IDs of the curriculums to delete.
     * @return Always {@literal null}.
     */
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteCurriculums(
                    @RequestParam("ids") Set<Integer> curriculumIds) {
        curriculumService.deleteCurriculums(curriculumIds);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Updates an existing curriculum with non-null fields of the given
     * curriculum data.
     * <ul>
     * <li>{@link HttpStatus#OK}: Update successful.</li>
     * <li>{@link HttpStatus#BAD_STATUS}: Curriculum ID doesn't exist.</li>
     * </ul>
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculum
     *            The curriculum data to update with. Fields set to null are
     *            ignored.
     * @return The updated curriculum.
     */
    @PatchMapping("/")
    public ResponseEntity<Curriculum> updateCurriculum(
                    @RequestBody Curriculum curriculum) {
        Curriculum updatedCurriculum;
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<Curriculum> response;
        try {
            updatedCurriculum = curriculumService.updateCurriculum(curriculum);
        } catch (NoContentException ex) {
            updatedCurriculum = null;
            status = HttpStatus.BAD_REQUEST;
        }

        response = new ResponseEntity<>(updatedCurriculum, status);

        return response;
    }

    /**
     * Updates an existing curriculum with all fields of the given curriculum
     * data.
     * <ul>
     * <li>{@link HttpStatus#OK}: Replacement successful.</li>
     * <li>{@link HttpStatus#BAD_STATUS}: Curriculum ID doesn't exist.</li>
     * </ul>
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculum
     *            The curriculum data to update with. All fields are replaced
     *            with the provided data.
     * @return The updated curriculum.
     */
    @PutMapping("/")
    public ResponseEntity<Curriculum> replaceCurriculum(
                    @RequestBody Curriculum curriculum) {
        Curriculum updatedCurriculum;
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<Curriculum> response;
        try {
            updatedCurriculum = curriculumService.replaceCurriculum(curriculum);
        } catch (NoContentException ex) {
            updatedCurriculum = null;
            status = HttpStatus.BAD_REQUEST;
        }

        response = new ResponseEntity<>(updatedCurriculum, status);

        return response;
    }

    /**
     * Link subtopics to a curriculum they belong to.
     * <ul>
     * <li>{@link HttpStatus#OK}: Successfully linked subtopics to
     * curriculum.</li>
     * <li>{@link HttpStatus#BAD_REQUEST}: Invalid subtopic IDs detected.</li>
     * </ul>
     * 
     * @see #insertSubtopicsToCurriculumFallback(Integer, Set)
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param cid
     *            The curriculum to link the subtopics to.
     * @param subtopicIds
     *            The IDs of the subtopics to link to a curriculum.
     * @return Always {@literal null}.
     */
    @HystrixCommand(fallbackMethod = "insertSubtopicsToCurriculumFallback")
    @PutMapping("/{cid}")
    public ResponseEntity<Void> insertSubtopicsToCurriculum(
                    @PathVariable Integer cid,
                    @RequestParam("subIds") Set<Integer> subtopicIds) {
        HttpStatus status = HttpStatus.OK;

        try {
            curriculumService.insertSubtopicsToCurriculum(cid, subtopicIds);
        } catch (BadRequestException ex) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Void>(status);
    }

    /**
     * Hystrix fallback method for
     * {@link #insertSubtopicsToCurriculum(Integer, Set)}.
     * 
     * @see #insertSubtopicsToCurriculum(Integer, Set)
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param cid
     *            The curriculum ID.
     * @return A response entity containing
     *         {@link HttpStatus#SERVICE_UNAVAILABLE}.
     */
    public ResponseEntity<Void> insertSubtopicsToCurriculumFallback(Integer cid,
                    Set<Integer> subtopicIds) {
        return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
