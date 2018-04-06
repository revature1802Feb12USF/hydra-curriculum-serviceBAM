package com.revature.hydra.curriculum.pojos;

/**
 * Non-persistent bean holding subtopic status information
 */
public class SubtopicStatus {

	private Integer id;

	private String name;

	public SubtopicStatus() {
		
	}

	public SubtopicStatus(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SubtopicStatus(String name) {
		super();
		this.name = name;
	}//NOSONAR

	public Integer getId() {
		return id;
	}//NOSONAR

	public void setId(Integer id) {
		this.id = id;
	}//NOSONAR

	public String getName() {
		return name;
	}//NOSONAR

	public void setName(String name) {
		this.name = name;
	}//NOSONAR

	@Override
	public String toString() {
		return "SubtopicStatus [(Suptopic Status ID) \t id=" + id + ",\n"
				+ "(Name) \t name=" + name + "\n]";//NOSONAR
	}

}
