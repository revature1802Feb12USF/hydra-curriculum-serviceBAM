package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.CurriculumSubtopic;

/**
 * Repository class for getting CurriculumSubtopic info.
 * 
 * @author Unknown
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
public interface CurriculumSubtopicRepository
                extends JpaRepository<CurriculumSubtopic, Integer> {
    public List<CurriculumSubtopic> findAllByCurriculumId(Integer id);

    public void deleteBySubtopicIdAndCurriculumId(Integer subtopicId,
                    Integer curriculumId);

    public void deleteSubtopicsByCurriculumIdAndSubtopicIdIn(
                    Integer curriculumId, Iterable<Integer> subtopicIds);
}
