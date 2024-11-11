package com.epam.TestAutomation.Project.API.Utilities;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.testng.ITestContext;

import com.epam.TestAutomation.Project.API.POJOClasses.PatchPunchOutData;
import com.epam.TestAutomation.Project.API.POJOClasses.PostPunchInData;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HttpsMethods {
	private static ITestContext context=ITestContextClass.getContext();
	static String bearer=context.getCurrentXmlTest().getParameter("Bearer");
	static String cookie=context.getCurrentXmlTest().getParameter("Cookie");
	public static ArrayList<String> ids=new ArrayList();

	public static void postPunchInAndPunchOut(ITestContext context,String string, String string2,String s4) {
		// TODO Auto-generated method stub
		String bearer=context.getCurrentXmlTest().getParameter("Bearer");
		String cookie=context.getCurrentXmlTest().getParameter("Cookie");
		String empNumber="13";
		String timeZone="5.5";
		
		
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
		ids.add(id);
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

	public static String getTotalHoursOfAttendanceSheet(ITestContext context,String id) {
		// TODO Auto-generated method stub
		String bearer=context.getCurrentXmlTest().getParameter("Bearer");
		String cookie=context.getCurrentXmlTest().getParameter("Cookie");
		LinkedHashMap data=given().header("Authorization","Bearer "+bearer).and()
				.header("Cookie",cookie).and()
				.header("Content-Type","application/x-www-form-urlencoded")
				 .queryParam("id", id)
                .when()
                .get("/attendanceSheet")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().get("meta.totals.T");
		 String duration=(String)data.get("duration");
		return duration;
	}

	
	public static JsonPath PostPunchIn(PostPunchInData punchInData) {
		JsonPath jsonPathObject=given().header("Authorization","Bearer "+bearer)
		.and()
		.header("Cookie",cookie)
		.and()
		.header("Content-Type","application/json")
		.body(punchInData)
		.and()
        .when()
        .post("/attendanceRecords")
        .then()
        .statusCode(201)
        .extract()
        .jsonPath();
		
		return jsonPathObject;
	}
	public static JsonPath PatchPunchOut(PatchPunchOutData patchOutData,String id) {
		JsonPath jsonPathObject=given().header("Authorization","Bearer "+bearer).and()
		.header("Cookie",cookie).and()
		.header("Content-Type","application/json")
		.pathParam("id", id)
		.body(patchOutData)
		.and()
        .when()
        .patch("/attendanceRecord/{id}")
        .then()
        .statusCode(200)
        .extract()
        .jsonPath();
		
		return jsonPathObject;
		
	}
	public static Response PatchPunchOut(PatchPunchOutData patchOutData) {
		Response responseObj=given().header("Authorization","Bearer "+bearer).and()
		.header("Cookie",cookie).and()
		.header("Content-Type","application/json")
		.pathParam("id", context.getAttribute("idForPunchOut"))
		.body(patchOutData)
		.and()
        .when()
        .patch("/attendanceRecord/{id}")
        ;
		
		return responseObj;
		
	}
	public static Response GetResponseOfAttendanceSheet() {
		Response responseObject=given().header("Authorization","Bearer "+bearer).and()
		.header("Cookie",cookie).and()
		.header("Content-Type","application/x-www-form-urlencoded")
        .when()
        .get("/attendanceSheet")
        ;
		return responseObject;
	}

	public static void DeleteCreatedSessions() {
		for(String id:ids)
		{
		try {
		//String ids=(String) context.getAttribute("idForPunchOut");
		// TODO Auto-generated method stub
		String body="{\"ids\":[\""+id+"\"]}";
		
		given().header("Authorization","Bearer "+bearer)
		.and()
		.header("Cookie",cookie)
		.and()
		.header("Content-Type","application/json")
		.body(body)
		.and()
        .when()
        .delete("/attendanceRecords")
        .then()
        .extract()
        .jsonPath();
		}
		catch(Exception e) {}
		}
	}

	public static ArrayList<String> GetAttendanceSheets() {
		// TODO Auto-generated method stub
		
		ArrayList<String> responseObject=given().header("Authorization","Bearer "+bearer)
		.and()
		.header("Cookie",cookie)
		.and()
		.header("Content-Type","application/x-www-form-urlencoded")
		.queryParam("filter[empNumber]", "13")
		//.body("filter%5BempNumber%5D=13&orderBy=ASC&orderField=sheetPeriod")
		.and()
        .when()
        .get("/attendanceSheets")
        .then()
        .statusCode(200)
        .extract()
        .jsonPath()
        .get("meta.ids");
       
		return responseObject;
	}
	
	
}
