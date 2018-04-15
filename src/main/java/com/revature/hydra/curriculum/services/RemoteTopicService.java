package com.revature.hydra.curriculum.services;

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

import com.revature.hydra.curriculum.beans.remote.Subtopic;

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
     * @param subtopicIds IDs of subtopics to get
     * @return A list of the requested subtopics.
     * 
     * @author Ricky Baker (1802-Matt)
     */
    public List<Subtopic> requestSubtopics(Set<Integer> subtopicIds) {
        ParameterizedTypeReference<List<Subtopic>> paramTypeRef = new ParameterizedTypeReference<List<Subtopic>>() {};
        ResponseEntity<List<Subtopic>> subtopics = restTemplate.exchange(requestSubtopicsEndpoint
                + "?ids=" + ST.format("<%1; separator=\",\">", subtopicIds),
                HttpMethod.GET, null, paramTypeRef);
        
        switch(subtopics.getStatusCode()) {
        case OK:
            return subtopics.getBody();
        default:
            return null;
        }
    }
    
    /**
     * Verifies the existence of the subtopic IDs.
     * 
     * @param subtopicIds The list of subtopics to verify.
     * @return {@literal true} if all given subtopic IDs are valid; otherwise, {@literal false}.
     *             If some error occurred, {@literal null} is returned.
     * 
     * @author Ricky Baker (1802-Matt)
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