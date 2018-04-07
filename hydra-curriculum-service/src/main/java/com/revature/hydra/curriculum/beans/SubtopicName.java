package com.revature.hydra.curriculum.beans;

/**
 * non-persistent bean for Subtopic Name
 * This class contains data on the Subtopic Name..
 */
public class SubtopicName {

	private Integer id;

	private String name;

	private TopicName topic;

	private SubtopicType type;

	/**
	 * no arg constructor
	 */
	public SubtopicName() {
		super();
	}

	/**
	 * Creates a SubtopicName object with an integer ID, name, topic name, subtopic type
	 * @param id
	 * @param name
	 * @param topic
	 * @param type
	 */
	public SubtopicName(Integer id, String name, TopicName topic, SubtopicType type) {
		super();
		this.id = id;
		this.name = name;
		this.topic = topic;
		this.type = type;
	}

	/**
	 * Creates a SubtopicName object with a name, topic name, and subtopic type
	 * @param name
	 * @param topic
	 * @param type
	 */
	public SubtopicName(String name, TopicName topic, SubtopicType type) {
		super();
		this.name = name;
		this.topic = topic;
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

	public TopicName getTopic() {
		return topic;
	}

	public void setTopic(TopicName topic) {
		this.topic = topic;
	}

	public SubtopicType getType() {
		return type;
	}

	public void setType(SubtopicType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SubtopicName [\n" + "(SubtopicName ID) \t id =" + id + ",\n"
				+ "(SubtopicName name) \t name =" + name + ",\n"
				+ "(SubtopicName topic) \t topic=" + topic + "]";
	}

}
