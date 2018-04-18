package com.revature.services;

import java.text.MessageFormat;
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

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.revature.beans.remote.Subtopic;

/**
 * A service class which acts as the interface for performing requests to the
 * topic service.
 * 
 * @author Ricky Baker (1802-Matt)
 */
@Service
public class RemoteTopicService {
    private final String requestSubtopicsEndpoint;
    private final String verifySubtopicsExistEndpoint;
    private final String gatewayServiceName;

    private RestTemplate restTemplate;
    private EurekaClient discoveryClient;

    @Autowired
    public RemoteTopicService(
                    @Value("${remote-api.topic.request-bulk}") String requestSubtopicsEndpoint,
                    @Value("${remote-api.topic.verify}") String verifySubtopicsExistEndpoint,
                    @Value("${remote-api.gateway.name}") String gatewayServiceName,
                    RestTemplateBuilder restTemplateBuilder,
                    EurekaClient discoveryClient) {

        this.requestSubtopicsEndpoint = requestSubtopicsEndpoint;
        this.verifySubtopicsExistEndpoint = verifySubtopicsExistEndpoint;
        this.gatewayServiceName = gatewayServiceName;
        this.discoveryClient = discoveryClient;
        restTemplate = restTemplateBuilder.build();
    }

    /**
     * Requests the list of given subtopics from the remote topic service.
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param subtopicIds
     *            IDs of subtopics to get
     * 
     * @return A list of the requested subtopics.
     */
    public List<Subtopic> requestSubtopics(Set<Integer> subtopicIds) {
        ParameterizedTypeReference<List<Subtopic>> paramTypeRef = new ParameterizedTypeReference<List<Subtopic>>() {
        };
        InstanceInfo gatewayInfo = discoveryClient
                        .getNextServerFromEureka(gatewayServiceName, false);
        MessageFormat endpointFormat = new MessageFormat(
                        "{0}://{1}:{2,number,#}{3}?ids={4}");

        Object[] endpointParams = new Object[] { "http",
                        gatewayInfo.getIPAddr(), gatewayInfo.getPort(),
                        requestSubtopicsEndpoint,
                        ST.format("<%1; separator=\",\">", subtopicIds) };

        String endpoint = endpointFormat.format(endpointParams);

        ResponseEntity<List<Subtopic>> subtopics = restTemplate
                        .exchange(endpoint, HttpMethod.GET, null, paramTypeRef);

        switch (subtopics.getStatusCode()) {
        case OK:
            return subtopics.getBody();
        default:
            return null;
        }
    }

    /**
     * Verifies the existence of the subtopic IDs.
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param subtopicIds
     *            The list of subtopics to verify.
     * 
     * @return {@literal true} if all given subtopic IDs are valid; otherwise,
     *         {@literal false}. If some error occurred, {@literal null} is
     *         returned.
     */
    public Boolean allSubtopicsExist(Set<Integer> subtopicIds) {
        InstanceInfo gatewayInfo = discoveryClient
                        .getNextServerFromEureka(gatewayServiceName, false);
        MessageFormat endpointFormat = new MessageFormat(
                        "{0}://{1}:{2,number,#}{3}?ids={4}");

        Object[] endpointParams = new Object[] { "http",
                        gatewayInfo.getIPAddr(), gatewayInfo.getPort(),
                        verifySubtopicsExistEndpoint,
                        ST.format("<%1; separator=\",\">", subtopicIds) };

        String endpoint = endpointFormat.format(endpointParams);

        ResponseEntity<Void> response = restTemplate.exchange(endpoint,
                        HttpMethod.GET, null, Void.class);

        switch (response.getStatusCode()) {
        case OK:
            return true;
        case CONFLICT:
            return false;
        default:
            return null;
        }
    }
}