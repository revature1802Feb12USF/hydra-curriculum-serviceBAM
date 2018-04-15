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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.hydra.util.ReflectionUtils;

/**
 * A subtopic linked with a scheduled time.
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 *  <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
 * 
 * @see Schedule
 * @see ScheduledDate
 * 
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 * 
 * @version 2.0
 */
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
    
    @JoinColumn(name="DATE")
    @OneToOne(optional=false)
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
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((date == null) ? 0 : date.hashCode());
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        result = PRIME * result + ((parentSchedule == null) ? 0 : parentSchedule.hashCode());
        result = PRIME * result + ((subtopicId == null) ? 0 : subtopicId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ReflectionUtils.testEquality(this, obj);
    }
}
