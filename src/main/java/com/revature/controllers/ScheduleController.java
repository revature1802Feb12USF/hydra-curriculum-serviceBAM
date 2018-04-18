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
 * This class establishes REST endpoints for retrieval and modification of
 * Schedule data.<br>
 * 
 * Handles Zuul Endpoint: /curricula/schedules <br>
 * 
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
 * </pre>
 * 
 * 
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Retrieves all schedules.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * @author Ricky Baker (1802-Matt)
     * 
     * @return All schedules.
     * @throws NoContentException
     */
    @GetMapping
    public List<Schedule> getAllSchedules() throws NoContentException {
        return scheduleService.getAll();
    }

    /**
     * Retrieves the schedule with the given ID.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * @author Ricky Baker (1802-Matt)
     * 
     * @param id
     *            The ID of the schedule to be retrieved.
     * @return Schedule with the given ID.
     * @throws NoContentException
     */
    @GetMapping("/{sid}")
    public Schedule getScheduleById(@PathVariable Integer sid)
                    throws NoContentException {
        return scheduleService.getById(sid);
    }

    /**
     * Retrieves all schedules belonging to the specified curriculum.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * @param cid
     *            The associated curriculum's ID.
     * @return The schedules belonging to the specified curriculum.
     * @throws NoContentException
     */
    @GetMapping("/curriculum/{cid}")
    public List<Schedule> getScheduleByCurriculumId(@PathVariable Integer cid)
                    throws NoContentException {
        return scheduleService.getAllSchedulesByCurriculumId(cid);
    }

    /**
     * Retrieves a schedule specified by the givenID, with an ordered list of
     * subtopics based on start time.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * @author Ricky Baker (1802-Matt)
     * 
     * @param id
     *            TheID of the schedule to be retrieved.
     * @return A schedule specified by the givenID, with an ordered list of
     *         subtopics based on start time
     * @throws NoContentException
     */
    @GetMapping("/ordered/{sid}")
    public Schedule getOrderedSchedule(@PathVariable Integer id)
                    throws NoContentException {
        return scheduleService.getByIdOrdered(id);
    }

    /**
     * Adds a schedule.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * @author Ricky Baker (1802-Matt)
     * 
     * @param schedule
     *            Schedule to add to the database
     * 
     * @throws NoContentException
     * @throws BadRequestException
     */
    @PostMapping
    public Schedule addSchedule(@RequestBody Schedule schedule)
                    throws NoContentException, BadRequestException {
        return scheduleService.add(schedule);
    }

    /**
     * Updates schedule if it exists in the database
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * @author Ricky Baker (1802-Matt)
     * 
     * @param schedule
     *            The schedule to update.
     * 
     * @throws NoContentException
     * @throws BadRequestException
     */
    @PatchMapping
    public Schedule updateSchedule(@RequestBody Schedule schedule) throws NoContentException, BadRequestException {
        return scheduleService.update(schedule);
    }

    /**
     * Delete a schedule by ID.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * @author Ricky Baker (1802-Matt)
     * 
     * @param id
     *            The ID of the schedule to delete
     */
    @DeleteMapping("/{sid}")
    public void deleteScheduleById(@PathVariable int sid) {
        scheduleService.deleteById(sid);
    }
}