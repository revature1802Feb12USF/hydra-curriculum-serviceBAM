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
 * Pseudo-join table to represent a many-to-many relationship between Subtopic and Curriculum
 */
@Entity
@Table(name="CURRICULUM_SUBTOPIC")
public class CurriculumSubtopic {
	
	@Id
	@Column(name="ID")
	@SequenceGenerator(name = "CURRICULUM_SUBTOPIC_ID_SEQ_GEN", 
					   sequenceName = "CURRICULUM_SUBTOPIC_ID_SEQ",
					   allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "CURRICULUM_SUBTOPIC_ID_SEQ_GEN")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CURRICULUM_ID", 
				referencedColumnName="Id")
	@NotNull(message="Curriculum ID cannot be null.")
	private Curriculum curriculum;
	
	@Column(name="SUBTOPIC_ID")
	@NotNull(message="Subtopic ID cannot be null.")
	private Integer subtopicId;

	public CurriculumSubtopic() {
		
	}
	
	public CurriculumSubtopic(Integer currSubtopicId, Curriculum curriculum, Integer subtopicId) {
		super();
		id = currSubtopicId;
		this.curriculum = curriculum;
		this.subtopicId = subtopicId;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Curriculum getCurriculum() {
		return curriculum;
	}



	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}



	public Integer getSubtopicId() {
		return subtopicId;
	}



	public void setSubtopicId(Integer subtopicId) {
		this.subtopicId = subtopicId;
	}

	@Override
	public String toString() {
		return "CurrSubtopic [id=" + id + ", curriculum=" + curriculum + ", subtopicId=" + subtopicId + "]";
	}
}
