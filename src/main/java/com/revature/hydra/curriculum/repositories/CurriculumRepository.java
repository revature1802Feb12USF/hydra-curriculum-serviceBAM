package com.revature.hydra.curriculum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hydra.curriculum.beans.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {
    public Curriculum findCurriculumById(Integer id);
    public List<Curriculum> findCurriculumByName(String curriculumName);
    public void deleteSubtopicsByIdIn(Iterable<Integer> curriculumIds);
    public List<Curriculum> findCurriculumsByIdIn(Iterable<Integer> curriculumIds);
    public List<Curriculum> findAllCurriculumsByNameAndMasterVersion(
                    String name, Boolean i);
}
