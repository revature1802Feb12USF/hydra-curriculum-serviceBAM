package com.revature.hydra.curriculum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hydra.curriculum.beans.ScheduledDate;

public interface ScheduledDateRepository extends JpaRepository<ScheduledDate, Integer>{

	public List<ScheduledDate> findall();
	public ScheduledDate findSechduledDateById(Integer id);
}
