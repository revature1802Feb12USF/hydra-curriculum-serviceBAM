package com.revature.hydra.curriculum.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="SCHEDULE")
@JsonIdentityInfo(property="id", generator=ObjectIdGenerators.PropertyGenerator.class)
public class Schedule {
	
	@Id
	@SequenceGenerator(name = "SCHEDULE_ID_GEN", sequenceName = "SCHEDULE_ID_SEQ", allocationSize=1, initialValue=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_ID_GEN")
	private Integer id;
	
	@ManyToOne(optional=false,
			   cascade=CascadeType.ALL)
	@NotNull
	private Curriculum curriculum;
	
	@OneToMany(mappedBy="parentSchedule", 
			   fetch=FetchType.LAZY,
			   orphanRemoval=true,
			   cascade=CascadeType.ALL)
	private List<ScheduledSubtopic> subtopics;
	
	public Schedule() {}
	
	public Schedule(Integer id, Curriculum curriculum) {
		super();
		this.id = id;
		this.curriculum = curriculum;
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
	
	public List<ScheduledSubtopic> getSubtopics() {
		return subtopics;
	}

	public void setSubtopics(List<ScheduledSubtopic> subtopics) {
		this.subtopics = subtopics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curriculum == null) ? 0 : curriculum.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Schedule other = (Schedule) obj;
		if (curriculum == null) {
			if (other.curriculum != null)
				return false;
		} else if (!curriculum.equals(other.curriculum))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
