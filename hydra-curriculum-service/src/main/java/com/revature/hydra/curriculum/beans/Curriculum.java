package com.revature.hydra.curriculum.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Curriculum")
public class Curriculum {

	@Id
	@Column(name = "Id")
	@SequenceGenerator(name = "ID_SEQ", sequenceName = "ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
	private Integer Id;
	
	@Column(name= "Name")
	@NotEmpty(message = "Curriculum name cannot be empty")
	private String name;
	
	@Column(name ="Version")
	private int version;
	
	@Column(name = "Creator")
	private Integer creatorId;
	
	@Column(name = "Modifier")
	private Integer modifierId;
	
	@Column(name = "Date_Created")
	@NotEmpty(message = "Curriculum Date Created cannot be empty")
	private String dateCreated;
	
	@Column(name = "Number_Of_Weeks")
	private int numberOfWeeks;
	
	//should probably be a boolean
	@Column(name = "Is_Master")
	private int isMasterVersion;
	
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
	 * @param isMasterVersion 1 if the curriculum is the master version. Otherwise, should be 0.
	 */
	public Curriculum(String name, int version, Integer creatorId,
			Integer modifierId, String dateCreated, int numberOfWeeks, int isMasterVersion) {
		super();
		this.name = name;
		this.version = version;
		this.creatorId = creatorId;
		this.modifierId = modifierId;
		this.dateCreated = dateCreated;
		this.numberOfWeeks = numberOfWeeks;
		this.isMasterVersion = isMasterVersion;
	}
	
	/**
	 * Get the curriculum's ID.
	 * @return The curriculum's ID.
	 */
	public Integer getId() {
		return Id;
	}

	/**
	 * Set the curriculum's ID.
	 * @param id The ID number to set this curriculum's ID to.
	 */
	public void setId(Integer id) {
		this.Id = id;
	}

	/**
	 * Get the curriculum's name.
	 * @return The curriculum's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the curriculum's name.
	 * @param name The new name of the curriculum.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the curriculum version number.
	 * @return The version number of the curriculum.
	 */
	public int getVersion() {
		return version;
	}
	
	/**
	 * Set the curriculum version.
	 * @param version Set curriculum's version number.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Get the ID of the curriculum's creator.
	 * @return Get the curriculum creator's ID.
	 */
	public Integer getCreatorId() {
		return creatorId;
	}

	/**
	 * Specify the curriculum creator's ID.
	 * @param creator The curriculum creator's ID.
	 */
	public void setCreatorId(Integer creator) {
		this.creatorId = creator;
	}
	
	/**
	 * Get the ID of the last user who modified the curriculum.
	 * @return The ID of the last user who modified the curriculum.
	 */
	public Integer getModifierId() {
		return modifierId;
	}

	/**
	 * Specify the ID of the last user who modified the curriculum.
	 * @param modifierId The ID of the last user who modified the curriculum.
	 */
	public void setModifierId(Integer modifierId) {
		this.modifierId = modifierId;
	}

	/**
	 * Gets the date the curriculum was created.
	 * @return The date when the curriculum was 
	 */
	public String getDateCreated() {
		return dateCreated;
	}
	
	/**
	 * Set the date the curriculum was created.
	 * @param dateCreated The date the curriculum was created.
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	/**
	 * Get the number of weeks the curriculum lasts.
	 * @return The number of weeks the curriculum lasts.
	 */
	public int getNumberOfWeeks() {
		return numberOfWeeks;
	}
	
	/**
	 * Specify the number of weeks the curriculum lasts.
	 * @param numberOfWeeks The number of weeks the curriculum lasts.
	 */
	public void setNumberOfWeeks(int numberOfWeeks) {
		this.numberOfWeeks = numberOfWeeks;
	}

	/**
	 * Gets whether or not the user is the master version or not.
	 * 	0 -> non-master version
	 *  1 -> master version
	 * @return 1 if this curriculum is the master version. 0 if this curriculum is not the master version.
	 */
	public int getIsMasterVersion() {
		return isMasterVersion;
	}

	/**
	 * Specify whether or not the curriculum is the master version.
	 * @param isMaster 1 if the curriculum is the master version.
	 * 				   0 if the curriculum is not the master version.
	 */
	public void setIsMasterVersion(int isMasterVersion) {
		this.isMasterVersion = isMasterVersion;
	}
	
	
	@Override
	public String toString() {
		return "Curriculum [ (ID) id=" + Id + ", (Curriculum Name) curriculumName=" + name + ", (Curriculum Version) curriculumVersion="
				+ version + ", (Curriculum Creator ID) curriculumCreator=" + creatorId + ", (Curriculum Modifier ID) curriculumModifier="
				+ modifierId + ", (Date Created) curriculumDateCreated=" + dateCreated + ", (Duration in Weeks) curriculumNumberOfWeeks="
				+ numberOfWeeks + ", (Is Master Version) isMaster=" + isMasterVersion + "]";
	}
	
	@Override
	public int hashCode() {
		//make prime, PRIME
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((creatorId == null) ? 0 : creatorId.hashCode());
		result = PRIME * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = PRIME * result + ((modifierId == null) ? 0 : modifierId.hashCode());
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		result = PRIME * result + numberOfWeeks;
		result = PRIME * result + version;
		result = PRIME * result + ((Id == null) ? 0 : Id.hashCode());
		result = PRIME * result + isMasterVersion;
		return result; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		// modify to check using instanceof
		if (getClass() != obj.getClass()) return false;
		
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
		if (numberOfWeeks != other.numberOfWeeks)
			return false;
		if (version != other.version)
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (isMasterVersion != other.isMasterVersion)
			return false;
		return true;
	}
	
}
