package com.revature.beans;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.revature.util.Getter;
import com.revature.util.ReflectionUtils;
import com.revature.util.Setter;

/**
 * Represents a general curriculum.
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 *  <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
 *  
 * @author Unknown
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 * 
 * @version 2.0
 */
@Entity
@Table(name="CURRICULUM")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Curriculum {

    @Id
    @Column(name="ID")
    @SequenceGenerator(initialValue=0,
                       allocationSize=1,
                       name="CURRICULUM_ID_SEQ_GEN",
                       sequenceName="CURRICULUM_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
                    generator="CURRICULUM_ID_SEQ_GEN")
    private Integer id;
    
    @Column(name="NAME")
    @NotEmpty(message="Curriculum name cannot be empty.")
    @NotNull(message="Curriculum name cannot be null.")
    private String name;
    
    @Column(name="VERSION")
    @NotNull(message="Curriculum version cannot be null.")
    private Integer version;
    
    @Column(name="CREATOR_ID")
    @NotNull(message="Curriculum creator ID cannot be null.")
    private Integer creatorId;
    
    @Column(name="MODIFIER_ID")
    @NotNull(message="Curriculum modifier ID cannot be null.")
    private Integer modifierId;
    
    @Column(name="DATE_CREATED")
    @NotNull(message="Curriculum date created cannot be null.")
    private Date dateCreated;
    
    @Column(name="WEEK_DURATION")
    @NotNull(message="Curriculum duration (weeks) cannot be null.")
    private Integer weekDuration;
    
    @Column(name="MASTER_VERSION")
    @NotNull(message="Curriculum master version flag cannot be null.")
    @Getter("isMasterVersion")
    @Setter("setIsMasterVersion")
    private Boolean masterVersion;
    
    
    public Curriculum() {
    }

    public Curriculum(Integer id, String name, Integer version, Integer creatorId, Integer modifierId,
            Date dateCreated, Integer duration, Boolean masterVersion) {
        super();
        this.id = id;
        this.name = name;
        this.version = version;
        this.creatorId = creatorId;
        this.modifierId = modifierId;
        this.dateCreated = dateCreated;
        this.weekDuration = duration;
        this.masterVersion = masterVersion;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(Integer weekDuration) {
        this.weekDuration = weekDuration;
    }

    @JsonGetter("masterVersion")
    public Boolean isMasterVersion() {
        return masterVersion;
    }
    
    @JsonSetter("masterVersion")
    public void setIsMasterVersion(Boolean masterVersion) {
        this.masterVersion = masterVersion;
    }

    @Override
    public String toString() {
        return "Curriculum [(Curriculum Id) \t id=" + id + ",\n"
                + "(Name) \t name=" + name + ",\n"
                + "(Version) \t version=" + version + ",\n"
                + "(Created by) \t creatorId=" + creatorId + ",\n"
                + "(Last Modified by) \t modifierId=" + modifierId + ",\n "
                + "(Created on) \t dateCreated=" + dateCreated + ",\n"
                + "(Duration in weeks) \t weekDuration=" + weekDuration + ",\n"
                + "(Master Version) \t masterVersion=" + masterVersion + "\n]";
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((creatorId == null) ? 0 : creatorId.hashCode());
        result = PRIME * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        result = PRIME * result + ((masterVersion == null) ? 0 : masterVersion.hashCode());
        result = PRIME * result + ((modifierId == null) ? 0 : modifierId.hashCode());
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        result = PRIME * result + ((version == null) ? 0 : version.hashCode());
        result = PRIME * result + ((weekDuration == null) ? 0 : weekDuration.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ReflectionUtils.testEquality(this, obj);
    }
}
