package com.revature.hydra.curriculum.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="SCHEDULED_SUBTOPICS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ScheduledSubtopic {
	@Id
	@SequenceGenerator(initialValue=0,
					   allocationSize=1,
					   name="SCHEDULED_SUBTOPIC_ID_SEQ_GEN",
					   sequenceName="SCHEDULED_SUBTOPIC_ID_SEQ")
	@GeneratedValue(generator="SCHEDULED_SUBTOPIC_ID_SEQ_GEN",
					strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="SUBTOPIC_ID")
	@NotNull
	private Integer subtopicId;
	
	@Column(name="SCHEDULED_DATE")
	@NotNull
	private ScheduledDate date;
	
	@Column(name="PARENT_CURRICULUM")
	@ManyToOne(cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	@NotNull
	private Curriculum parentCurriculum;
	
	public ScheduledSubtopic() {
		super();
	}

	public ScheduledSubtopic(Integer id, Integer subtopicId, ScheduledDate date, Curriculum parentCurriculum) {
		super();
		this.id = id;
		this.subtopicId = subtopicId;
		this.date = date;
		this.parentCurriculum = parentCurriculum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubtopicId() {
		return subtopicId;
	}

	public void setSubtopicId(Integer subtopicId) {
		this.subtopicId = subtopicId;
	}

	public ScheduledDate getDate() {
		return date;
	}

	public void setDate(ScheduledDate date) {
		this.date = date;
	}

	public Curriculum getParentCurriculum() {
		return parentCurriculum;
	}

	public void setParentCurriculum(Curriculum parentCurriculum) {
		this.parentCurriculum = parentCurriculum;
	}

	@Override
	public String toString() {
		return "ScheduledSubtopic [(Scheduled Subtopic Id) \t id=" + id + ",\n"
				+ "(Subtopic Id) \t subtopicId=" + subtopicId + ",\n"
				+ "(Date) \t date=" + date + ",\n"
				+ "(Parent Curriculum) \t parentCurriculum=" + parentCurriculum + "\n]";
	}
	
	
}
