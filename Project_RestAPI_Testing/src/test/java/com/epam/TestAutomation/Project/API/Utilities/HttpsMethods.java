package com.epam.TestAutomation.Project.API.Utilities;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.util.LinkedHashMap;

import org.testng.ITestContext;

import io.restassured.response.Response;

public class HttpsMethods {

	public static void post(ITestContext context,String string, String string2,String s4) {
		// TODO Auto-generated method stub
		String bearer=context.getCurrentXmlTest().getParameter("Bearer");
		String cookie=context.getCurrentXmlTest().getParameter("Cookie");
		String empNumber="13";
		String timeZone="5.5";
		String date="2022-03-22";
		String time="00:00";
		String forcePunchIn="false";
		
		String body="{\"empNumber\":\""+empNumber+"\","
				+ "\"date\":\""+string+"\","
				+ "\"time\":\""+string2+"\","
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
		String id=(String)data.get("id");
		String body1="{\"empNumber\":\""+empNumber+"\","
				+ "\"date\":\""+string+"\","
				+ "\"time\":\""+s4+"\","
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
		//assertTrue(response.path("success"));
	}

	public static String getTotalHours(ITestContext context) {
		// TODO Auto-generated method stub
		String bearer=context.getCurrentXmlTest().getParameter("Bearer");
		String cookie=context.getCurrentXmlTest().getParameter("Cookie");
		LinkedHashMap data=given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/x-www-form-urlencoded")
				 .queryParam("id", "653")
                .when()
                .get("/attendanceSheet")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().get("meta.totals.T");
		 String duration=(String)data.get("duration");
		return duration;
	}

	

}
