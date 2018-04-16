package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.beans.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
    public Schedule findById(Integer id);
}
