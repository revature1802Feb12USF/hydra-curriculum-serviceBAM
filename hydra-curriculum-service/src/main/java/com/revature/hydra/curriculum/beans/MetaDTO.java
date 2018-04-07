package com.revature.hydra.curriculum.beans;

/**
 * non-persistent bean for MetaDTO.
 * This is a data transfer object that contains data of a curriculum
 */
public class MetaDTO {

	private Curriculum curriculum;

	/**
	 * no args constructor for MetaDTO.
	 */
	public MetaDTO(){
		
	}

	/**
	 * Creates a MetaDTO object that will contain a curriculum
	 * @param curriculum
	 */
	public MetaDTO(Curriculum curriculum) {
		super();
		this.curriculum = curriculum;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@Override
	public String toString() {
		return "MetaDTO [\n" + "(MetaDTO of Curriculum) \t curriculum =" + curriculum + "]";
	}
	
}
