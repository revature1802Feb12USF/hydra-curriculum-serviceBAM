package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.beans.Curriculum;

/**
 * Repository class for getting Curriculum info.
 * 
 * @author Unknown
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
public interface CurriculumRepository
                extends JpaRepository<Curriculum, Integer> {
    public Curriculum findCurriculumById(Integer id);

    public List<Curriculum> findCurriculumByName(String curriculumName);

    public void deleteSubtopicsByIdIn(Iterable<Integer> curriculumIds);

    public List<Curriculum> findCurriculumsByIdIn(
                    Iterable<Integer> curriculumIds);

    public List<Curriculum> findAllCurriculumsByNameAndMasterVersion(
                    String name, Boolean i);
}
