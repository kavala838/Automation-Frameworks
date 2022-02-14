package com.epam.TestAutomation.RestAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestResponseByObject {
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
	}
	@Test(dataProvider="ObjectsData")
	public void testPostObjectPostResponse(PostObject obj) {
		given().header("Content-Type","Application/json")
		   .body(obj)
		   .and()
		   .when()
		   .post("/posts")
		   .then()
		   .statusCode(201);
	}
	@Test(dataProvider="IdData")
	public void testGetResponseById(int id) {
		PostObjectWithId obj=given().pathParam("id", id)
			.when()
			.get("/posts/{id}")
			.as(PostObjectWithId.class);
		assertEquals(id,obj.getId());
	}
	@Test
	public void tesrGetResponseAllPosts() {
		PostObjectWithId[] objArr=given()
				.when()
				.get("/posts")
				.as(PostObjectWithId[].class);
		assert(objArr.length==100);
	}
	@Test
	public void testGetResponseOfComments() {
		UserDetailsObject[] objArr=given().queryParam("postId", 1)
				.when()
				.get("/comments")
				.as(UserDetailsObject[].class);
		for(int i=0;i<objArr.length;i++) {
			assertEquals(1,objArr[i].getPostId());
		}
	}
	@DataProvider(name="ObjectsData")
	public Object[][] objData(){
		PostObject obj1=new PostObject();
		PostObject obj2=new PostObject();
		PostObject obj3=new PostObject();
		PostObject obj4=new PostObject();
		PostObject obj5=new PostObject();
		obj1.setUserId(5);  obj1.setTitle("title1");  obj1.setBody("body1");
		obj2.setUserId(1);  obj2.setTitle("title2");  obj2.setBody("body1");
		obj3.setUserId(2);  obj3.setTitle("title3");  obj3.setBody("body1");
		obj4.setUserId(3);  obj4.setTitle("title4");  obj4.setBody("body1");
		obj5.setUserId(4);  obj5.setTitle("title5");  obj5.setBody("body1");
		Object[][] returnObj= {
				{obj1},
				{obj2},
				{obj3},
				{obj4},
				{obj5}
		};
		return returnObj;
	}
	@DataProvider(name="IdData")
	public Object[][] IdData(){
		Object[][] returnObj= {
				{1},{2},{3},{4},{5}
		};
		return returnObj;
	}
}
