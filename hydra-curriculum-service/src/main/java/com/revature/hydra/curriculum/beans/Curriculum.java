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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Curriculum")
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
	@NotEmpty(message="Curriculum name cannot be empty")
	@NotNull
	private String name;
	
	@Column(name="VERSION")
	@NotNull
	private Integer version;
	
	@Column(name="CREATOR_ID")
	@NotNull
	private Integer creatorId;
	
	@Column(name="MODIFIER_ID")
	@NotNull
	private Integer modifierId;
	
	@Column(name="DATE_CREATED")
	@NotNull
	private Timestamp dateCreated;
	
	@Column(name="WEEK_DURATION")
	@NotNull
	private Integer weekDuration;
	
	//should probably be a boolean
	@Column(name="MASTER_VERSION")
	private Boolean masterVersion;
	
	
	/**
	 * Creates a curriculum with default values.
	 */
	public Curriculum() {
	}

	/**
	 * Creates a curriculum with the given parameters.
	 * @param name The name of the curriculum.
	 * @param version The version number of the curriculum.
	 * @param creatorId The user ID of the user who created the curriculum.
	 * @param modifierId The user ID of the user who last modified the curriculum.
	 * @param dateCreated The date the curriculum was created.
	 * @param numberOfWeeks Number of weeks the curriculum lasts.
	 */
	
	
	/**
	 * Get the curriculum's ID.
	 * @return The curriculum's ID.
	 */
	public Integer getId() {
		return id;
	}

	public Curriculum(Integer id, String name, Integer version, Integer creatorId, Integer modifierId,
			Timestamp dateCreated, Integer duration, Boolean masterVersion) {
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

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getWeekDuration() {
		return weekDuration;
	}

	public void setWeekDuration(Integer weekDuration) {
		this.weekDuration = weekDuration;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getMasterVersion() {
		return masterVersion;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creatorId == null) ? 0 : creatorId.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((masterVersion == null) ? 0 : masterVersion.hashCode());
		result = prime * result + ((modifierId == null) ? 0 : modifierId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((weekDuration == null) ? 0 : weekDuration.hashCode());
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
		Curriculum other = (Curriculum) obj;
		if (creatorId == null) {
			if (other.creatorId != null)
				return false;
		} else if (!creatorId.equals(other.creatorId))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (masterVersion == null) {
			if (other.masterVersion != null)
				return false;
		} else if (!masterVersion.equals(other.masterVersion))
			return false;
		if (modifierId == null) {
			if (other.modifierId != null)
				return false;
		} else if (!modifierId.equals(other.modifierId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (weekDuration == null) {
			if (other.weekDuration != null)
				return false;
		} else if (!weekDuration.equals(other.weekDuration))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
