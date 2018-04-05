package com.revature.hydra.curriculum.bean;

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

/**
 * The Curriculum Subtopic class will contain data about the subtopics of the curriculum
 */
@Entity
@Table(name = "Curriculum_Subtopic")
public class CurriculumSubtopic {
	
	@Id
	@Column(name = "Curriculum_Subtopic_Id")
	@SequenceGenerator(name = "Curriculum_Subtopic_ID_SEQ", sequenceName = "Curriculum_Subtopic_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Curriculum_Subtopic_ID_SEQ")
	private Integer curriculumSubtopicId;
	
	@Column(name = "curriculum_Subtopic_Name_Id")
	@NotNull(message="Curriculum Subtopic Name cannot be null")
	private int curriculumSubtopicNameId;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curriculum_Subtopic_Cur_Id", referencedColumnName = "Curriculum_Id")
	private Curriculum curriculum;	//curriculum that the subtopic belongs to

	
	@Column(name = "Curriculum_Week")
	private int curriculumSubtopicWeek;
	
	@Column(name = "Curriculum_Day")
	private int curriculumSubtopicDay;	//TODO discuss modifications on how to make this more clear
	
	/**
	 * Creates a no-args curriculum subtopic 
	 */
	public CurriculumSubtopic() {
		
	}

	/**
	 * Creates a subtopic for a curriculum with a specific subtopic id, subtopic name id, curriculum that the subtopic belongs to, 
	 * the week the subtopic is assigned, and day that the subtopic is being assigned
	 * @param curriculumSubtopicId
	 * @param curriculumSubtopicNameId
	 * @param curriculum
	 * @param curriculumSubtopicWeek
	 * @param curriculumSubtopicDay
	 */
	public CurriculumSubtopic(Integer curriculumSubtopicId, int curriculumSubtopicNameId, Curriculum curriculum,
			int curriculumSubtopicWeek, int curriculumSubtopicDay) {
		super();
		this.curriculumSubtopicId = curriculumSubtopicId;
		this.curriculumSubtopicNameId = curriculumSubtopicNameId;
		this.curriculum = curriculum;
		this.curriculumSubtopicWeek = curriculumSubtopicWeek;
		this.curriculumSubtopicDay = curriculumSubtopicDay;
	}

	/**
	 * @return the curriculumSubtopicId
	 */
	public Integer getCurriculumSubtopicId() {
		return curriculumSubtopicId;
	}

	/**
	 * @param curriculumSubtopicId the curriculumSubtopicId to set
	 */
	public void setCurriculumSubtopicId(Integer curriculumSubtopicId) {
		this.curriculumSubtopicId = curriculumSubtopicId;
	}

	/**
	 * @return the curriculumSubtopicNameId
	 */
	public int getCurriculumSubtopicNameId() {
		return curriculumSubtopicNameId;
	}

	/**
	 * @param curriculumSubtopicNameId the curriculumSubtopicNameId to set
	 */
	public void setCurriculumSubtopicNameId(int curriculumSubtopicNameId) {
		this.curriculumSubtopicNameId = curriculumSubtopicNameId;
	}

	/**
	 * @return the curriculum
	 */
	public Curriculum getCurriculum() {
		return curriculum;
	}

	/**
	 * @param curriculum the curriculum to set
	 */
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	/**
	 * @return the curriculumSubtopicWeek
	 */
	public int getCurriculumSubtopicWeek() {
		return curriculumSubtopicWeek;
	}

	/**
	 * @param curriculumSubtopicWeek the curriculumSubtopicWeek to set
	 */
	public void setCurriculumSubtopicWeek(int curriculumSubtopicWeek) {
		this.curriculumSubtopicWeek = curriculumSubtopicWeek;
	}

	/**
	 * @return the curriculumSubtopicDay
	 */
	public int getCurriculumSubtopicDay() {
		return curriculumSubtopicDay;
	}

	/**
	 * @param curriculumSubtopicDay the curriculumSubtopicDay to set
	 */
	public void setCurriculumSubtopicDay(int curriculumSubtopicDay) {
		this.curriculumSubtopicDay = curriculumSubtopicDay;
	}

	@Override
	public String toString() {
		return "CurriculumSubtopic [\n" + "(Curriculum Subtopic Id) \t curriculumSubtopicId =" + curriculumSubtopicId + ",\n" 
				+ "(Subtopic Name Id) \t curriculumSubtopicNameId =" + curriculumSubtopicNameId + ",\n"
				+ "(Curriculum Subtopic it belongs to) \t curriculum =" + curriculum + ",\n" 
				+ "(Week of Subtopic) \t curriculumSubtopicWeek =" + curriculumSubtopicWeek + ",\n"
				+ "(Day of Subtopic) \t curriculumSubtopicDay =" + curriculumSubtopicDay 
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curriculum == null) ? 0 : curriculum.hashCode());
		result = prime * result + curriculumSubtopicDay;
		result = prime * result + ((curriculumSubtopicId == null) ? 0 : curriculumSubtopicId.hashCode());
		result = prime * result + curriculumSubtopicNameId;
		result = prime * result + curriculumSubtopicWeek;
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
		CurriculumSubtopic other = (CurriculumSubtopic) obj;
		if (curriculum == null) {
			if (other.curriculum != null)
				return false;
		} else if (!curriculum.equals(other.curriculum))
			return false;
		if (curriculumSubtopicDay != other.curriculumSubtopicDay)
			return false;
		if (curriculumSubtopicId == null) {
			if (other.curriculumSubtopicId != null)
				return false;
		} else if (!curriculumSubtopicId.equals(other.curriculumSubtopicId))
			return false;
		if (curriculumSubtopicNameId != other.curriculumSubtopicNameId)
			return false;
		if (curriculumSubtopicWeek != other.curriculumSubtopicWeek)
			return false;
		return true;
	}
	
}
