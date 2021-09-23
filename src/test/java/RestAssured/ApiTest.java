package RestAssured;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiTest {
	
final Logger LOGGER = LogManager.getLogger(ApiTest.class.getSimpleName());
	
	@Before
	public void beforeTest() {
		
		PropertyConfigurator.configure("log4j.properties");
		//create base path
		RestAssured.baseURI="https://reqres.in";
		
	}
	
	@Test
	public void getRequest() {
		
        RequestSpecification request= RestAssured.given();
        LOGGER.info("Sending Get Request /api/users?page=2");
        request.get("/api/users?page=2").then().log().ifStatusCodeIsEqualTo(200);
        //get response values using String variable
        String respBody = request.get("/api/users?page=2").getBody().asString();
        System.out.println("Request Body = " + respBody);
        LOGGER.info("Successfully Viewed Request");
	}
	
	@Test
	public void PostRequest() {
		
		RequestSpecification request= RestAssured.given();
		
		LOGGER.info("Sending Post Request /api/users");
		String requestBody = " {\"name\": \"Arun\", \"job\": \"Team Leader\"} ";
		
		//setup and executing post request
		
		request.header("Content-Type", "Application/json")
					  .header("header2", "value2")
					  .header("header3", "value3")
					  .body(requestBody)
					  .log().all()
					  .post("/api/users").then().log().ifStatusCodeIsEqualTo(201);		
		LOGGER.info("Successfully added Values");
	}


}
