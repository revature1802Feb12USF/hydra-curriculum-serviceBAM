package com.revature.hydra.curriculum.pojos;

import java.util.Arrays;
/**
 * DTO containing a MetaDTO and WeeksDTO[]
 */
public class CurriculumSubtopicDTO {

	private MetaDTO meta;
	
	private WeeksDTO [] weeks;
	
	public CurriculumSubtopicDTO(){
		//Empty Because of No Args.
		
	}

	public MetaDTO getMeta() {
		return meta;
	}

	public void setMeta(MetaDTO meta) {
		this.meta = meta;
	}

	public WeeksDTO[] getWeeks() {
		return weeks;
	}

	public void setWeeks(WeeksDTO[] weeks) {
		this.weeks = weeks;
	}

	@Override
	public String toString() {
		return "CurriculumSubtopicDTO [(Curriculum) \t meta=" + meta + ",\n"
				+ "(Weeks) \t weeks=" + Arrays.toString(weeks) + "\n]";
	}
	
}
