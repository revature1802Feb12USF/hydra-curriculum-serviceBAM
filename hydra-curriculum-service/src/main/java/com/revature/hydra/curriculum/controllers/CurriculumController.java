package com.revature.hydra.curriculum.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.hydra.curriculum.beans.Batch;
import com.revature.hydra.curriculum.beans.Curriculum;
import com.revature.hydra.curriculum.beans.CurriculumSubtopic;
import com.revature.hydra.curriculum.beans.CurriculumSubtopicDTO;
import com.revature.hydra.curriculum.beans.DaysDTO;
import com.revature.hydra.curriculum.beans.Subtopic;
import com.revature.hydra.curriculum.beans.SubtopicName;
import com.revature.hydra.curriculum.exceptions.BadRequestException;
import com.revature.hydra.curriculum.exceptions.NoContentException;
import com.revature.hydra.curriculum.services.CurriculumService;
import com.revature.hydra.curriculum.services.CurriculumSubtopicService;

/**
 * This class establishes REST endpoints for retrieval and modification of curriculum data.
 */
@RestController
@RequestMapping("/api/v2/curriculums")
public class CurriculumController {
	
	/**
	 * Generates a RestTemplate for performing external REST requests.
	 * @param restTemplateBuilder The template builder used to generate the RestTemplate.
	 * @return A RestTemplate to be used for performing external REST API requests.
	 */
	@LoadBalanced
	@Bean
	public RestTemplate buildRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Autowired
	private RestTemplate restTemplate;
	

	@Autowired
	CurriculumService curriculumService;

	@Autowired
	CurriculumSubtopicService curriculumSubtopicService;
	
	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Olayinka Ewumi (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Retrieves all curriculums.
	 * 	HttpStatus.OK: At least 1 curriculum found.
	 *  HttpStatus.NO_CONTENT: No curriculums found.
	 *  
	 * @return The list of all curriculums.
	 * @throws NoContentException Thrown when given list is empty or null. (HttpStatus.NO_CONTENT)
	 */
	@GetMapping
	public List<Curriculum> getAllCurriculums() throws NoContentException {
		return curriculumService.getAllCurriculums();
	}

	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Olayinka Ewumi (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin) 
	 * 
	 * Retrieves the curriculum specified by the ID cId.
	 * 	HttpStatus.OK: Curriculum found.
	 *  HttpStatus.NO_CONTENT: No curriculum with the ID cId found. 
	 *  HttpStatus.BAD_REQUEST: Parameters missing.
	 *  
	 * @param cId The curriculum ID to search for.
	 * 
	 * @return The curriculum specified by the given ID.
	 * @throws BadRequestException There are missing parameters.
	 * @throws NoContentException No curriculum found for the given ID.
	 */
	@GetMapping("/{cId}")
	public Curriculum getCurriculumById(@PathVariable int cId) throws NoContentException {
		return curriculumService.getCurriculumById(cId);
	}

	
	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Olayinka Ewumi (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Retrieves a list of curriculum subtopics with the given curriculum ID.
	 * 	HttpStatus.OK: Found at least 1 subtopic for the specified curriculum.
	 *  HttpStatus.NO_CONTENT: No subtopics found for the specified curriculum.
	 *  HttpStatus.BAD_REQUEST: Missing parameters.
	 *  
	 * @param cId The curriculum ID.
	 * @return A list of curriculum subtopics belonging to the given curriculum.
	 * @throws BadRequestException Parameters missing.
	 * @throws NoContentException No subtopics found for the specified curriculum.
	 */
	@GetMapping("/schedule/{cId}")
	public List<CurriculumSubtopic> getAllCurriculumSchedules(@PathVariable int cId)
			throws BadRequestException, NoContentException {
		return curriculumService.getAllCurriculumSchedulesForCurriculum(cId);
	}
	

	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Olayinka Ewumi (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Retrieves all topic names from the topic name pool.
	 * 	HttpStatus.OK: Found at least 1 topic name.
	 *  HttpStatus.NO_CONTENT: No topic names found.
	 *  
	 * @return The list of all subtopic names.
	 * @throws NoContentException No topics found.
	 */
	@HystrixCommand(fallbackMethod = "getSubtopicNames")
	@GetMapping("topicpool") // TODO WAWA
	public List<SubtopicName> getTopicPool() throws NoContentException {
		ParameterizedTypeReference<List<SubtopicName>> ptr = new ParameterizedTypeReference<List<SubtopicName>>() {};
		
		List<SubtopicName> result = restTemplate.exchange(
				"http://hydra-topic-service/api/v2/subtopicService/getAllSubtopicNames", HttpMethod.GET, null, ptr).getBody();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			throw new NoContentException("No SubtopicNames were found");
		}
	}
	
	/**
	 * Hystrix fallback method for getTopicPool().
	 * 
	 * @return An empty list of subtopic names.
	 * @throws NoContentException No subtopics found.
	 */
	public List<SubtopicName> getSubtopicNames() throws NoContentException {
		throw new NoContentException("No subtopic names were found.");
	}

	
	
	
	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Olayinka Ewumi (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin) 
	 * 
	 * Retrieves all topic names from the topic pool.
	 * 	HttpStatus.OK: Found at least 1 topic.
	 *  HttpStatus.NO_CONTENT: No topics found.
	 * 
	 * @return The list of all subtopics.
	 * @throws NoContentException No topics found.
	 */
	@HystrixCommand(fallbackMethod = "getSubtopics")
	@GetMapping("subtopicpool")
	public List<Subtopic> getSubtopicPool() throws NoContentException {
		ParameterizedTypeReference<List<Subtopic>> ptr = new ParameterizedTypeReference<List<Subtopic>>() {};
		List<Subtopic> result = this.restTemplate.exchange(
				"http://hydra-topic-service/api/v2/subtopicService/getAllSubtopics", HttpMethod.GET, null, ptr).getBody();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			throw new NoContentException("No Subtopics were found");
		}
	}
	
	
	/**
	 * Hystrix fallback method for getSubtopicPool().
	 * 
	 * @return A list of all subtopics.
	 * @throws NoContentException No subtopics found.
	 */
	public List<Subtopic> getSubtopics() throws NoContentException {
		throw new NoContentException("No subtopics found.");
	}

	
	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Add a new curriculum. Handles the case of the new curriculum being added as the master version.
	 * 
	 * @param json JSON string that contains the curriculum subtopic object.
	 * 
	 * @return The newly created curriculum.
	 * @throws JsonMappingException Error occurred in mapping the parsed JSON string.
	 * @throws IOException Error occurred in parsing the JSON string.
	 */
	@PostMapping
	public Curriculum addSchedule(@RequestBody ObjectNode json) throws JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
//		CurriculumSubtopicDTO c = mapper.readValue(json, CurriculumSubtopicDTO.class);
		CurriculumSubtopicDTO c = mapper.convertValue(json, CurriculumSubtopicDTO.class);
		
		
		// save curriculum object first

		Curriculum curriculum = new Curriculum();
		curriculum.setCreatorId(c.getMeta().getCurriculum().getCreatorId());
		curriculum.setDateCreated(c.getMeta().getCurriculum().getDateCreated());
		curriculum.setName(c.getMeta().getCurriculum().getName());
		curriculum.setNumberOfWeeks(c.getMeta().getCurriculum().getNumberOfWeeks());
		curriculum.setVersion(c.getMeta().getCurriculum().getVersion());
		curriculum.setIsMasterVersion(c.getMeta().getCurriculum().getIsMasterVersion());
		
		//curriculum = c.getMeta().getCurriculum();
		
		if (curriculum.getIsMasterVersion() == 1) {
			List<Curriculum> curriculumList = curriculumService.findAllCurriculumByName(curriculum.getName());
			Curriculum prevMaster = null;

			for (int i = 0; i < curriculumList.size(); i++) {
				if (curriculumList.get(i).getIsMasterVersion() == 1)
					prevMaster = curriculumList.get(i);
			}
			
			
			if (prevMaster != null) {
				prevMaster.setIsMasterVersion(0);
				curriculumService.save(prevMaster);
			}
		}
		
		
		Curriculum addedCurr = curriculumService.save(curriculum);

		int numWeeks = c.getWeeks().length;
		for (int i = 0; i < numWeeks; i++) { // for each week
			DaysDTO[] days = c.getWeeks()[i].getDays(); 
			for (int j = 0; j < days.length; j++) { // for each day
				Integer[] subtopic = days[j].getSubtopics();
				for (int k = 0; k < subtopic.length; k++) { // for each sub-topic
					CurriculumSubtopic cs = new CurriculumSubtopic();
					cs.setCurriculum(curriculum);
					cs.setCurriculumSubtopicNameId(subtopic[k]);
					cs.setCurriculumSubtopicWeek(i + 1);
					cs.setCurriculumSubtopicDay(j + 1);
					curriculumSubtopicService.saveCurriculumSubtopic(cs);
				}
			}
		}
		return addedCurr;
	}
	
	
	/**
	 * @author Jordan DeLong
	 * @author Carter Taylor (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Marks the curriculum with the given ID cId as the master version.
	 * 	HttpStatus.BAD_REQUEST: Could not find a curriculum with the provided ID.
	 * 
	 * @param cId The ID of the curriculum to mark as the master version.
	 * 
	 * @throws BadRequestException Could not find a curriculum with the provided ID.
	 * @throws NoContentException Could not find the curriculum with the provided ID.
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@PatchMapping("master/{cId}")
	public void markCurriculumAsMaster(@PathVariable int cId) throws BadRequestException, NoContentException {
		Curriculum c = new Curriculum();

		c = curriculumService.getCurriculumById(cId);

		// find the curriculum with same name and isMaster = 1; set to 0; save
		List<Curriculum> curriculumList = curriculumService.findAllCurriculumByName(c.getName());

		try {
			Curriculum prevMaster = null;
			
			
			// Set all curriculum versions as non-master
			curriculumList.forEach((Curriculum curriculum) -> {
				curriculum.setIsMasterVersion(0);
			});
			
			for (int i = 0; i < curriculumList.size(); i++) {
				if (curriculumList.get(i).getIsMasterVersion() == 1)
					prevMaster = curriculumList.get(i);
			}
			
			if (prevMaster != null) {
				prevMaster.setIsMasterVersion(0);
				curriculumService.save(prevMaster);
			} else {
				LogManager.getRootLogger().error(prevMaster);
			}
		} catch (NullPointerException e) {
			LogManager.getRootLogger().error(e);
		}

		// save new master curriculum
		c.setIsMasterVersion(1);
		curriculumService.save(c);
	}

	/**
	 * @author Carter Taylor (1712-Steve)
	 * @author Stephen Negron (1801-Trevin) 
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Sync batch by getting list of curriculum subtopics related to that batch type.
	 * HttpStatus.RESET_CONTENT if successful, HttpStatus.NO_CONTENT if already synced.
	 * 
	 * @param id  Batch ID
	 *            
	 * @throws CustomException
	 */
	
	// TODO Rework ERRTHANG
	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "emptyMethod")
	@ResponseStatus(value = HttpStatus.RESET_CONTENT)
	@GetMapping("syncbatch/{id}")
	public void syncBatch(@PathVariable int id) throws NoContentException {
		Batch currBatch = restTemplate.getForObject("http://hydra-batch-service/getBatchById/" + id, Batch.class);
		String batchType = currBatch.getType().getName();
		List<Curriculum> curriculumList = curriculumService.findAllCurriculumByNameAndIsMaster(batchType, 1);

		// get master version
		Curriculum c = null;
		for (int i = 0; i < curriculumList.size(); i++) {
			// master version found
			if (curriculumList.get(i).getIsMasterVersion() == 1) {
				c = curriculumList.get(i);
			}
		}

		// if master not found, get latest version
		if (c == null) { // TODO Use Java Streams API
			curriculumList = curriculumService.findAllCurriculumByName(batchType);
			if (curriculumList != null) {
				int min = curriculumList.get(0).getVersion();
				Curriculum tempCurric = curriculumList.get(0);
				for (int i = 1; i < curriculumList.size(); i++) {
					if (curriculumList.get(i).getVersion() > min) {
						min = curriculumList.get(i).getVersion();
						tempCurric = curriculumList.get(i);
					}
				}
				c = tempCurric;
			} else {
				throw new NoContentException("No curriculums by name: " + batchType + " were found");
			}
		}

		List<CurriculumSubtopic> subtopicListMonday = curriculumSubtopicService.getCurriculumSubtopicsForDay(c, 1);
		List<CurriculumSubtopic> subtopicListTuesday = curriculumSubtopicService.getCurriculumSubtopicsForDay(c, 2);
		List<CurriculumSubtopic> subtopicListWednesday = curriculumSubtopicService.getCurriculumSubtopicsForDay(c, 3);
		List<CurriculumSubtopic> subtopicListThursday = curriculumSubtopicService.getCurriculumSubtopicsForDay(c, 4);
		List<CurriculumSubtopic> subtopicListFriday = curriculumSubtopicService.getCurriculumSubtopicsForDay(c, 5);

		Map<Integer, List<CurriculumSubtopic>> map = new ConcurrentHashMap<Integer, List<CurriculumSubtopic>>();

		map.put(1, subtopicListMonday);
		map.put(2, subtopicListTuesday);
		map.put(3, subtopicListWednesday);
		map.put(4, subtopicListThursday);
		map.put(5, subtopicListFriday);

		// logic goes here to add to calendar

		List<Subtopic> persistedSubtopics = this.restTemplate
				.postForEntity("http://hydra-topic-service/api/v2/subtopicService/mapCurriculumSubtopicsToSubtopics/"
						+ currBatch.getId(), HttpMethod.POST, List.class, map).getBody();

		if (persistedSubtopics.isEmpty()) {
			throw new NoContentException("No subtopics were found");
		}
	}
	
	
	/**
	 * Hystrix fallback method for syncBatch().
	 */
	public void emptyMethod() {
		
	}

	/**
	 * @author Carter Taylor
	 * @author James Holzer (1712-Steve)
	 * @author Stephen Negron (1801-Trevin)
	 * @author Rafael Sanchez (1801-Trevin)
	 * 
	 * Deletes a curriculum version along with it's related CurriculumSubtopics
	 * HttpStatus.OK if successful.
	 * 
	 * @param version Curriculum version 
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping("version")
	public void deleteCurriculumVersion(@RequestBody Curriculum version) {
		curriculumService.deleteCurriculum(version);
	}

}
