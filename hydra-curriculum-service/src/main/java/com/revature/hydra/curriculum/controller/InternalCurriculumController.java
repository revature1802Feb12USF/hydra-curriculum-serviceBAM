package com.revature.hydra.curriculum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hydra.curriculum.bean.Curriculum;
import com.revature.hydra.curriculum.service.CurriculumService;

@RestController
@RequestMapping(value = "/")
public class InternalCurriculumController {

	@Autowired
	CurriculumService curriculumService;

	@PostMapping("deleteCurriculum")
	public void deleteCurriculum(@RequestBody Curriculum curriculum){
		curriculumService.deleteCurriculum(curriculum);
	}
	
	@PostMapping("deleteCurriculumSubtopics")
	public void deleteCurriculumSubtopics(@RequestBody Curriculum curriculum){
		curriculumService.deleteCurriculumSubtopics(curriculum);
	}
	
	@PostMapping("findAllCurriculumByName/{name}")
	public List<Curriculum> findAllCurriculumByName(@PathVariable String name){
		return curriculumService.findAllCurriculumByName(name);
	}
	
	@PostMapping("findAllCurriculumByNameAndIsMaster/{name}")
	public List<Curriculum> findAllCurriculumByNameAndIsMaster(@PathVariable String name, @RequestBody Integer isMaster){
		return curriculumService.findAllCurriculumByNameAndIsMaster(name, isMaster);
	}
	
	@PostMapping("getAllCurriculum")
	public List<Curriculum> getAllCurriculum(){
		return curriculumService.getAllCurriculum();
	}
	
	@PostMapping("getCuricullumById/{id}")
	public Curriculum getCuricullumById(@PathVariable Integer id){
		return curriculumService.getCuricullumById(id);
	}
	
	@PostMapping("getCuricullumByIdKeepPwd/{id}")
	public Curriculum getCuricullumByIdKeepPwd(@PathVariable Integer id){
		return curriculumService.getCuricullumByIdKeepPwd(id);
	}
	
	@PostMapping("save")
	public Curriculum save(@RequestBody Curriculum curriculum){
		return curriculumService.save(curriculum);
	}

}
