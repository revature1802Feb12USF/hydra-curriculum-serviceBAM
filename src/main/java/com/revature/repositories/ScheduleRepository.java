package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.beans.Schedule;

/**
 * Repository class for getting Schedule info.
 * 
 * @author Unknown
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    public Schedule findById(Integer id);

    public List<Schedule> findAllSchedulesByCurriculumId(Integer id);

    public void deleteSchedulesByCurriculumIdIn(Iterable<Integer> id);
}
