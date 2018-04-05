package com.revature.hydra.curriculum.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.hydra.curriculum.bean.Curriculum;
import com.revature.hydra.curriculum.bean.CurriculumSubtopic;
import com.revature.hydra.curriculum.pojos.BadRequestException;
import com.revature.hydra.curriculum.pojos.Batch;
import com.revature.hydra.curriculum.pojos.CurriculumSubtopicDTO;
import com.revature.hydra.curriculum.pojos.DaysDTO;
import com.revature.hydra.curriculum.pojos.NoContentException;
import com.revature.hydra.curriculum.pojos.Subtopic;
import com.revature.hydra.curriculum.pojos.SubtopicName;
import com.revature.hydra.curriculum.service.CurriculumService;
import com.revature.hydra.curriculum.service.CurriculumSubtopicService;

@RestController
@RequestMapping("/api/v2/curriculum/")
public class CurriculumController {

	@LoadBalanced
	@Bean
	public RestTemplate buildRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Autowired
	private RestTemplate restTemplate;
	
	// TODO DALEK IT
	// @HystrixCommand(fallbackMethod = "cachedTopic")
	// @GetMapping("/getTopic")
	// public Topic getTopic() {
	// System.out.println("hit /getTopic");
	// Topic t =
	// restTemplate.getForObject("http://hydra-topic-service/api/v2/Topic",
	// Topic.class);
	// return t;
	// }

	// public Topic cachedTopic() {
	// return new Topic();
	// }

	@Autowired
	CurriculumService curriculumService;

	@Autowired
	CurriculumSubtopicService curriculumSubtopicService;
	
	// TODO DALEK IT
	public CurriculumService get() {
		return curriculumService;
	}

	// TODO WHAT
	/***
	 * @author Nam Mai Method is needed for injecting mocked services for unit test
	 */
	@Autowired
	public CurriculumController(CurriculumService cs, CurriculumSubtopicService css) {
		curriculumService = cs;
		curriculumSubtopicService = css;
	}

	/**
	 * @author Carter Taylor (1712-Steve), Olayinka Ewumi (1712-Steve), Stephen
	 *         Negron (1801-Trevin), Rafael Sanchez (1801-Trevin) getAllCurriculum:
	 *         method to get all curriculums
	 * @return List<Curriculum>, HttpStatus.OK if successful, HttpStatus.NO_CONTENT
	 *         if list is empty
	 * @throws Exception
	 */
	@GetMapping(value = "all")
	public List<Curriculum> getAllCurriculum() throws NoContentException {
		List<Curriculum> curriculums = curriculumService.getAllCurriculum();
		if (curriculums != null && !curriculums.isEmpty()) {
			return curriculums;
		} else {
			// return new ResponseEntity<List<Curriculum>>(HttpStatus.NO_CONTENT);
			throw new NoContentException("No Curriculums Found");
		}
	}

	/**
	 * @author Carter Taylor (1712-Steve), Olayinka Ewumi (1712-Steve), Stephen
	 *         Negron (1801-Trevin), Rafael Sanchez (1801-Trevin) getCurriculumById:
	 *         method to get a Curriculum by its Id
	 * @return Curriculum, HttpStatus.OK if successful HttpStatus.NO_CONTENT if id
	 *         doesn't match, HttpStatus.BAD_REQUEST if missing parameters
	 * @throws BadRequestException
	 * @throws NoContentException
	 */
//	@SuppressWarnings("unused") // TODO ERASE ME (Suppress Warning annotation)
	@GetMapping(value = "getcurriculum/{cId}") // TODO FIX ENDPOINT
	public Curriculum getCurriculumById(@PathVariable int cId) throws BadRequestException, NoContentException {
		Curriculum result = new Curriculum(); // TODO REMOVE ALLOCATION
		try {
			result = curriculumService.getCuricullumById(cId);
		} catch (NullPointerException e) { // TODO REMOVE POINTLESS TRY-CATCH
			throw new BadRequestException("Request Failed");
		}
		
		
		if (result != null) {
			return result;
		} else {
			throw new NoContentException("Curriculum by id: " + cId + " was not found");
		}
	}

	
	
	
	/**
	 * @author Carter Taylor (1712-Steve), Olayinka Ewumi (1712-Steve), Stephen
	 *         Negron (1801-Trevin), Rafael Sanchez (1801-Trevin)
	 * @param PathVariable:
	 *            int cId holds curriculumId getAllCurriculumSchedules: method to
	 *            retrieve list of curriculum subtopics given a curriculumId
	 * @return List<CurriculumSubtopics>, HttpStatus.OK if successful
	 *         HttpStatus.NO_CONTENT if id doesn't match, HttpStatus.BAD_REQUEST if
	 *         missing parameters
	 * @throws BadRequestException
	 * @throws NoContentException
	 */
	@GetMapping(value = "schedule/{cId}")
	public List<CurriculumSubtopic> getAllCurriculumSchedules(@PathVariable int cId)
			throws BadRequestException, NoContentException {
		Curriculum c = new Curriculum();

		try {
			c = curriculumService.getCuricullumById(cId);
			c.setCurriculumId(cId); // TODO REDUNDANT
		} catch (NullPointerException e) {
			throw new BadRequestException("Request Failed");
		}
		
		// TODO Rearrange into try-catch block
		List<CurriculumSubtopic> result = curriculumSubtopicService.getCurriculumSubtopicForCurriculum(c);
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			throw new NoContentException("No schedules by Curriculum Id: " + cId + " were found");
		}
	}
	
	
	
	// TODO FIX AUTHORS, 1 AUTHOR PER @AUTHOR IN JAVADOCS
	/**
	 * @author Carter Taylor (1712-Steve), Olayinka Ewumi (1712-Steve), Stephen
	 *         Negron (1801-Trevin), Rafael Sanchez (1801-Trevin) getTopicPool:
	 *         method to get list of topics
	 * @return List<SubtopicName>, HttpStatus.OK if successful,
	 *         HttpStatus.NO_CONTENT if list is empty
	 * @throws NoContentException
	 */
	@HystrixCommand(fallbackMethod = "getSubtopicNames")
	@GetMapping("topicpool")
	public List<SubtopicName> getTopicPool() throws NoContentException {
		ParameterizedTypeReference<List<SubtopicName>> ptr = new ParameterizedTypeReference<List<SubtopicName>>() {};
		
		List<SubtopicName> result = this.restTemplate.exchange(
				"http://hydra-topic-service/api/v2/subtopicService/getAllSubtopicNames", HttpMethod.GET, null, ptr).getBody();
		if (result != null) {
			return result;
		} else {
			throw new NoContentException("No SubtopicNames were found");
		}
	}
	
	// Hystrix fallback method for getTopicPool
	public List<SubtopicName> getSubtopicNames() {
		return new ArrayList<SubtopicName>();
	}

	
	
	
	/**
	 * @author Carter Taylor (1712-Steve), Olayinka Ewumi (1712-Steve), Stephen
	 *         Negron (1801-Trevin), Rafael Sanchez (1801-Trevin) getSubtopicPool:
	 *         method to get list of subtopics with associated batch and status
	 * @return List<Subtopic>, HttpStatus.OK if successful, HttpStatus.NO_CONTENT if
	 *         list is empty
	 * @throws NoContentException
	 */
	@HystrixCommand(fallbackMethod = "getSubtopics")
	@GetMapping("subtopicpool")
	public List<Subtopic> getSubtopicPool() throws NoContentException {
		ParameterizedTypeReference<List<Subtopic>> ptr = new ParameterizedTypeReference<List<Subtopic>>() {
		};
		List<Subtopic> result = this.restTemplate.exchange(
				"http://hydra-topic-service/api/v2/subtopicService/getAllSubtopics", HttpMethod.GET, null, ptr).getBody();
		if (result != null) {
			return result;
		} else {
			throw new NoContentException("No Subtopics were found");
		}
	}
	
	// Linked to by a Hystrix Command for getSubtopicPool
	public List<Subtopic> getSubtopics() {
		return new ArrayList<Subtopic>();
	}

	
	// TODO PARAMETER
	/**
	 * @author Carter Taylor (1712-Steve), Stephen Negron (1801-Trevin), Rafael
	 *         Sanchez (1801-Trevin)
	 * @param json
	 *            String that contains curriculum subtopic object addSchedule:
	 *            method that takes a curriculum subtopic (schedule) as input from
	 *            request body and saves both curriculum and curriculum subtopic.
	 *            Handles case of incoming curriculum being marked as master
	 *            version.
	 * @return Curriculum, HttpStatus.CREATED if successful
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@PostMapping(value = "addcurriculum")
	public Curriculum addSchedule(@RequestBody String json) throws JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		CurriculumSubtopicDTO c = mapper.readValue(json, CurriculumSubtopicDTO.class);

		// save curriculum object first

		Curriculum curriculum = new Curriculum();
		curriculum.setCurriculumCreator(c.getMeta().getCurriculum().getCurriculumCreator());
		curriculum.setCurriculumDateCreated(c.getMeta().getCurriculum().getCurriculumDateCreated());
		curriculum.setCurriculumName(c.getMeta().getCurriculum().getCurriculumName());
		curriculum.setCurriculumNumberOfWeeks(c.getMeta().getCurriculum().getCurriculumNumberOfWeeks());
		curriculum.setCurriculumVersion(c.getMeta().getCurriculum().getCurriculumVersion());
		curriculum.setIsMaster(c.getMeta().getCurriculum().getIsMaster());
		
		//curriculum = c.getMeta().getCurriculum();
		
		if (curriculum.getIsMaster() == 1) {
			List<Curriculum> curriculumList = curriculumService.findAllCurriculumByName(curriculum.getCurriculumName());
			Curriculum prevMaster = null;

			for (int i = 0; i < curriculumList.size(); i++) {
				if (curriculumList.get(i).getIsMaster() == 1)
					prevMaster = curriculumList.get(i);
			}
			
			
			if (prevMaster != null) {
				prevMaster.setIsMaster(0);
				curriculumService.save(prevMaster);
			}
			
			
			// Alternative
//			Optional<Curriculum> prevMaster = curriculumList.stream().reduce((a, b) -> {
//				return b.getIsMaster() == 1 ? b : a;
//			});
//			
//			if(prevMaster.isPresent()) {
//				Curriculum curr = prevMaster.get();
//				curr.setIsMaster(0);
//				curriculumService.save(curr);
//			}
			
			
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
	
	
	// TODO Rework code for efficiency (offload to db)
	/**
	 * @author Jordan DeLong Carter Taylor (1712-Steve), Stephen Negron
	 *         (1801-Trevin), Rafael Sanchez (1801-Trevin)
	 * @param PathVariable:
	 *            int cId that holds curriculumId markCurricullumAsMaster: method
	 *            that marks selected curriculum as master version (identified by id
	 *            sent as request parameter), and sets previous master version to
	 *            non-master status.
	 * @return HttpStatus.BAD_REQUEST if missing parameter, HttpStatus.ACCEPTED if
	 *         successful
	 * @throws BadRequestException
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "makemaster/{cId}")
	public void markCurriculumAsMaster(@PathVariable int cId) throws BadRequestException {
		Curriculum c = new Curriculum();

		try {
			c = curriculumService.getCuricullumByIdKeepPwd(cId);
		} catch (NullPointerException e) {
			throw new BadRequestException("Request failed");
		}

		// find the curriculum with same name and isMaster = 1; set to 0; save
		List<Curriculum> curriculumList = curriculumService.findAllCurriculumByName(c.getCurriculumName());

		try {
			Curriculum prevMaster = null;

			for (int i = 0; i < curriculumList.size(); i++) {
				if (curriculumList.get(i).getIsMaster() == 1)
					prevMaster = curriculumList.get(i);
			}
			if (prevMaster != null) {
				prevMaster.setIsMaster(0);
				curriculumService.save(prevMaster);
			} else {
				LogManager.getRootLogger().error(prevMaster);
			}
		} catch (NullPointerException e) {
			LogManager.getRootLogger().error(e);
		}

		// save new master curriculum
		c.setIsMaster(1);
		curriculumService.save(c);
	}

	/**
	 * @author Carter Taylor (1712-Steve), Stephen Negron (1801-Trevin), Rafael
	 *         Sanchez (1801-Trevin)
	 * @param PathVariable
	 *            int id batch id given as path variable syncBatch: sync batch by
	 *            getting list of curriculum subtopics related to that batch type
	 * @return HttpStatus.RESET_CONTENT if successful, HttpStatus.NO_CONTENT if
	 *         already synced
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
			if (curriculumList.get(i).getIsMaster() == 1) {
				c = curriculumList.get(i);
			}
		}

		// if master not found, get latest version
		if (c == null) { // TODO Use Java Streams API
			curriculumList = curriculumService.findAllCurriculumByName(batchType);
			if (curriculumList != null) {
				int min = curriculumList.get(0).getCurriculumVersion();
				Curriculum tempCurric = curriculumList.get(0);
				for (int i = 1; i < curriculumList.size(); i++) {
					if (curriculumList.get(i).getCurriculumVersion() > min) {
						min = curriculumList.get(i).getCurriculumVersion();
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
	
	// Hystrix Fallback for syncBatch
	public void emptyMethod() {
		
	}

	/**
	 * @author Carter Taylor, James Holzer (1712-Steve), Stephen Negron
	 *         (1801-Trevin), Rafael Sanchez (1801-Trevin)
	 * @param RequestBody
	 *            Curriculum version deleteCurriculumVersion: Deletes a curriculum
	 *            version along with it's related CurriculumSubtopics
	 * @return HttpStatus.OK if successful
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping("deleteversion")
	public void deleteCurriculumVersion(@RequestBody Curriculum version) {
		curriculumService.deleteCurriculum(version);
	}

}
