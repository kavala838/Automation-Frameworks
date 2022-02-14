package com.epam.TestAutomation.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPost {
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
	}
	@Test
	public static void testPostObjectGetResponse() {
		given().when().get("/posts").then().statusCode(200);

	}

	@Test
	public static void testPostObjectGetResponseWithID() {
		given().pathParam("id", 1).when().get("/posts/{id}").then().statusCode(200)
				.body("userId", equalTo(1))
				.body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));

	}

	@Test(dataProvider = "userIdDataProvider")

	public static void testPostObjectGetResponseQueryParam(int UserId) {
		List<Object> posts = given().queryParam("userId", UserId).when()
				.get("/posts").then().statusCode(200).extract().jsonPath()
				.getList("$");

		assert (posts.size() == 10);
	}

	@DataProvider(name = "userIdDataProvider")
	public static Object[][] userDataProvider() {
		Object[][] returnObj = { { 1 }, { 2 }, { 3 }, { 4 }, { 5 } };
		return returnObj;
	}
	
	
	@Test
	public void testPostObjectPostResponse() {
		String body="{\r\n"
				+ "    \"userId\":8,\r\n"
				+ "    \"title\":\"THis is Title\",\r\n"
				+ "    \"body\":\"This is a comment\"\r\n"
				+ "}";
		given().header("Content-Type","Application/json")
			   .body(body)
			   .and()
			   .when()
			   .post("/posts")
			   .then()
			   .statusCode(201);
	}
	
	@Test
	public void testPostObjectPutResponse() {
		String body="{\r\n"
				+ "    \"userId\":10,\r\n"
				+ "    \"title\":\"THis is Modified Title\",\r\n"
				+ "    \"body\":\"This is a Modified comment\"\r\n"
				+ "}";
		given().header("Content-Type","Application/json")
		   .body(body)
		   .and()
		   .when()
		   .put("/posts/1")
		   .then()
		   .statusCode(200)
		   .body("userId",equalTo(10));
		
	}
	
	@Test
	public void testPostObjectPatchResponse() {
		String body="{\r\n"
				+ "    \r\n"
				+ "    \"body\":\"This is a comment\"\r\n"
				+ "}";
		given().header("Content-Type","Application/json")
		   .body(body)
		   .and()
		   .when()
		   .patch("/posts/1")
		   .then()
		   .statusCode(200)
		   .body("body",equalTo("This is a comment"));
	}
}
