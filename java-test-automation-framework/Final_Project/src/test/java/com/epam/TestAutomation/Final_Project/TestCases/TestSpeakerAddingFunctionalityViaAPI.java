package com.epam.TestAutomation.Final_Project.TestCases;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.TestAutomation.Final_Project.PomPages.SpeakersPage;
import com.epam.TestAutomation.Final_Project.Utilities.SetUp;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.epam.TestAutomation.Final_Project.Utilities.*;

public class TestSpeakerAddingFunctionalityViaAPI {
	WebDriver driver;
	SpeakersPage speakersPage;
	int speakerID;
	Connection cn;
	String speakerName;
	String speakerEmail;
	String speakerProfile;
	@BeforeClass
	public void setUp(ITestContext context) throws MalformedURLException, InterruptedException, SQLException, ClassNotFoundException {
		driver=SetUp.setTillDashBoardPage(context);
		speakersPage=new SpeakersPage(driver);
		
		RestAssured.baseURI="http://epinhydw0087:9090/programs/speaker/name/";
		cn = DriverManager.getConnection("jdbc:mysql://EPINHYDW0087:3306/programs", "qa", "qa123");
		
		speakerName=context.getCurrentXmlTest().getParameter("SpeakerName");
		speakerEmail=context.getCurrentXmlTest().getParameter("SpeakerEmail");
		speakerProfile=context.getCurrentXmlTest().getParameter("SpeakerProfile");
		
		ExtentReportsClass.initExtentReports(context);
		context.setAttribute("WebDriver", driver);
	}
	
	@Test(priority=1)
	public void testAddedSpeakerIsDisplayedinUI() throws InterruptedException {
		speakersPage.clickOnAddSpeakerButton()
					.addDetails(speakerName,speakerEmail,speakerProfile)
					.clickOnSave();
		
		speakersPage.searchName(speakerName);
		
		boolean isDisplayed=speakersPage.isDataDisplayed(speakerName,speakerEmail,speakerProfile);
		assertTrue(isDisplayed);
	}
	
	@Test(priority=2)
	public void testGetResponseOfAddedSpeakerByAPI() {
		List list=given()
        	   .when()
               .get(speakerName)
               .then()
               .statusCode(200)
               .extract().response().jsonPath().getList("$");
		LinkedHashMap data=(LinkedHashMap) list.get(0);
		System.out.println(data.get("speakerId"));
		speakerID=(int) data.get("speakerId");
	}
	
	
	@Test(priority=3)
	public void testSpeakerDetailsAddedReflectedInDataBase() throws SQLException {
		String speakerNameDataBase="";
		String speakerEmailDataBase="";
		String speakerProfileDataBase="";
		Statement st=cn.createStatement();
		ResultSet rs=st.executeQuery("SELECT*FROM speaker_info WHERE id="+speakerID+";");
		while(rs.next()) {
			
			speakerNameDataBase=rs.getString(2);
			speakerEmailDataBase=rs.getString(5);
			speakerProfileDataBase=rs.getString(3);
		}
		System.out.println(speakerEmailDataBase);
		assertTrue(speakerNameDataBase.equals(speakerName));
		assertTrue(speakerEmailDataBase.equals(speakerEmail));
		assertTrue(speakerProfileDataBase.equals(speakerProfile));
	}
	
	
	@AfterClass
	public void close() {
		ExtentReportsClass.endReport();
		driver.quit();
	}
}
