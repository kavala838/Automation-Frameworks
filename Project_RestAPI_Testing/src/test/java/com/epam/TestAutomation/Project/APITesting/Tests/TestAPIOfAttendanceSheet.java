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

import com.epam.TestAutomation.Project.API.POJOClasses.PatchPunchOutData;
import com.epam.TestAutomation.Project.API.POJOClasses.PostPunchInData;
import com.epam.TestAutomation.Project.API.Utilities.HttpsMethods;
import com.epam.TestAutomation.Project.API.Utilities.ITestContextClass;
import com.epam.TestAutomation.Project.API.Utilities.ParseTime;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
	
	@Test(priority=1,enabled=true)
	public void TestGetResponseOfAttendanceSheet(ITestContext context) {
		ITestContextClass.setContext(context);
		Response Getresponse=HttpsMethods.GetResponseOfAttendanceSheet();
		Getresponse.then().statusCode(200);
		
		LinkedHashMap DataOfResponse=Getresponse.then().extract().jsonPath().get("data");
		 SheetId=(String)DataOfResponse.get("latestSheetId");
		 context.setAttribute("latestSheetId", SheetId);
	}
	
	@Test(priority=2,enabled=true)
	public void TestPostResponseOfPunchIn(ITestContext context) {
		
		//This can provided from a data provider
		PostPunchInData punchIn=new PostPunchInData();
		punchIn.setDate("2022-03-22");
		punchIn.setEmpNumber("13");
		punchIn.setTime("00:00");
		punchIn.setTimezoneOffset("5.5");
		punchIn.setforcePunchIn(false);
		
		
		JsonPath JsonResponse=HttpsMethods.PostPunchIn(punchIn);
		assertTrue(JsonResponse.get("success"));
		
		
		LinkedHashMap DataOfResponse=JsonResponse.get("data");
		String id=(String) DataOfResponse.get("id");
		context.setAttribute("idForPunchOut", id);
	}
	
	
	@Test(priority=3,enabled=true)
	public void TestPostResponseOfValidPunchOut(ITestContext context) {
		
		//Can Be passed From A dataProvider
		PatchPunchOutData punchOut=new PatchPunchOutData();
		punchOut.setDate("2022-03-22");
		punchOut.setEmpNumber("13");
		punchOut.setTime("01:00");
		punchOut.setTimezoneOffset("5.5");
		
		
		JsonPath JsonResponse=HttpsMethods
				.PatchPunchOut(punchOut,(String)context.getAttribute("idForPunchOut"));
		assertTrue(JsonResponse.get("success"));
		
		
	}
	
	//NegativeTest
	@Test(priority=4,enabled=true)
	public void TestPostResponseOfPunchInOverlapping(ITestContext context) {
		
		PostPunchInData punchIn=new PostPunchInData();
		punchIn.setDate("2022-03-22");
		punchIn.setEmpNumber("13");
		punchIn.setTime("00:30");
		punchIn.setTimezoneOffset("5.5");
		punchIn.setforcePunchIn(false);
		
		JsonPath JsonResponse=HttpsMethods.PostPunchIn(punchIn);
		
		
		LinkedHashMap DataOfResponse=JsonResponse.get("messages");
		assertEquals("Overlapping Records Found",(String)DataOfResponse.get("error"));
		 
	}
	
	
	@Test(priority=5,enabled=true)
	
	public void TestPatchtResponsePunchOutAgainAfterPunchOut(ITestContext context) {
		PatchPunchOutData punchOut=new PatchPunchOutData();
		punchOut.setDate("2022-03-22");
		punchOut.setEmpNumber("13");
		punchOut.setTime("11:00");
		punchOut.setTimezoneOffset("5.5");
		Response response=HttpsMethods.PatchPunchOut(punchOut);
		
		response.then().statusCode(403);
		
		
	}
	
	
	
	@Test(priority=6,enabled=true)
	public void TestPatchtResponsePunchOutBeforePunchIn(ITestContext context) {
		
		PostPunchInData punchIn=new PostPunchInData();
		punchIn.setDate("2022-03-29");
		punchIn.setEmpNumber("13");
		punchIn.setTime("10:00");
		punchIn.setTimezoneOffset("5.5");
		punchIn.setforcePunchIn(false);
		
		
		JsonPath JsonResponse=HttpsMethods.PostPunchIn(punchIn);
		
		LinkedHashMap DataOfResponse=JsonResponse.get("data");
		String id=(String) DataOfResponse.get("id");
		context.setAttribute("idForPunchOut", id);
		
		
		 
		PatchPunchOutData punchOut=new PatchPunchOutData();
		punchOut.setDate("2022-03-29");
		punchOut.setEmpNumber("13");
		punchOut.setTime("09:30");
		punchOut.setTimezoneOffset("5.5");
		
		
		JsonResponse=HttpsMethods
				.PatchPunchOut(punchOut,(String)context.getAttribute("idForPunchOut"));
		DataOfResponse=JsonResponse.get("messages");
		assertEquals("Failed to Save",(String)DataOfResponse.get("error"));
		
		
	}
	@Test(priority=7,enabled=true)
	public void TestTotalHoursAggregation(ITestContext context) {
		
		String InTime="10:00";
		String OutTime="12:00";
		
		
		String duration=HttpsMethods.getTotalHoursOfAttendanceSheet(context,"653");
		System.out.println(duration);
		int TotalTimePreSession=ParseTime.convertIntoMinutes(duration);
	
		
		
		HttpsMethods.postPunchInAndPunchOut(context,"2022-03-10",InTime,OutTime);
		int SessionDuration=ParseTime.getDuration(InTime,OutTime);
	    
		String durationAfterNewSession=HttpsMethods.getTotalHoursOfAttendanceSheet(context,"653");
		System.out.println(durationAfterNewSession);
		int TotalTimePostSession=ParseTime.convertIntoMinutes(durationAfterNewSession);
		
		assert(TotalTimePreSession+SessionDuration==TotalTimePostSession);
	}
	
	@AfterMethod
	public void closeTab() {
		try {
		HttpsMethods.DeleteCreatedSessions();
		}
		catch(Exception e) {}
	}
	
}
