package com.revature.hydra.curriculum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hydra.curriculum.beans.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
	public Schedule findById(Integer id);
}
