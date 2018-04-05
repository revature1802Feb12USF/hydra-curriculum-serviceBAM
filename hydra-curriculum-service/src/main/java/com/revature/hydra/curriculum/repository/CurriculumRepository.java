package com.revature.hydra.curriculum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hydra.curriculum.bean.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {
	
	public List<Curriculum> findAll();
	public Curriculum findByCurriculumId(Integer id);
	public List<Curriculum> findByCurriculumName(String curriculumName);
	public List<Curriculum> findByCurriculumNameAndIsMaster(String name, Integer isMaster);

	
}
