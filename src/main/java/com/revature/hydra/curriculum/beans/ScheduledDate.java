package com.revature.hydra.curriculum.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.hydra.util.ReflectionUtils;

/**
 * Contains scheduling information for a scheduled subtopic item.
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 *  <pre style="margin:0;border:0;padding:0;">    13 April 2018</pre>
 * 
 * @see Schedule
 * @see ScheduledSubtopic
 * 
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 * 
 * @version 2.0
 */
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
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((day == null) ? 0 : day.hashCode());
        result = PRIME * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        result = PRIME * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = PRIME * result + ((week == null) ? 0 : week.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ReflectionUtils.testEquality(this, obj);
    }
    
}
