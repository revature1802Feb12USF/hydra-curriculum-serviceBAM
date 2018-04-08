package com.revature.hydra.curriculum.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SCHEDULE")
public class Schedule {
	
	@Id
	@SequenceGenerator(name = "SCHEDULE_ID_GEN", sequenceName = "SCHEDULE_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_ID_GEN")
	private Integer id;
	
	@Column(name="CURRICULULM_ID")
	@NotNull
	private Curriculum curriculum;
	
	@OneToMany
	@NotNull
	private List<ScheduledSubtopic> scheduledSubtopics;
	
	public Schedule() {
		
	}
	
	public Schedule(Integer id, Curriculum curriculum, List<ScheduledSubtopic> scheduledSubtopics) {
		super();
		this.id = id;
		this.curriculum = curriculum;
		this.scheduledSubtopics = scheduledSubtopics;
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

	public List<ScheduledSubtopic> getScheduledSubtopics() {
		return scheduledSubtopics;
	}

	public void setScheduledSubtopics(List<ScheduledSubtopic> scheduledSubtopics) {
		this.scheduledSubtopics = scheduledSubtopics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curriculum == null) ? 0 : curriculum.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((scheduledSubtopics == null) ? 0 : scheduledSubtopics.hashCode());
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
		if (scheduledSubtopics == null) {
			if (other.scheduledSubtopics != null)
				return false;
		} else if (!scheduledSubtopics.equals(other.scheduledSubtopics))
			return false;
		return true;
	}
	
	
	
}
