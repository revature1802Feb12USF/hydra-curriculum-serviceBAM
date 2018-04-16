package com.revature.beans.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.beans.ScheduledSubtopic;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ScheduleDto {
    private Integer curriculumId;
    private List<ScheduledSubtopic> subtopics;
    
    public List<ScheduledSubtopic> getSubtopics() {
        return subtopics;
    }
    
    public void setSubtopics(List<ScheduledSubtopic> subtopics) {
        this.subtopics = subtopics;
    }
    
    public Integer getCurriculumId() {
        return curriculumId;
    }
    
    public void setCurriculumId(Integer curriculumId) {
        this.curriculumId = curriculumId;
    }
}
