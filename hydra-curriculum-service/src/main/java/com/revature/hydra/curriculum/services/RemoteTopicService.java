package com.revature.hydra.curriculum.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.stringtemplate.v4.ST;

import com.revature.hydra.curriculum.beans.remote.Subtopic;

@Service
public class RemoteTopicService {
	
	// TODO annotate with @Value() from properties
	@Value("")
	private static String requestSubtopicsEndpoint;
	
	@Value("")
	private static String verifySubtopicsExistEndpoint;
	
	@Autowired
	private static RestTemplate restTemplate;
	
	
	/**
	 * Generates a RestTemplate for performing external REST requests.
	 * @param restTemplateBuilder The template builder used to generate the RestTemplate.
	 * @return A RestTemplate to be used for performing external REST API requests.
	 */
	@LoadBalanced
	@Bean
	private static RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	
	/**
	 * Requests the list of given subtopics from the remote topic service.
	 * @param subtopicIds IDs of subtopics to get
	 * @return A list of the requested subtopics.
	 */
	public List<Subtopic> requestSubtopics(Set<Integer> subtopicIds) {
		ParameterizedTypeReference<List<Subtopic>> paramTypeRef = new ParameterizedTypeReference<List<Subtopic>>() {};
		
		return restTemplate.exchange(requestSubtopicsEndpoint
				+ "?ids=" + ST.format("<%1; separator=\",\"", subtopicIds),
				HttpMethod.GET, null, paramTypeRef).getBody();
	}
	
	/**
	 * Verifies the existence of the subtopic IDs.
	 * @param subtopicIds The list of subtopics to verify.
	 * @return {@literal true} if all given subtopic IDs are valid; otherwise, {@literal false}.
	 */
	public Boolean allSubtopicsExist(Set<Integer> subtopicIds) {
		
		ResponseEntity<Void> response 
			= restTemplate.exchange(verifySubtopicsExistEndpoint 
				+ "?ids=" + ST.format("<%1; separator=\",\"", subtopicIds), 
				HttpMethod.GET, null, Void.class);
		
		switch(response.getStatusCode()) {
		case OK:
			return true;
		case CONFLICT:
			return false;
		default:
			return null;
		}
	}
}
