package com.revature.hydra.curriculum.pojos;

import java.util.Arrays;

/**
 * DTO that holds subtopic id's
 */
public class DaysDTO {

	private Integer[] subtopics;
	
	public DaysDTO(){
		
	}

	public Integer[] getSubtopics() {
		return subtopics;
	}

	public void setSubtopics(Integer[] subtopics) {
		this.subtopics = subtopics;
	}

	@Override
	public String toString() {
		return "DaysDTO [(Sube Topics) /t subtopics=" + Arrays.toString(subtopics) + "\n]";
	}
	
}
