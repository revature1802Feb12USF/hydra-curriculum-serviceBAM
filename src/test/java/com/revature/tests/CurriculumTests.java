package com.revature.tests;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import io.restassured.RestAssured;

public class CurriculumTests {
	
	/**
	 * Rest Assured test to retrieve all curriculums
	 * 
	 * @author Johne Vang (1802-Matt)
	 */
	@Test
	public void testGetAllCurriculums() {
		RestAssured.get("http://localhost:9001/api/v2/curricula/all")
		.then()
		.assertThat()
		.body("[0].id", equalTo(100));
		
		RestAssured.get("http://localhost:9001/api/v2/curricula/all")
		.then()
		.assertThat()
		.body("[1].id", equalTo(101));
		
		RestAssured.get("http://localhost:9001/api/v2/curricula/all")
		.then()
		.assertThat()
		.body("[2].id", equalTo(102));
		
		RestAssured.get("http://localhost:9001/api/v2/curricula/all")
		.then()
		.assertThat()
		.body("[3].id", equalTo(103));
	}

	/**
	 * Rest Assured test for retrieving a list of curriculums by id's
	 * 
	 * @author Johne Vang (1802-Matt)
	 */
	@Test
	public void testGetCurriculums() {
		RestAssured.get("http://localhost:9001/api/v2/curricula/?ids=101&ids=102")
		.then()
		.assertThat()
		.body("[0].id", equalTo(101));
		
		RestAssured.get("http://localhost:9001/api/v2/curricula/?ids=101&ids=102")
		.then()
		.assertThat()
		.body("[1].id", equalTo(102));
		
		RestAssured.get("http://localhost:9001/api/v2/curricula/?ids=101&ids=102")
		.then()
		.assertThat()
		.body("[2].id", equalTo(null));
	}

	/**
	 * Rest Assured Test JSON root to retrieve all subtopics for a curriculum
	 * 
	 * @author Johne Vang (1802-Matt)
	 */
	@Test
	public void testGetAllCurriculumSubtopicIds() {
		
		RestAssured.get("http://localhost:9001/api/v2/curricula/100/subtopics")
		.then()
		.body("", hasItems(1000,1001,1002,1003));
		
	}
//
//	@Test
//	public void testMarkCurriculumAsMaster() {
//		
//	}

//	@Test
//	public void testAddCurriculum() {
//		
//	}
//
//	@Test
//	public void testDeleteSubtopics() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteCurriculums() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateCurriculum() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReplaceCurriculum() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInsertSubtopicsToCurriculum() {
//		fail("Not yet implemented");
//	}
}
