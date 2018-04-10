package com.revature.hydra.curriculum.beans;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
	
	@JoinColumn(name="SCHEDULE",nullable=false)
	@ManyToOne(cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId=true)
	private Schedule parentSchedule;
	
	public ScheduledSubtopic() {
		super();
	}

	public ScheduledSubtopic(Integer id, Integer subtopicId, ScheduledDate date, Schedule parentSchedule) {
		super();
		this.id = id;
		this.subtopicId = subtopicId;
		this.date = date;
		this.parentSchedule = parentSchedule;
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

	public Schedule getParentSchedule() {
		return parentSchedule;
	}

	public void setParentSchedule(Schedule parentSchedule) {
		this.parentSchedule = parentSchedule;
	}

	@Override
	public String toString() {
		return "ScheduledSubtopic [(Scheduled Subtopic Id) \t id=" + id + ",\n"
				+ "(Subtopic Id) \t subtopicId=" + subtopicId + ",\n"
				+ "(Date) \t date=" + date + ",\n"
				+ "(Parent Schedule) \t parentSchedule=" + parentSchedule + "\n]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parentSchedule == null) ? 0 : parentSchedule.hashCode());
		result = prime * result + ((subtopicId == null) ? 0 : subtopicId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduledSubtopic other = (ScheduledSubtopic) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentSchedule == null) {
			if (other.parentSchedule != null)
				return false;
		} else if (!parentSchedule.equals(other.parentSchedule))
			return false;
		if (subtopicId == null) {
			if (other.subtopicId != null)
				return false;
		} else if (!subtopicId.equals(other.subtopicId))
			return false;
		return true;
	}
}
