package com.revature.hydra.curriculum.beans;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SCHEDULED_DATE")
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
	private ZonedDateTime startTime;
	
	@Column(name="END_TIME")
	@NotNull
	private ZonedDateTime endTime;

	public ScheduledDate() {
		super();
	}

	public ScheduledDate(Integer id, Integer week, Integer day, ZonedDateTime startTime, ZonedDateTime endTime) {
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

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(ZonedDateTime endTime) {
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
	
	
	
	
}
