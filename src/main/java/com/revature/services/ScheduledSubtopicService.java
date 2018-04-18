package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Schedule;
import com.revature.beans.ScheduledSubtopic;
import com.revature.exceptions.NoContentException;
import com.revature.repositories.ScheduledSubtopicRepository;

/**
 * A Service class for retrieving and modifying ScheduledSubtopic data.
 * 
 * @author Seth Maize (1802-Matt)
 */
@Service
public class ScheduledSubtopicService {

    @Autowired
    private ScheduledSubtopicRepository scheduledSubtopicRepository;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Retrieve all scheduled subtopics from the database.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @return All scheduled subtopics.
     * @throws NoContentException
     *             No scheduled subtopics exist.
     */
    public List<ScheduledSubtopic> getAll() throws NoContentException {

        List<ScheduledSubtopic> scheduledSubtopicList = scheduledSubtopicRepository
                        .findAll();

        if (scheduledSubtopicList != null && !scheduledSubtopicList.isEmpty()) {
            return (List<ScheduledSubtopic>) scheduledSubtopicList;
        } else {
            throw new NoContentException("No scheduled subtopics found.");
        }
    }

    /**
     * Retrieve the specified scheduled subtopics.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @param ids
     *            A list of IDs of the scheduled subtopics to retrieve.
     * 
     * @return The scheduled subtopics with the provided IDs.
     * 
     * @throws NoContentException
     *             None of the requested scheduled subtopics were found.
     */
    public List<ScheduledSubtopic> getScheduledSubtopicsById(List<Integer> ids)
                    throws NoContentException {
        List<ScheduledSubtopic> subtopics = scheduledSubtopicRepository
                        .findAllByIdIn(ids);

        if (subtopics != null && !subtopics.isEmpty()) {
            return subtopics;
        } else {
            throw new NoContentException("Unable to find specified schedules");
        }
    }

    /**
     * Add multiple scheduled subtopics.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @param subtopics
     *            The scheduled subtopics to be added.
     */
    @Transactional
    public Integer add(Integer scheduleId, ScheduledSubtopic subtopic)
                    throws NoContentException {
        Schedule schedule = scheduleService.getById(scheduleId);

        subtopic.setParentSchedule(schedule);

        scheduledSubtopicRepository.save(subtopic);

        return scheduleId;
    }

    /**
     * Deletes scheduled subtopics from the database.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @param ids
     *            The IDs of the scheduled subtopics to be deleted.
     */
    public void delete(List<Integer> ids) {
        scheduledSubtopicRepository.deleteByIdIn(ids);
    }

    /**
     * Update scheduled subtopics.
     * 
     * 
     * @author Seth Maize (1802-Matt)
     * 
     * @param subtopics
     *            The new scheduled subtopic data to use for the update.
     */
    public void update(List<ScheduledSubtopic> subtopics) {
        scheduledSubtopicRepository.save(subtopics);
    }

}
