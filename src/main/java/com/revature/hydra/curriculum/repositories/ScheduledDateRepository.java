package com.revature.hydra.curriculum.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hydra.curriculum.beans.ScheduledDate;

public interface ScheduledDateRepository extends JpaRepository<ScheduledDate, Integer>{

    public ScheduledDate findSechduledDateById(Integer id);
}
