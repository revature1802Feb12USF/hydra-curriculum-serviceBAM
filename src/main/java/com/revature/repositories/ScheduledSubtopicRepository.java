package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.beans.Schedule;
import com.revature.beans.ScheduledSubtopic;

public interface ScheduledSubtopicRepository extends JpaRepository<ScheduledSubtopic, Integer>{

    public ScheduledSubtopic findScheduledSubtopicById(int id);
    public List<ScheduledSubtopic> findByParentSchedule(Schedule schedule);//ParentScheduleIdOrderByScheduledDateStartTimeAsc(int id);
    public void deleteByIdAndParentScheduleId(int subtopicId, int parentScheduleId);
    public void deleteByIdIn(List<Integer> ids);
    public List<ScheduledSubtopic> findAllByIdIn(List<Integer> ids);
}
