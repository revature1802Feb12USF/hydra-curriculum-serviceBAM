package com.revature.hydra.curriculum.beans.remote;

public class Topic {
	private Integer id;
	private String name;
	private Integer batchId;
	private Integer weekNumber;
	
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
	
	public Integer getBatchId() {
		return batchId;
	}
	
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	
	public Integer getWeekNumber() {
		return weekNumber;
	}
	
	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}
	
	@Override
	public String toString() {
		return "Topic [(Topic Id) \t id=" + id + ",\n"
				+ "(Name) \t name=" + name + ",\n"
				+ "(Batch Id) \t batchId=" + batchId + ",\n"
				+ "(Week Number) \t weekNumber=" + weekNumber + "\n]";
	}
}
