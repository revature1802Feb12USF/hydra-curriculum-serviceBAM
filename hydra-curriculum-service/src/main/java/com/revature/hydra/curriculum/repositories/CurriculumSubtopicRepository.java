package com.revature.hydra.curriculum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.hydra.curriculum.beans.Curriculum;
import com.revature.hydra.curriculum.beans.CurriculumSubtopic;

@Repository
public interface CurriculumSubtopicRepository extends JpaRepository<CurriculumSubtopic, Integer> {
	public List<CurriculumSubtopic> findAll();
	public List<CurriculumSubtopic> findByCurriculum(Curriculum c);
	public void deleteByCurriculum(Curriculum c);
	public List<CurriculumSubtopic> findByCurriculumAndCurriculumSubtopicDay(Curriculum c, int day);
}
