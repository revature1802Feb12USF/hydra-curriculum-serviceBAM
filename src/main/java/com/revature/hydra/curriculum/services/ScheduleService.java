package com.revature.hydra.curriculum.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hydra.curriculum.beans.Curriculum;
import com.revature.hydra.curriculum.beans.Schedule;
import com.revature.hydra.curriculum.exceptions.BadRequestException;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.repositories.ScheduleRepository;

/**
 * A Service class for retrieving and modifying Schedule data.
 * 
 *@author Seth Maize (Matt 1802)
 *@author Ricky Baker (Matt 1802)
 */
@Service
public class ScheduleService {
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private CurriculumService curriculumService;
    
    
    /**
     * Retrieve all schedules from the database
     * 
     * @author Seth Maize (Matt 1802)
     * 
     * @return A list of all schedules in the database
     * 
     * @throws NoContentException 
     */
    public List<Schedule> getAll() throws NoContentException{
        List<Schedule> scheduleList = (List<Schedule>) scheduleRepository.findAll();
        
        if(scheduleList != null && !scheduleList.isEmpty()) {
            return scheduleList;
        }
        else {
            throw new NoContentException("No schedules found");
        }
    }
    
    /**
     * Retrieves schedule by id from database
     * 
     * @author Seth Maize (Matt 1802)
     *  
     * @param id The id that identifies which schedule to grab
     * 
     * @return Schedule specified by the id given
     * 
     * @throws NoContentException 
     */
    public Schedule getById(Integer id) throws NoContentException {
        Schedule schedule = scheduleRepository.findById(id);
        
        if(schedule != null) {
            return schedule;
        }
        else {
            throw new NoContentException("Schedule by id: " + id + " was not found");
        }
    }
    
    /**
     * Get a schedule with an ordered list of ScheduledSubtopics based on start time in ascending order
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param id
     * 
     * @return
     * 
     * @throws NoContentException
     */
    public Schedule getByIdOrdered(Integer id) throws NoContentException {
        Schedule schedule = scheduleRepository.findById(id);
        
        if(schedule != null) {
            schedule.getSubtopics().sort((a, b) ->
                a.getDate().getStartTime().compareTo(b.getDate().getStartTime())
            );
            return schedule;
        }
        else {
            throw new NoContentException("Schedule by id: " + id + " was not found");
        }
    }
    
    
    /**
     * Registers a new schedule into the system.
     * 
     * @author Seth Maize (Matt 1802)
     * @author Ricky Baker (Matt 1802)
     * 
     * @param schedule Adds schedule to the database
     * 
     * @return The added schedule.
     * 
     * @throws BadRequestException Non-existent subtopics exist within the schedule.
     * 
     * @throws NoContentException Non-existent curriculum specified.
     */
    @Transactional
    public Schedule add(Schedule schedule) throws NoContentException, BadRequestException {
        schedule.setId(null);
        
        Curriculum curriculum = curriculumService.getCurriculumById(schedule.getCurriculum().getId());
        
        //make sure that curriculum is valid
        if(curriculum == null) {
            throw new BadRequestException("Non-existent curriculum is associated with the schedule.");
        }
        else {
            schedule.setCurriculum(curriculum);
        }
        
        return scheduleRepository.save(schedule);
        
    }
    
    /**
     * Update schedule that already exists in the database
     * 
     * @author Ricky Baker
     * @author Seth Maize
     * 
     * @param schedule The schedule to update.
     * 
     * @throws NoContentException
     */
    @Transactional
    public void update(Schedule schedule) throws NoContentException {
        Schedule scheduleCopy = getById(schedule.getId());
        
        //before updating we are making sure that the schedule exists
        if(scheduleCopy == null) {
            throw new NoContentException("This curriculum does not exist.");
        }
        else {
            scheduleRepository.save(schedule);
        }
    }
    
    /**
     * Delete Schedule by id
     * 
     *@author Seth Maize (Matt 1802)
     *
     * @param id The id of the schedule to delete
     */
    @Transactional
    public void deleteById(Integer id) {
        scheduleRepository.delete(id);
    }
    
}
