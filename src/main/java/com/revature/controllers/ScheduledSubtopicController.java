package com.revature.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.ScheduledSubtopic;
import com.revature.exceptions.NoContentException;
import com.revature.services.ScheduledSubtopicService;

/**
 * This class establishes REST endpoints for retrieval and modification of
 * ScheduledSubtopic data.
 * 
 * Handles Zuul Endpoint: curricula/scheduled-subtopics <br>
 * 
 * <pre style="margin:0;border:0;padding:0;font-size:14">
 * ""  - GET    - {@link #getSubtopics(List)}
 *     - POST   - {@link #addSubtopics(List)}
 *     - PATCH  - {@link #updateSubtopics(List)}
 *     - DELETE - {@link #deleteSubtopicsById(List)}
 * </pre>
 * 
 * 
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
@RestController
@RequestMapping("/scheduled-subtopics")
public class ScheduledSubtopicController {

    @Autowired
    ScheduledSubtopicService scheduledSubtopicService;

    /**
     * Retrieves a list of specified subtopics.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @param ids
     *            The id's of the ScheduledSubtopics to return
     * @return A JSON array containing the specified Subtopics
     * @throws NoContentException
     */
    @GetMapping
    public List<ScheduledSubtopic> getSubtopics(
                    @RequestParam("ids") List<Integer> ids)
                    throws NoContentException {
        return scheduledSubtopicService.getScheduledSubtopicsById(ids);
    }

    /**
     * Add a scheduled subtopic.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @param subtopics
     *            The subtpic to add to the database
     * @return the schedule id (for Angular to not fail JSON.parse)
     */
    @PostMapping
    public Integer addSubtopics(@RequestParam("schedule") Integer scheduleId,
                    @RequestBody ScheduledSubtopic subtopic)
                    throws NoContentException {
        return scheduledSubtopicService.add(scheduleId, subtopic);
    }

    /**
     * Update scheduled subtopics.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @param subtopics
     *            A list of scheduled subtopics to update.
     */
    @PatchMapping
    public void updateSubtopics(
                    @RequestBody List<ScheduledSubtopic> subtopics) {
        scheduledSubtopicService.update(subtopics);
    }

    /**
     * Delete the specified scheduled subtopics by ID.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @param ids
     *            The IDs of the scheduled subtopics to be deleted.
     */
    @Transactional
    @DeleteMapping
    public void deleteSubtopicsById(@RequestParam("ids") List<Integer> ids) {
        scheduledSubtopicService.delete(ids);
    }

}
