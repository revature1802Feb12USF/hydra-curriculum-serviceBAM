package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.beans.ScheduledDate;

/**
 * Repository class for getting ScheduledDate info.
 * 
 * 
 * @author Unknown
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
public interface ScheduledDateRepository
                extends JpaRepository<ScheduledDate, Integer> {

public interface ScheduledDateRepository extends JpaRepository<ScheduledDate, Integer>{
    public ScheduledDate findSechduledDateById(Integer id);
}
