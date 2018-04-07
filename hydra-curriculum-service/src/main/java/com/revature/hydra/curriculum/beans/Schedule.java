package com.revature.hydra.curriculum.beans;

import java.util.ArrayList;
import java.util.List;

import com.revature.hydra.curriculum.beans.remote.Subtopic;

/**
 * A DTO used to bulk-transfer the data needed to create a curriculum schedule.
 */
public class Schedule {
	private Curriculum curriculum;
	private List<Subtopic> subtopics;
	
	public Schedule() {
		curriculum = null;
		subtopics = new ArrayList<>();
	}
	
	public Schedule(Curriculum curriculum, List<Subtopic> subtopics) {
		super();
		this.curriculum = curriculum;
		this.subtopics = subtopics;
	}



	public List<Subtopic> getSubtopics() {
		return subtopics;
	}
	
	public void setSubtopics(List<Subtopic> subtopics) {
		this.subtopics = subtopics;
	}
	
	public Curriculum getCurriculum() {
		return curriculum;
	}
	
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	
	
}
