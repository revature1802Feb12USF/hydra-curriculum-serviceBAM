package com.revature.hydra.curriculum.beans.remote;

import java.time.LocalDateTime;

/**
 * non-persistent bean for Batch.
 * This class contains data about Batch. 
 */
public class Batch {

	private Integer id; 

	private String name; 

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Integer trainerID;

	private BatchType type;
	
	/**
	 * No-args constructor
	 */
	public Batch() {
		super();
	}

	/**
	 * Creates a Batch with a specified batch name, the start date for the training of the
	 * batch, the end date of training for the batch, the trainer's ID associated
	 * with the batch, and the type of batch it will be.
	 * 
	 * @param name the name of the Batch
	 * @param startDate start date of the batch
	 * @param endDate end date of the batch
	 * @param trainerID	trainer's ID 
	 * @param type specifying what type of batch it will be
	 */
	public Batch(String name, LocalDateTime startDate, LocalDateTime endDate, int trainerID, BatchType type) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainerID = trainerID;
		this.type = type;
	}
	
	/**
	 * Creates a Batch with an ID, a specified batch name, the start date for the training of the
	 * batch, the end date of training for the batch, the trainer's ID associated
	 * with the batch, and the type of batch it will be.
	 * 
	 * @param id for unique identifier
	 * @param name the name of the Batch
	 * @param startDate start date of the batch
	 * @param endDate end date of the batch
	 * @param trainerID	trainer's ID 
	 * @param type specifying what type of batch it will be
	 */
	public Batch(Integer id, String name, LocalDateTime startDate, LocalDateTime endDate, int trainer, BatchType type) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainerID = trainer;
		this.type = type;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public int getTrainer() {
		return trainerID;
	}

	public void setTrainer(int trainer) {
		this.trainerID = trainer;
	}

	public int getTrainerID() {
		return trainerID;
	}

	public void setTrainerID(int trainerID) {
		this.trainerID = trainerID;
	}

	public BatchType getType() {
		return type;
	}

	public void setType(BatchType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Batch [\n " + "(Batch ID) \t id =" + id + ",\n" 
				+ "(Batch Name) \t name =" + name + ",\n" 
				+ "(Batch Start Date) \t startDate =" + startDate + ",\n" 
				+ "(End Date) \t endDate =" + endDate + ",\n" 
				+ "(Trainer's ID) \t trainerID =" + trainerID + ",\n" 
				+ "(Batch Type) \t type =" + type 
				+ "]";
	}

}
