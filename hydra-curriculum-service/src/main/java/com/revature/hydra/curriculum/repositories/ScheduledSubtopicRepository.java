package com.revature.hydra.curriculum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hydra.curriculum.beans.ScheduledSubtopic;

public interface ScheduledSubtopicRepository extends JpaRepository<ScheduledSubtopic, Integer>{

	public List<ScheduledSubtopic> findAll();
	public ScheduledSubtopic findScheduledSubtopicById(int id);
	public List<ScheduledSubtopic> findAllByParentScheduleIdOrderByScheduledDateStartTimeAsc(int id);
	public void deleteByIdAndParentScheduleId(int subtopicId, int parentScheduleId);
	
}
