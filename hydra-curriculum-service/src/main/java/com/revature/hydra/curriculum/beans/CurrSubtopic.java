package com.revature.hydra.curriculum.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 */
@Entity
@Table(name="CURRICULUM_SUBTOPIC")
public class CurrSubtopic {
	
	@Id
	@Column(name="CURRICULUM_SUBTOPIC_ID")
	@SequenceGenerator(name = "CURRICULUM_SUBTOPIC_ID_GEN", sequenceName = "CURRICULUM_SUBTOPIC_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRICULUM_SUBTOPIC_ID_GEN")
	private Integer CurrSubtopicId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CURRICULUM_ID", 
				referencedColumnName="Id")
	@NotNull(message="Curriculum ID cannot be null.")
	private Curriculum curriculums;
	
	@Column(name="SUBTOPIC_ID")
	@NotNull(message="Subtopic ID cannot be null.")
	private Integer subtopicId;

	public CurrSubtopic() {
		
	}
	
	public CurrSubtopic(Integer currSubtopicId, Curriculum curriculums, Integer subtopicId) {
		super();
		CurrSubtopicId = currSubtopicId;
		this.curriculums = curriculums;
		this.subtopicId = subtopicId;
	}



	public Integer getCurrSubtopicId() {
		return CurrSubtopicId;
	}



	public void setCurrSubtopicId(Integer currSubtopicId) {
		CurrSubtopicId = currSubtopicId;
	}



	public Curriculum getCurriculums() {
		return curriculums;
	}



	public void setCurriculums(Curriculum curriculums) {
		this.curriculums = curriculums;
	}



	public Integer getSubtopicId() {
		return subtopicId;
	}



	public void setSubtopicId(Integer subtopicId) {
		this.subtopicId = subtopicId;
	}



	@Override
	public String toString() {
		return "CurrSubtopic [CurrSubtopicId=" + CurrSubtopicId + ", curriculums=" + curriculums + ", subtopicId="
				+ subtopicId + "]";
	}

	
	
}
