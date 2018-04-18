package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.beans.Schedule;
import com.revature.beans.ScheduledSubtopic;

/**
 * Repository class for getting ScheduledSubtopic info.
 * 
 * @author Unknown
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
public interface ScheduledSubtopicRepository
                extends JpaRepository<ScheduledSubtopic, Integer> {

    public ScheduledSubtopic findScheduledSubtopicById(int id);

    public void deleteByIdAndParentScheduleId(int subtopicId,
                    int parentScheduleId);

    public void deleteByIdIn(List<Integer> ids);

    public List<ScheduledSubtopic> findAllByIdIn(List<Integer> ids);
}
