package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Curriculum;
import com.revature.beans.Schedule;
import com.revature.beans.ScheduledSubtopic;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.NoContentException;
import com.revature.repositories.ScheduleRepository;

/**
 * A Service class for retrieving and modifying Schedule data.
 * 
 * @author Seth Maize (Matt 1802)
 * @author Ricky Baker (Matt 1802)
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CurriculumService curriculumService;

    /**
     * Retrieve all schedules.
     * 
     * @author Seth Maize (Matt 1802)
     * 
     * @return A list of all schedules.
     * @throws NoContentException
     *             No schedules exist.
     */
    public List<Schedule> getAll() throws NoContentException {
        List<Schedule> scheduleList = (List<Schedule>) scheduleRepository
                        .findAll();

        if (scheduleList != null && !scheduleList.isEmpty()) {
            return scheduleList;
        } else {
            throw new NoContentException("No schedules found");
        }
    }

    /**
     * Retrieves a schedule by ID.
     * 
     * 
     * @author Seth Maize (Matt 1802)
     * 
     * @param id
     *            The ID of the schedule to retrieve.
     * @return The schedule specified by the ID given.
     * @throws NoContentException
     *             Schedule was not found.
     */
    public Schedule getById(Integer id) throws NoContentException {
        Schedule schedule = scheduleRepository.findById(id);

        if (schedule != null) {
            return schedule;
        } else {
            throw new NoContentException(
                            "Schedule by id: " + id + " was not found");
        }
    }

    /**
     * Get a schedule with an ordered list of scheduled subtopics based on start
     * time in ascending order.
     * 
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param id
     *            The ID of the schedule to retrieve.
     * @return The requested schedule.
     * 
     * @throws NoContentException
     *             Schedule not found.
     */
    public Schedule getByIdOrdered(Integer id) throws NoContentException {
        Schedule schedule = scheduleRepository.findById(id);

        if (schedule != null) {
            schedule.getSubtopics().sort((a, b) -> a.getDate().getStartTime()
                            .compareTo(b.getDate().getStartTime()));
            return schedule;
        } else {
            throw new NoContentException(
                            "Schedule by id: " + id + " was not found");
        }
    }

    /**
     * Registers a new schedule into the system.
     * 
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param schedule
     *            The schedule to add.
     * @return The added schedule.
     * @throws BadRequestException
     *             Non-existent subtopics exist within the schedule.
     * @throws NoContentException
     *             Non-existent curriculum specified.
     */
    @Transactional
    public Schedule add(Schedule schedule)
                    throws NoContentException, BadRequestException {
        schedule.setId(null);

        Curriculum curriculum = curriculumService
                        .getCurriculumById(schedule.getCurriculum().getId());

        // make sure that curriculum is valid
        if (curriculum == null) {
            throw new BadRequestException(
                            "Non-existent curriculum is associated with the schedule.");
        }

        schedule.setCurriculum(curriculum);

        List<ScheduledSubtopic> subtopics = schedule.getSubtopics();
        if (subtopics != null && !subtopics.isEmpty()) {
            schedule.setSubtopics(new ArrayList<ScheduledSubtopic>());
        }

        schedule = scheduleRepository.save(schedule);

        if (subtopics != null && !subtopics.isEmpty()) {
            final Schedule parentSchedule = schedule;
            subtopics.forEach(subtopic -> subtopic
                            .setParentSchedule(parentSchedule));
            schedule.setSubtopics(subtopics);
            schedule = scheduleRepository.save(schedule);
        }

        return schedule;
    }

    /**
     * Update an existing schedule.
     * 
     * 
     * @author Ricky Baker
     * @author Seth Maize
     * 
     * @param schedule
     *            The data to update the schedule with a matching ID with.
     * @throws NoContentException
     *             Schedule does not exist.
     */
    @Transactional
    public Schedule update(Schedule schedule) throws NoContentException {
        Schedule scheduleCopy = getById(schedule.getId());

        // before updating we are making sure that the schedule exists
        if (scheduleCopy == null) {
            throw new NoContentException("This curriculum does not exist.");
        } else {
            scheduleRepository.save(schedule);
            return schedule;
        }
    }

    /**
     * Delete a schedulue.
     * 
     * 
     * @author Seth Maize (Matt 1802)
     *
     * @param id
     *            The ID of the schedule to delete.
     */
    @Transactional
    public void deleteById(Integer id) {
        scheduleRepository.delete(id);
    }

    /**
     * Get all schedules belonging to a curriculum.
     * 
     * 
     * @see CurriculumService#getAllSchedulesByCurriculumId(Integer)
     * 
     * @author Ricky Baker (1802-Matt)
     * @author Seth Maize (1802-Matt)
     * 
     * @param id
     *            The curriculum's ID.
     * @return The schedules belonging to the curriculum.
     */
    public List<Schedule> getAllSchedulesByCurriculumId(Integer id) {
        return scheduleRepository.findAllSchedulesByCurriculumId(id);
    }

}
