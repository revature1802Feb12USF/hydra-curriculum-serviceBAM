package com.revature.hydra.curriculum.bean;

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
	@Column(name = "Curriculum_Id")
	@SequenceGenerator(name = "Curriculum_ID_SEQ", sequenceName = "Curriculum_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Curriculum_ID_SEQ")
	private Integer curriculumId;
	
	@Column(name= "Curriculum_name")
	@NotEmpty(message = "Curriculum name cannot be empty")
	private String curriculumName;
	
	@Column(name ="Curriculum_version")
	private int curriculumVersion;
	
	@Column(name = "Curriculum_Creator")
	private Integer curriculumCreator;
	
	@Column(name = "Curriculum_Modifier")
	private Integer curriculumModifier;
	
	@Column(name = "Curriculum_Date_Created")
	@NotEmpty(message = "Curriculum Date Created cannot be empty")
	private String curriculumDateCreated;
	
	@Column(name = "Curriculum_Number_Of_Weeks")
	private int curriculumNumberOfWeeks;
	
	//should probably be a boolean
	@Column(name = "Curriculum_Is_Master")
	private int isMaster;
	
	/**
	 * Creates a curriculum with default values.
	 */
	public Curriculum() {
	}

	/**
	 * Creates a curriculum with the given parameters.
	 * @param id The ID of the curriculum.
	 * @param curriculumName The name of the curriculum.
	 * @param curriculumVersion The version number of the curriculum.
	 * @param curriculumCreator The user ID of the user who created the curriculum.
	 * @param curriculumModifier The user ID of the user who last modified the curriculum.
	 * @param curriculumDateCreated The date the curriculum was created.
	 * @param curriculumNumberOfWeeks Number of weeks the curriculum lasts.
	 * @param isMaster 1 if the curriculum is the master version. Otherwise, should be 0.
	 */
	public Curriculum(Integer id, String curriculumName, int curriculumVersion, Integer curriculumCreator,
			Integer curriculumModifier, String curriculumDateCreated, int curriculumNumberOfWeeks, int isMaster) {
		super();
		this.curriculumId = id;
		this.curriculumName = curriculumName;
		this.curriculumVersion = curriculumVersion;
		this.curriculumCreator = curriculumCreator;
		this.curriculumModifier = curriculumModifier;
		this.curriculumDateCreated = curriculumDateCreated;
		this.curriculumNumberOfWeeks = curriculumNumberOfWeeks;
		this.isMaster = isMaster;
	}
	
	/**
	 * Get the curriculum's ID.
	 * @return The curriculum's ID.
	 */
	public Integer getCurriculumId() {
		return curriculumId;
	}

	/**
	 * Set the curriculum's ID.
	 * @param id The ID number to set this curriculum's ID to.
	 */
	public void setCurriculumId(Integer id) {
		this.curriculumId = id;
	}

	/**
	 * Get the curriculum's name.
	 * @return The curriculum's name.
	 */
	public String getCurriculumName() {
		return curriculumName;
	}

	/**
	 * Set the curriculum's name.
	 * @param curriculumName The new name of the curriculum.
	 */
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	/**
	 * Get the curriculum version number.
	 * @return The version number of the curriculum.
	 */
	public int getCurriculumVersion() {
		return curriculumVersion;
	}
	
	/**
	 * Set the curriculum version.
	 * @param curriculumVersion Set curriculum's version number.
	 */
	public void setCurriculumVersion(int curriculumVersion) {
		this.curriculumVersion = curriculumVersion;
	}

	/**
	 * Get the ID of the curriculum's creator.
	 * @return Get the curriculum creator's ID.
	 */
	public Integer getCurriculumCreator() {
		return curriculumCreator;
	}

	/**
	 * Specify the curriculum creator's ID.
	 * @param curriculumCreator The curriculum creator's ID.
	 */
	public void setCurriculumCreator(Integer curriculumCreator) {
		this.curriculumCreator = curriculumCreator;
	}
	
	/**
	 * Get the ID of the last user who modified the curriculum.
	 * @return The ID of the last user who modified the curriculum.
	 */
	public Integer getCurriculumModifier() {
		return curriculumModifier;
	}

	/**
	 * Specify the ID of the last user who modified the curriculum.
	 * @param curriculumModifier The ID of the last user who modified the curriculum.
	 */
	public void setCurriculumModifier(Integer curriculumModifier) {
		this.curriculumModifier = curriculumModifier;
	}

	/**
	 * Gets the date the curriculum was created.
	 * @return The date when the curriculum was 
	 */
	public String getCurriculumDateCreated() {
		return curriculumDateCreated;
	}
	
	/**
	 * Set the date the curriculum was created.
	 * @param curriculumDateCreated The date the curriculum was created.
	 */
	public void setCurriculumDateCreated(String curriculumDateCreated) {
		this.curriculumDateCreated = curriculumDateCreated;
	}
	
	/**
	 * Get the number of weeks the curriculum lasts.
	 * @return The number of weeks the curriculum lasts.
	 */
	public int getCurriculumNumberOfWeeks() {
		return curriculumNumberOfWeeks;
	}
	
	/**
	 * Specify the number of weeks the curriculum lasts.
	 * @param curriculumNumberOfWeeks The number of weeks the curriculum lasts.
	 */
	public void setCurriculumNumberOfWeeks(int curriculumNumberOfWeeks) {
		this.curriculumNumberOfWeeks = curriculumNumberOfWeeks;
	}

	/**
	 * Gets whether or not the user is the master version or not.
	 * 	0 -> non-master version
	 *  1 -> master version
	 * @return 1 if this curriculum is the master version. 0 if this curriculum is not the master version.
	 */
	public int getIsMaster() {
		return isMaster;
	}

	/**
	 * Specify whether or not the curriculum is the master version.
	 * @param isMaster 1 if the curriculum is the master version.
	 * 				   0 if the curriculum is not the master version.
	 */
	public void setIsMaster(int isMaster) {
		this.isMaster = isMaster;
	}
	
	
	@Override
	public String toString() {
		return "Curriculum [ (ID) id=" + curriculumId + ", (Curriculum Name) curriculumName=" + curriculumName + ", (Curriculum Version) curriculumVersion="
				+ curriculumVersion + ", (Curriculum Creator ID) curriculumCreator=" + curriculumCreator + ", (Curriculum Modifier ID) curriculumModifier="
				+ curriculumModifier + ", (Date Created) curriculumDateCreated=" + curriculumDateCreated + ", (Duration in Weeks) curriculumNumberOfWeeks="
				+ curriculumNumberOfWeeks + ", (Is Master Version) isMaster=" + isMaster + "]";
	}
	
	@Override
	public int hashCode() {
		//make prime, PRIME
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((curriculumCreator == null) ? 0 : curriculumCreator.hashCode());
		result = PRIME * result + ((curriculumDateCreated == null) ? 0 : curriculumDateCreated.hashCode());
		result = PRIME * result + ((curriculumModifier == null) ? 0 : curriculumModifier.hashCode());
		result = PRIME * result + ((curriculumName == null) ? 0 : curriculumName.hashCode());
		result = PRIME * result + curriculumNumberOfWeeks;
		result = PRIME * result + curriculumVersion;
		result = PRIME * result + ((curriculumId == null) ? 0 : curriculumId.hashCode());
		result = PRIME * result + isMaster;
		return result; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		// modify to check using instanceof
		if (getClass() != obj.getClass()) return false;
		
		Curriculum other = (Curriculum) obj;
		if (curriculumCreator == null) {
			if (other.curriculumCreator != null)
				return false;
		} else if (!curriculumCreator.equals(other.curriculumCreator))
			return false;
		if (curriculumDateCreated == null) {
			if (other.curriculumDateCreated != null)
				return false;
		} else if (!curriculumDateCreated.equals(other.curriculumDateCreated))
			return false;
		if (curriculumModifier == null) {
			if (other.curriculumModifier != null)
				return false;
		} else if (!curriculumModifier.equals(other.curriculumModifier))
			return false;
		if (curriculumName == null) {
			if (other.curriculumName != null)
				return false;
		} else if (!curriculumName.equals(other.curriculumName))
			return false;
		if (curriculumNumberOfWeeks != other.curriculumNumberOfWeeks)
			return false;
		if (curriculumVersion != other.curriculumVersion)
			return false;
		if (curriculumId == null) {
			if (other.curriculumId != null)
				return false;
		} else if (!curriculumId.equals(other.curriculumId))
			return false;
		if (isMaster != other.isMaster)
			return false;
		return true;
	}
	
}
