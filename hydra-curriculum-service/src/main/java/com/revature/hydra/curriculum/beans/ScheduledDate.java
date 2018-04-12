package com.revature.hydra.curriculum.beans;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="SCHEDULED_DATE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ScheduledDate {
	@Id
	@SequenceGenerator(initialValue=0,
					   allocationSize=1,
					   name="SCHEDULED_DATE_ID_SEQ_GEN",
					   sequenceName="SCHEDULED_DATE_ID_SEQ")
	@GeneratedValue(generator="SCHEDULED_DATE_ID_SEQ_GEN", 
					strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="WEEK")
	@NotNull
	private Integer week;
	
	@Column(name="DAY")
	@NotNull
	private Integer day;
	
	@Column(name="START_TIME")
	@NotNull
	private Timestamp startTime;
	
	@Column(name="END_TIME")
	@NotNull
	private Timestamp endTime;
	
	public ScheduledDate() {
		super();
	}

	public ScheduledDate(Integer id, Integer week, Integer day, Timestamp startTime, Timestamp endTime) {
		super();
		this.id = id;
		this.week = week;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "ScheduledDate [(Schedule Date Id) \t id=" + id + ",\n"
				+ "(Week) \t week=" + week + ",\n"
				+ "(Day) \t day=" + day + ",\n"
				+ "(Start Time) \t startTime=" + startTime + ",\n"
				+ "(End Time) \t endTime=" + endTime + "\n]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());
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
		ScheduledDate other = (ScheduledDate) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		return true;
	}
	
}
