package com.revature.hydra.curriculum.pojos;

import java.util.Arrays;

/**
 * DTO holding DaysDTO information
 */
public class WeeksDTO {

	private DaysDTO[] days;
	
	public WeeksDTO(){
		
	}

	public WeeksDTO(DaysDTO[] days) {
		super();
		this.days = days;
	}
	
	public DaysDTO[] getDays() {
		return days;
	}

	public void setDays(DaysDTO[] days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "WeeksDTO [(Days) \t days=" + Arrays.toString(days) + "\n]";
	}
	
}
