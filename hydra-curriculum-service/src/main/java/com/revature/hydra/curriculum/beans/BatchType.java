package com.revature.hydra.curriculum.beans;

/**
 * Non-persistent bean that references the BatchType a Batch member is in
 */
public class BatchType {

	private Integer id;

	private String name;

	private Integer length = 10;

	/**
	 * Default Constructor for BatchType Creates a new BatchType object
	 */
	public BatchType() {
		super();
	}

	/**
	 * Creates a new BatchType object
	 * @param id Integer id references the id of this object     
	 * @param name Name of the Batch Type
	 * @param length References the length of this BatchType in weeks       
	 */
	public BatchType(Integer id, String name, Integer length) {
		super();
		this.id = id;
		this.name = name;
		this.length = length;
	}

	/**
	 * Creates a new BatchType object
	 * 
	 * @param name Name of the Batch Type
	 * @param length References the length of this BatchType in weeks
	 */
	public BatchType(String name, Integer length) {
		super();
		this.name = name;
		this.length = length;
	}

	/**
	 * Gets the id of this BatchType object
	 * @return this BatchType's id
	 */

	public Integer getId() {
		return id;
	}

	/**
	 * Gets the full name of this BatchType object
	 * @return this BatchType's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the length of this BatchType object
	 * @return The BatchType's length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * Sets the id of this BatchType object
	 * @return this BatchType's id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the name of this BatchType object
	 * @param name The BatchType's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the length of this BatchType object
	 * @param length The BatchType's new length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "BatchType [(BatchType ID) \t id=" + id+ ",\n" 
				+ "(Full Name) \t name=" + name + ",\n" 
				+ "(Length) \t length=" + length + "\n]";
	}

}
