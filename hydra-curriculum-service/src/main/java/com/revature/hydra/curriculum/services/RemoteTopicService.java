package com.revature.hydra.curriculum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.stringtemplate.v4.ST;

import com.revature.hydra.curriculum.beans.remote.Subtopic;

@Service
public class RemoteTopicService {
	
	// TODO annotate with @Value() from properties
	private static String topicEndpoint = "";
	private static String requestSubtopicsEndpoint = "";
	
	@Autowired
	private static RestTemplate restTemplate;
	
	@Bean
	private static RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	
	/**
	 * Requests the list of given subtopics from the remote topic service.
	 * @param subtopicIds IDs of subtopics to get
	 * @return A list of the requested subtopics.
	 */
	public List<Subtopic> requestSubtopics(List<Integer> subtopicIds) {
		List<Subtopic> subtopics;
		ParameterizedTypeReference<List<Subtopic>> paramTypeRef = new ParameterizedTypeReference<List<Subtopic>>() {};
		
		subtopics = restTemplate.exchange(requestSubtopicsEndpoint
				+ "?ids=" + ST.format("<%1; separator=\",\"", subtopicIds),
				HttpMethod.GET, null, paramTypeRef).getBody();
		
		return subtopics;
	}
}
