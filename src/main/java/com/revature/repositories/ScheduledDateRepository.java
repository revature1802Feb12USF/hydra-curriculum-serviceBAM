package com.revature.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.beans.ScheduledDate;

public interface ScheduledDateRepository extends JpaRepository<ScheduledDate, Integer>{

    public ScheduledDate findSechduledDateById(Integer id);
}
