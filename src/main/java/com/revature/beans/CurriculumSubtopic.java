package com.revature.beans;

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

import com.revature.util.ReflectionUtils;

/**
 * Pseudo-join table to represent a many-to-many relationship between 
 * Subtopic (remote) and Curriculum.
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 *  <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
 * 
 * @see Curriculum
 * 
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 * 
 * @version 2.0
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

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((curriculum == null) ? 0 : curriculum.hashCode());
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        result = PRIME * result + ((subtopicId == null) ? 0 : subtopicId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ReflectionUtils.testEquality(this, obj);
    }
    
    
}
