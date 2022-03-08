package com.epam.TestAutomation.Project.APITesting.Tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.TestAutomation.Project.API.Utilities.HttpsMethods;
import com.epam.TestAutomation.Project.API.Utilities.ParseTime;

import io.restassured.RestAssured;
import io.restassured.response.Response;



public class TestAPIOfAttendanceSheet {
	
	String bearer;
	String cookie;
	String SheetId;
	@BeforeClass
	public void setUp(ITestContext context) throws MalformedURLException {
		RestAssured.baseURI="https://prasoonr-trials73.orangehrmlive.com/api";
		bearer=(String)context.getCurrentXmlTest().getParameter("Bearer");
		cookie=(String)context.getCurrentXmlTest().getParameter("Cookie");
		
	}
	
	@Test(priority=1,enabled=false)
	public void TestGetResponseOfAttendanceSheet(ITestContext context) {
		LinkedHashMap data=given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/x-www-form-urlencoded")
                .when()
                .get("/attendanceSheet")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().get("data");
		 System.out.println(data.get("latestSheetId"));
		 SheetId=(String)data.get("latestSheetId");
	}
	
	@Test(priority=2,enabled=false)
	public void TestPostResponseOfPunchIn(ITestContext context) {
		String empNumber="13";
		String timeZone="5.5";
		String date="2022-03-22";
		String time="00:00";
		String forcePunchIn="false";
		
		String body="{\"empNumber\":\""+empNumber+"\","
				+ "\"date\":\""+date+"\","
				+ "\"time\":\""+time+"\","
				+ "\"timezoneOffset\":\""+timeZone+"\","
				+ "\"forcePunchIn\":false}";
		
		LinkedHashMap data=given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/json")
				.body(body)
				.and()
                .when()
                .post("/attendanceRecords")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().get("data");
		// System.out.println(data.get("latestSheetId"));
		System.out.println((String)data.get("id"));
		 String id=(String)data.get("id");
		 context.setAttribute("id", id);
	}
	
	
	@Test(priority=3,enabled=false)
	public void TestPostResponseOfValidPunchOut(ITestContext context) {
		String empNumber="13";
		String timeZone="5.5";
		String date="2022-03-22";
		String time="23:40";
		String id=(String) context.getAttribute("id");
		
		String body1="{\"empNumber\":\""+empNumber+"\","
				+ "\"date\":\""+date+"\","
				+ "\"time\":\""+time+"\","
				+ "\"timezoneOffset\":\""+timeZone+"\"}";
		
		Response response=given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/json")
				.pathParam("id", id)
				.body(body1)
				.and()
                .when()
                .patch("/attendanceRecord/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response();
		assertTrue(response.path("success"));
		// System.out.println(data.get("latestSheetId"));
		 //SheetId=(String)data.get("latestSheetId");
	}
	
	//NegativeTest
	@Test(priority=4,enabled=false)
	public void TestPostResponseOfPunchInOverlapping(ITestContext context) {
		String empNumber="13";
		String timeZone="5.5";
		String date="2022-03-22";
		String time="10:00";
		
		String body="{\"empNumber\":\""+empNumber+"\","
				+ "\"date\":\""+date+"\","
				+ "\"time\":\""+time+"\","
				+ "\"timezoneOffset\":\""+timeZone+"\","
				+ "\"forcePunchIn\":false}";
		
		
		LinkedHashMap data=given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/json")
				.body(body)
				.and()
                .when()
                .post("/attendanceRecords")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().get("messages");
		System.out.println(data.get("error"));
		assertEquals("Overlapping Records Found",(String)data.get("error"));
		// System.out.println(data.get("latestSheetId"));
		 //SheetId=(String)data.get("latestSheetId");
	}
	
	
	@Test(priority=5,enabled=false)
	public void TestPatchtResponseOfInValidPunchOut(ITestContext context) {
		String empNumber="13";
		String timeZone="5.5";
		String date="2022-03-22";
		String time="11:00";
		String id=(String) context.getAttribute("id");
		
		String body1="{\"empNumber\":\""+empNumber+"\","
				+ "\"date\":\""+date+"\","
				+ "\"time\":\""+time+"\","
				+ "\"timezoneOffset\":\""+timeZone+"\"}";
		
		given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/json")
				.pathParam("id", id)
				.body(body1)
				.and()
                .when()
                .patch("/attendanceRecord/{id}")
                .then()
                .statusCode(403);
                
		//assertTrue(response.path("success"));
		// System.out.println(data.get("latestSheetId"));
		 //SheetId=(String)data.get("latestSheetId");
	}
	//PunchOut Between Time of valid PunchIn And PunchOut ---403
	//PunchOut After valid in/OUT --??
	
	
	@Test(priority=6,enabled=false)
	public void TestPatchtResponsePunchOutBeforePunchIn(ITestContext context) {
		String empNumber="13";
		String timeZone="5.5";
		String date="2022-03-29";
		String time="10:00";
		String forcePunchIn="false";
		
		String body="{\"empNumber\":\""+empNumber+"\","
				+ "\"date\":\""+date+"\","
				+ "\"time\":\""+time+"\","
				+ "\"timezoneOffset\":\""+timeZone+"\","
				+ "\"forcePunchIn\":false}";
		
		LinkedHashMap data=given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/json")
				.body(body)
				.and()
                .when()
                .post("/attendanceRecords")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().get("data");
		
		empNumber="13";
		timeZone="5.5";
		date="2022-03-29";
		time="09:30";
		String id=(String)data.get("id");
		
		String body1="{\"empNumber\":\""+empNumber+"\","
				+ "\"date\":\""+date+"\","
				+ "\"time\":\""+time+"\","
				+ "\"timezoneOffset\":\""+timeZone+"\"}";
		
		data=given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/json")
				.pathParam("id", id)
				.body(body1)
				.and()
                .when()
                .patch("/attendanceRecord/{id}")
                .then()
                .statusCode(200)
		        .extract()
		        .jsonPath().get("messages");
				System.out.println(data.get("error"));
				assertEquals("Failed to Save",(String)data.get("error"));
	}
	@Test
	public void TestTotalHoursAggregation(ITestContext context) {
		String InTime="10:00";
		String OutTime="12:00";
		
		
		String duration=HttpsMethods.getTotalHours(context);
		System.out.println(duration);
		int TotalTimePreSession=ParseTime.convertIntoMinutes("duration");
	
		
		
		HttpsMethods.post(context,"2022-03-10",InTime,OutTime);
		int SessionDuration=ParseTime.getDuration(InTime,OutTime);
	    
		String durationAfterNewSession=HttpsMethods.getTotalHours(context);
		System.out.println(durationAfterNewSession);
		int TotalTimePostSession=ParseTime.convertIntoMinutes("durationAfterNewSession");
		
		assert(TotalTimePreSession+SessionDuration==TotalTimePostSession);
	}
	
	@AfterClass
	public void closeTab() {

	}
	//Comment
}
