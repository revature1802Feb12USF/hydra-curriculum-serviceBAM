package com.revature.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.stringtemplate.v4.ST;

import com.revature.beans.remote.Subtopic;

/**
 * A service class for that handles performing requests to the topic service.
 * 
 * @author Ricky Baker (1802-Matt)
 */
@Service
public class RemoteTopicService {
    private String requestSubtopicsEndpoint;
    private String verifySubtopicsExistEndpoint;
    private RestTemplate restTemplate;
    
    @Autowired
    public RemoteTopicService(@Value("${remote-api.topic.request-bulk}") String requestSubtopicsEndpoint, 
            @Value("${remote-api.topic.verify}") String verifySubtopicsExistEndpoint,
            RestTemplateBuilder restTemplateBuilder) {
        this.requestSubtopicsEndpoint = requestSubtopicsEndpoint;
        this.verifySubtopicsExistEndpoint = verifySubtopicsExistEndpoint;
        restTemplate = restTemplateBuilder.build();
    }
    
    /**
     * Requests the list of given subtopics from the remote topic service.
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param subtopicIds IDs of subtopics to get
     * 
     * @return A list of the requested subtopics.
     * 
     */
    public List<Subtopic> requestSubtopics(Set<Integer> subtopicIds) {
        ParameterizedTypeReference<List<Subtopic>> paramTypeRef = new ParameterizedTypeReference<List<Subtopic>>() {};
//    	ParameterizedTypeReference<String> paramTypeRef = new ParameterizedTypeReference<String>() {};
        
        String endpoint = requestSubtopicsEndpoint
                + "?ids=" + ST.format("<%1; separator=\",\">", subtopicIds);
      ResponseEntity<List<Subtopic>> subtopics
//    	ResponseEntity<String> subtopics
        	= restTemplate.exchange(endpoint,
                HttpMethod.GET, null, paramTypeRef);
//        String body = subtopics.getBody();
        
        switch(subtopics.getStatusCode()) {
        case OK:
//            return subtopics.getBody();
        	return null;
        default:
            return null;
        }
    }
    
    /**
     * Verifies the existence of the subtopic IDs.
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param subtopicIds The list of subtopics to verify.
     * 
     * @return {@literal true} if all given subtopic IDs are valid; otherwise, {@literal false}.
     *             If some error occurred, {@literal null} is returned.
     * 
     */
    public Boolean allSubtopicsExist(Set<Integer> subtopicIds) {
        ResponseEntity<Void> response
            = restTemplate.exchange(verifySubtopicsExistEndpoint
                + "?ids=" + ST.format("<%1; separator=\",\">", subtopicIds), 
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