package com.revature.hydra.curriculum.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 */
@Entity
@Table(name="Curriculum_Subtopic")
public class CurrSubtopic {
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Curriculum_Id", 
				referencedColumnName="Id")
	@NotNull(message="Curriculum ID cannot be null.")
	private Curriculum curriculum;
	
	@Column(name="Subtopic_Id")
	@NotNull(message="Subtopic ID cannot be null.")
	private Integer subtopicId;
	
	public CurrSubtopic() {
		curriculum = null;
		subtopicId = null;
	}
	
	public CurrSubtopic(Curriculum curriculum, Integer subtopicId) {
		super();
		this.curriculum = curriculum;
		this.subtopicId = subtopicId;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculumId) {
		this.curriculum = curriculumId;
	}

	public Integer getSubtopicId() {
		return subtopicId;
	}

	public void setSubtopicId(Integer subtopicId) {
		this.subtopicId = subtopicId;
	}

	@Override
	public String toString() {
		return "Subtopic [(Curriculu Id) \t curriculumId=" + curriculum + ",\n"
				+ "(Subtopic Id) \t subtopicId=" + subtopicId + "\n]";
	}
	
	
}
