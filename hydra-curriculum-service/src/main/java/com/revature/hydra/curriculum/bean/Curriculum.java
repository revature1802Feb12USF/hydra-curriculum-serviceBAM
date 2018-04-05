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
	
	public Curriculum() {
		
	}

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

	public Integer getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(Integer id) {
		this.curriculumId = id;
	}

	public String getCurriculumName() {
		return curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	public int getCurriculumVersion() {
		return curriculumVersion;
	}

	public void setCurriculumVersion(int curriculumVersion) {
		this.curriculumVersion = curriculumVersion;
	}

	public Integer getCurriculumCreator() {
		return curriculumCreator;
	}

	public void setCurriculumCreator(Integer curriculumCreator) {
		this.curriculumCreator = curriculumCreator;
	}

	public Integer getCurriculumModifier() {
		return curriculumModifier;
	}

	public void setCurriculumModifier(Integer curriculumModifier) {
		this.curriculumModifier = curriculumModifier;
	}

	public String getCurriculumDateCreated() {
		return curriculumDateCreated;
	}

	public void setCurriculumDateCreated(String curriculumDateCreated) {
		this.curriculumDateCreated = curriculumDateCreated;
	}

	public int getCurriculumNumberOfWeeks() {
		return curriculumNumberOfWeeks;
	}

	public void setCurriculumNumberOfWeeks(int curriculumNumberOfWeeks) {
		this.curriculumNumberOfWeeks = curriculumNumberOfWeeks;
	}

	public int getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(int isMaster) {
		this.isMaster = isMaster;
	}

	@Override
	public String toString() {
		return "Curriculum [id=" + curriculumId + ", curriculumName=" + curriculumName + ", curriculumVersion="
				+ curriculumVersion + ", curriculumCreator=" + curriculumCreator + ", curriculumModifier="
				+ curriculumModifier + ", curriculumDateCreated=" + curriculumDateCreated + ", curriculumNumberOfWeeks="
				+ curriculumNumberOfWeeks + ", isMaster=" + isMaster + "]";
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
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
