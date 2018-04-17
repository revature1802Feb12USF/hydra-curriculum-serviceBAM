package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Schedule;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.NoContentException;
import com.revature.services.ScheduleService;


/**
 * This class establishes REST endpoints for retrieval and modification of Schedule data.
 * 
 * Endpoint: /api/v2/curricula/schedules
 */
/**
 * This class establishes REST endpoints for retrieval and modification of Schedule data.
 * 
 * Handles Zuul Endpoint: /curricula/schedules <br>
 * <pre style="margin:0;border:0;padding:0;font-size:14">
 * ""  - GET    - {@link #getAllSchedules()}
 *     - POST   - {@link #addSchedule(Schedule)}
 *     - PATCH  - {@link #updateSchedule(Schedule)}
 *     
 * "/{sid}  - DELETE - {@link #deleteScheduleById(int)}
 * 
 * "/{sid}" - GET - {@link #getScheduleById(Integer)}
 * 
 * "/ordered/{sid}" - GET - {@link #getOrderedSchedule(Integer)}
 * 
 * </pre>
 * 
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 * 
 * @version 2.0
 */
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    
    @Autowired
    ScheduleService scheduleService;
    
    /**
     * Returns all schedules in a JSON array 
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @return all schedules in a JSON array
     * @throws NoContentException
     */
    
    @GetMapping
    public List<Schedule> getAllSchedules() throws NoContentException {
        return scheduleService.getAll();
    }
    
    /**
     * Retrieves schedule of the given id as a JSON
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param id The id of the schedule to be retrieved
     * @return schedule of the given id
     * @throws NoContentException
     */
    @GetMapping("/{sid}")
    public Schedule getScheduleById(@PathVariable Integer sid) throws NoContentException {
        return scheduleService.getById(sid);
    }
    
    @GetMapping("/curriculum/{cid}")
    public List<Schedule> getScheduleByCurriculumId(@PathVariable Integer cid) throws NoContentException {
        return scheduleService.getAllSchedulesByCurriculumId(cid);
    }
    
    
    
    /**
     * Retrieves a schedule specified by the given id, with an ordered list of subtopics based on start time as a JSON
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param id The id of the schedule to be retrieved
     * @return a schedule specified by the given id, with an ordered list of subtopics based on start time
     * @throws NoContentException
     */
    @GetMapping("/ordered/{sid}")
    public Schedule getOrderedSchedule(@PathVariable Integer id) throws NoContentException {
        return scheduleService.getByIdOrdered(id);
    }
    
    /**
     * Adds schedule to database
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param schedule Schedule to add to the database
     * 
     * @throws NoContentException
     * @throws BadRequestException
     */
    @PostMapping
    public void addSchedule(@RequestBody Schedule schedule) throws NoContentException, BadRequestException {
    	scheduleService.add(schedule);
    }
    
    /**
     * Updates schedule if it exists in the database
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param schedule The Schedule to update
     *  
     * @throws NoContentException
     * @throws BadRequestException
     */
    @PatchMapping
    public void updateSchedule(@RequestBody Schedule schedule) throws NoContentException, BadRequestException {
        scheduleService.update(schedule);
    }
    
    /**
     * Delete schedule, specified by id, from the database
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param id The id of the schedule to delete
     */
    @DeleteMapping("/{sid}")
    public void deleteScheduleById(@PathVariable int sid) {
        scheduleService.deleteById(sid);
    }

}