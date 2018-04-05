package com.revature.hydra.curriculum.service.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.revature.hydra.curriculum.bean.Curriculum;
import com.revature.hydra.curriculum.repository.CurriculumRepository;
import com.revature.hydra.curriculum.repository.CurriculumSubtopicRepository;
import com.revature.hydra.curriculum.service.CurriculumService;

public class CurriculumServiceTests {
	
	private CurriculumRepository mockCurriculumRepository;
	private CurriculumSubtopicRepository mockCurriculumSubtopicRepository;
	private CurriculumService curriculumService;
	
	/*
	 * create a mock CurriculumRepository
	 * create a mock CurriculumSubtopicRepository
	 * Instantiate a curriculumService with the mock CurriculumRepository and mock CurriculumSubtopicRepository
	 */
	@Before
	public void before() {
		mockCurriculumRepository = mock(CurriculumRepository.class);
		mockCurriculumSubtopicRepository = mock(CurriculumSubtopicRepository.class);
		curriculumService = new CurriculumService(mockCurriculumRepository, mockCurriculumSubtopicRepository);		
	}
	
	@Test
	public void getAllCurriculum_returnsUsersArgument() {
		
	}
	
	/*
	 * Testing if the given ID in getCurriculumByID exists in the database.
	 */
	@Test
	public void getCurriculumById_returnsCurriculumWithMatchingId() {
		// SETUP
		Curriculum curriculum = new Curriculum(1, null, 1, null, null, null, 1, 1);	
		when(mockCurriculumRepository.findByCurriculumId(1)).thenReturn(curriculum);
		
		// EXECUTE
		Curriculum returnCurriculum = curriculumService.getCuricullumById(1);
		
		// TEST
		assertEquals(returnCurriculum.getCurriculumId().intValue(), 1);
	}
	
	/*
	 * Testing if the given ID in getCurriculumByIDKeepPwd exists in the database.
	 */
	@Test
	public void getCurriculumByIdKeepPwd_returnsCurriculumWithMatchingId() {
		// SETUP
		Curriculum curriculum = new Curriculum(1, null, 1, null, null, null, 1, 1);	
		when(mockCurriculumRepository.findByCurriculumId(1)).thenReturn(curriculum);
		
		// EXECUTE
		Curriculum returnCurriculum = curriculumService.getCuricullumByIdKeepPwd(1);
		
		// TEST
		assertEquals(returnCurriculum.getCurriculumId().intValue(), 1);
	}
	
	/*
	 * Test and verify to see if curriculum will be saved in the repository 1 time in the 
	 * mockCurriculumRepository.
	 */
	@Test
	public void save_callsRepositorySave() {
		// SETUP
		Curriculum curriculum = new Curriculum(1, null, 1, null, null, null, 1, 1);	
		
		// EXECUTE
		curriculumService.save(curriculum);
		
		// TEST
		verify(mockCurriculumRepository, times(1)).save(curriculum);
	}
	
	/*
	 * Test and verify if findByCurriculumName will be invoked once and find the curriculumName in the 
	 * mockCurriculumRepository.
	 */
	@Test
	public void findAllCurriculumByName_callsRepositoryFindByCurriculumName() {
		// SETUP
		String name = "name";
		
		// EXECUTE
		curriculumService.findAllCurriculumByName(name);
		
		// TEST
		verify(mockCurriculumRepository, times(1)).findByCurriculumName(name);
	}
	
	/*
	 * Test and verify if findAllCurriculumByNameAndIsMaster will be invoked once and will search for master curriculum 
	 * in the mockCurriculumRepository.
	 */
	@Test
	public void findAllCurriculumByNameAndIsMaster_callsRepositoryFindByCurriculumNameAndIsMaster() {
		// SETUP
		String name = "name";
		Integer isMaster = 1;
		
		// EXECUTE
		curriculumService.findAllCurriculumByNameAndIsMaster(name, isMaster);
		
		// TEST
		verify(mockCurriculumRepository, times(1)).findByCurriculumNameAndIsMaster(name, isMaster);
	}
	
	/*
	 * Test and verify if a curriculum will be invoked once and will delete the curriculum from mockCurriculumRepository. 
	 */
	@Test
	public void deleteCurriculum_callsRepositoryDelete() {
		// SETUP
		Curriculum version = new Curriculum();
		
		// EXECUTE
		curriculumService.deleteCurriculum(version);
		
		// TEST
		verify(mockCurriculumRepository, times(1)).delete(version);
	}
	
	/*
	 * test and verify if a subtopic will be deleted from mockCurriculumSubtopicRepository.
	 */
	@Test
	public void deleteCurriculumSubtopics_callsRepositoryDeleteCurriculumSubtopics() {
		// SETUP
		Curriculum version = new Curriculum();
		
		// EXECUTE
		curriculumService.deleteCurriculumSubtopics(version);
		
		// TEST
		verify(mockCurriculumSubtopicRepository, times(1)).deleteByCurriculum(version);
	}

}
