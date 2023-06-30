package com.sdet.boredapi;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GET_Request {
	
	Logger logger = LogManager.getLogger(GET_Request.class);
	
	@Test
	public void getSocialActivitiesFromBoredAPI() {
		logger.log(Level.INFO, "---------------------------------------------Reporting Logs------------------------------------------------------");
		RestAssured.baseURI = "https://www.boredapi.com/";
		for (int i = 1; i <= 3; i++) {
				Response response = given()
										.param("type", "social")
										.param("participants", 2)
									.when()
										.get("/api/activity");
				
				Assert.assertEquals(response.getStatusCode(), 200);
				
				String activity = response.jsonPath().get("activity").toString();
				
				String type = response.jsonPath().get("type").toString();
				if (type.equals("social")) {
					logger.log(Level.INFO, "Passed for the Activity: "+activity+ "."+" Expected Type: social and Actual Type: " + type);
				} else {
					logger.log(Level.INFO, "Failed for the Activity: "+activity+ "."+" Expected Type: social but then Actual Type: " + type);
				}
				Assert.assertEquals(type, "social");
				
				String participants = response.jsonPath().get("participants").toString();
				if (participants.equals("2")) {
					logger.log(Level.INFO, "Passed for the Activity: "+activity+ "."+" Expected Participants: 2 and Actual Participants: " + participants);
				} else {
					logger.log(Level.INFO, "Failed for the Activity: "+activity+ "."+" Expected Participants: 2 but then Actual Participants: " + participants);
				}
				Assert.assertEquals(participants, "2");
					
		}
	}
}