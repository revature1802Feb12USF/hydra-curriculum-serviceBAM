package com.revature.hydra.curriculum.beans;

import java.sql.Timestamp;

/**
 * Non-persistent java bean that holds subtopic information
 */
public class Subtopic {

	private int subtopicId;

	private SubtopicName subtopicName;

	private Batch batch;

	private SubtopicStatus status;

	private Timestamp subtopicDate;

	public Subtopic() {
		super();
	}

	/**
	 * Multi-argument constructor for Subtopic bean
	 * 
	 * @param subtopicName Name of the subtopic.
	 * @param batch Batch associated with the subtopic
	 * @param status Status of the subtopic
	 * @param subtopicDate Date of the Subtopic
	 */
	public Subtopic(SubtopicName subtopicName, Batch batch, SubtopicStatus status, Timestamp subtopicDate) {
		super();
		this.subtopicName = subtopicName;
		this.batch = batch;
		this.status = status;
		this.subtopicDate = subtopicDate;
	}

	/**
	 * Multi-argument constructor for Subtopic bean
	 * 
	 * @param subtopicId Id of subtopic
	 * @param subtopicName Name of the subtopic.
	 * @param batch Batch associated with the subtopic
	 * @param status Status of the subtopic
	 * @param subtopicDate Date of the Subtopic
	 */
	public Subtopic(int subtopicId, SubtopicName subtopicName, Batch batch, SubtopicStatus status,
			Timestamp subtopicDate) {
		super();
		this.subtopicId = subtopicId;
		this.subtopicName = subtopicName;
		this.batch = batch;
		this.status = status;
		this.subtopicDate = subtopicDate;
	}

	public int getSubtopicId() {
		return subtopicId;
	}

	public void setSubtopicId(int subtopicId) {
		this.subtopicId = subtopicId;
	}

	public SubtopicName getSubtopicName() {
		return subtopicName;
	}

	public void setSubtopicName(SubtopicName subtopicName) {
		this.subtopicName = subtopicName;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public SubtopicStatus getStatus() {
		return status;
	}

	public void setStatus(SubtopicStatus status) {
		this.status = status;
	}

	public Timestamp getSubtopicDate() {
		return subtopicDate;
	}

	public void setSubtopicDate(Timestamp subtopicDate) {
		this.subtopicDate = subtopicDate;
	}

	@Override
	public String toString() {
		return "Subtopic [(Subtopic ID) \t subtopicId=" + subtopicId + ",\n"
				+ "(Batch) \t batch=" + batch + ",\n"
				+ "(Subtopic Date) \t subtopicDate=" + subtopicDate + ",\n"
				+ "(Status) \t status=" + status +"\n]";
	}

}
