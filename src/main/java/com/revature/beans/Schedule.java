package com.revature.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.revature.util.ReflectionUtils;

/**
 * Represents a collection of scheduled subtopic items.
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 *  <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
 * 
 * @see ScheduledSubtopic
 * @see ScheduledDate
 * 
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 * 
 * @version 2.0
 */
@Entity
@Table(name="SCHEDULE")
@JsonIdentityInfo(property="id",
                  generator=ObjectIdGenerators.PropertyGenerator.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Schedule {
    
    @Id
    @SequenceGenerator(name="SCHEDULE_ID_GEN",
                       sequenceName="SCHEDULE_ID_SEQ",
                       allocationSize=1,
                       initialValue=0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
                    generator="SCHEDULE_ID_GEN")
    private Integer id;
    
    @ManyToOne(optional=false)
    @NotNull(message="Schedule's referenced curriculum cannot be null.")
    private Curriculum curriculum;
    
    @OneToMany(mappedBy="parentSchedule",
               fetch=FetchType.LAZY,
               orphanRemoval=true,
               cascade= CascadeType.ALL)
    private List<ScheduledSubtopic> subtopics;
    
    public Schedule() {}
    

    public Schedule(Curriculum curriculum) {
        super();
        this.curriculum = curriculum;
        subtopics = new ArrayList<>();
    }
    
    public Schedule(Curriculum curriculum, List<ScheduledSubtopic> subtopics) {
        super();
        this.curriculum = curriculum;
        this.subtopics = subtopics;
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
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((curriculum == null) ? 0 : curriculum.hashCode());
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ReflectionUtils.testEquality(this, obj);
    }

    
    
}
