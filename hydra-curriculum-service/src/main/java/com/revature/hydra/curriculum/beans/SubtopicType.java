package com.revature.hydra.curriculum.beans;

/**
 * Non-persistent bean holding Subtopic Type information.
 */
public class SubtopicType {

	private Integer id;

	private String name;

	public SubtopicType() {

	}

	public SubtopicType(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SubtopicType(String name) {
		super();//NOSONAR
		this.name = name;
	}

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
		return "SubtopicType [(Subtopic Type ID) \t id=" + id + ",\n"
				+ "(Name) \t name=" + name + "\n]";//NOSONAR
	}

}
