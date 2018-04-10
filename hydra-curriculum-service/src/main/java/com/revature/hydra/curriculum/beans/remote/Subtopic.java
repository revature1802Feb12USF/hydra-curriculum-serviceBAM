package com.revature.hydra.curriculum.beans.remote;

import java.time.LocalDateTime;

/**
 * Non-persistent java bean that holds subtopic information
 */
public class Subtopic {

	private int id;

	private String name;

	private String status;

	private LocalDateTime date;
	
	private Topic parentTopic;
	
	public Subtopic() {
		super();
	}

	/**
	 * Multi-argument constructor for Subtopic bean
	 * 
	 * @param subtopicName Name of the subtopic.
	 * @param status Status of the subtopic
	 * @param subtopicDate Date of the Subtopic
	 */
	public Subtopic(String subtopicName, String status, LocalDateTime subtopicDate) {
		super();
		this.name = subtopicName;
		this.status = status;
		this.date = subtopicDate;
	}

	/**
	 * Multi-argument constructor for Subtopic bean
	 * 
	 * @param subtopicId Id of subtopic
	 * @param subtopicName Name of the subtopic.
	 * @param status Status of the subtopic
	 * @param subtopicDate Date of the Subtopic
	 */
	public Subtopic(int subtopicId, String subtopicName, String status,
			LocalDateTime subtopicDate) {
		super();
		this.id = subtopicId;
		this.name = subtopicName;
		this.status = status;
		this.date = subtopicDate;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Topic getParentTopic() {
		return parentTopic;
	}

	public void setParentTopic(Topic parentTopic) {
		this.parentTopic = parentTopic;
	}

	@Override
	public String toString() {
		return "Subtopic [(Subtopic Id) \t id=" + id + ",\n"
				+ "(Name) \t name=" + name + ",\n"
				+ "(Status) \t status=" + status + ",\n"
				+ "(Date) \t date=" + date + ",\n"
				+ "(Parent Topic) \t parentTopic=" + parentTopic + "\n]";
	}
}
