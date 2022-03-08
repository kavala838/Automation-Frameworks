package com.epam.TestAutomation.API_Assignment;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class TestAPI {
	Map<Integer,String> TokenList;
	Map<Integer,String> IdList;
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="http://restapi.adequateshop.com";
		TokenList=new HashMap<Integer,String>();
		IdList=new HashMap<Integer,String>();
	}
	
	// 1.Testing Registration
	@Test(dataProvider="NewData")
	
	public void testRegistration(String name, String email, String password) {
		String body = "{\r\n" + "\r\n" 
	            + "            \"name\":\"" + name + "\",\r\n" 
				+ "            \"email\":\""+ email + "\",\r\n"
	            + "            \"password\":" + password + "\r\n" + "}";
	  //System.out.println(body); 
	  String msg=given().header("Content-Type","Application/json")  
			            .body(body)
			            .and()
			            .when() 
			            .post("/api/authaccount/registration") 
			            .then() 
			            .statusCode(200)
			            .extract()
			            .jsonPath()
			            .get("message"); 
	  assertEquals(msg,"success"); 
	  }
	 
	
	
	
	// 2.Testing LogIn
	@Test(dataProvider="NewData",dependsOnMethods="testRegistration") ///
	public void testLogIn(String name,String email,String password) {
		String body="{\r\n"
				+ "\r\n"
				+ "            \"email\":\""+email+"\",\r\n"
				+ "            \"password\":"+password+"\r\n"
				+ "}";
		//System.out.println(body);
		Response response=given().header("Content-Type","Application/json")
		   .body(body)
		   .and()
		   .when()
		   .post("/api/authaccount/login")
		   .then()
		   .statusCode(200)
		   .extract()
		   .response();
		//assertEquals(msg,"success");
		System.out.println(response);
		assertEquals("success",response.path("message"));
		int id=response.path("data.Id");
		String token=response.path("data.Token");
		IdList.put(id, name);
		TokenList.put(id, token);
		//System.out.println("login");
	}
	
	
	
	
	 // 3.Testing to get All Users of Different Pages for different Tokens.
	  @Test(dataProvider="DataAfterLogIn",dependsOnMethods="testLogIn") 
	  
	  public void testGetUsersRequest(int id,String authorization) {
		  
		  for(int i=1;i<=10;i++)
		  {
		  Response response=given().header("Authorization","Bearer "+authorization)
				                   .queryParam("page", i)
				                   .when()
				                   .get("/api/users")
				                   .then()
				                   .statusCode(200)
				                   .extract()
				                   .response();
		  assertEquals(i,response.path("page"));
		  }
		 // System.out.println("users");
	  }
	  
	  
	 
	  // 4.Testing to get a user with Id.
	  @Test(dataProvider="DataAfterLogIn",dependsOnMethods="testLogIn") 
	  
	  public void testGetUserDetailsRequestWithUserId(int id,String authorization) {
		  Response response=given().header("Authorization","Bearer "+authorization)
                  .pathParam("id", id)
                  .when()
                  .get("/api/users/{id}")
                  .then()
                  .statusCode(200)
                  .extract()
                  .response();
		  assertEquals(id,response.path("id"));
	  }
	  
	  
	  
	  
	  
	  // 5.Testing to login  with UnRegistered Details
	  @Test(dataProvider="InvalidUserNamesAndPasswords")
	  public void testLogInForUnregisteredUsers(String email,String password) {
		  String body="{\r\n"
					+ "\r\n"
					+ "            \"email\":\""+email+"\",\r\n"
					+ "            \"password\":"+password+"\r\n"
					+ "}";
			//System.out.println(body);
			Response response=given().header("Content-Type","Application/json")
			   .body(body)
			   .and()
			   .when()
			   .post("/api/authaccount/login")
			   .then()
			   .statusCode(200)
			   .extract()
			   .response();
			assertEquals("invalid username or password",response.path("message"));
		  
	  }
	  
	  
	  
	  
	  // 6. Testing to log in with valid userName but with wrong password
	  @Test(dataProvider="NewData")
	  public void testLogInWithValidUserNameAndWrongPassword(String name,String email,String password) {
		  password+="000";
		  String body="{\r\n"
					+ "\r\n"
					+ "            \"email\":\""+email+"\",\r\n"
					+ "            \"password\":"+password+"\r\n"
					+ "}";
			//System.out.println(body);
			Response response=given().header("Content-Type","Application/json")
			   .body(body)
			   .and()
			   .when()
			   .post("/api/authaccount/login")
			   .then()
			   .statusCode(200)
			   .extract()
			   .response();
			assertEquals("invalid username or password",response.path("message"));
	  }
	  
	  
	  
	  // 7.Testing to get users page without Token
	  @Test
	  public void testGetUsersPageWithoutToken() {
		  for(int i=1;i<=10;i++)
		  {
		  given().queryParam("page", i)
				                   .when()
				                   .get("/api/users")
				                   .then()
				                   .statusCode(401);  // 401->Unauthorized
				                   
		  }
	  }
	  
	  
	  
	  
	  // 8.Testing to get user by Id without Token
	  @Test
	  public void testFetUserWithIdWithoutToken() {
		  for(int i=1;i<=10;i++) {
			  given()
              .pathParam("id", i)
              .when()
              .get("/api/users/{id}")
              .then()
              .statusCode(401);
		  }
	  }
	  
	  
	  
	  // 9.Testing to get user by Id with an Invalid Token
	  @Test(dataProvider="InvalidTokens")
	  public void testGetResponseWithInvalidToken(String token) {
		  String msg=given().header("Authorization","Bearer "+token)
          .pathParam("id", 1)
          .when()
          .get("/api/users/{id}")
          .then()
          .statusCode(401)
          .extract()
          .asString();
		 // System.out.println(msg);
		  assertEquals("\"Inavlid Token\"",msg);
	  }
	  
	  
	  
	  // 10.Testing to get users page with an Invalid Token
	  @Test(dataProvider="InvalidTokens")
	  public void testGetUsersPageInvalidToken(String token) {
		  String msg=given().header("Authorization","Bearer "+token)
				  .queryParam("page", 1)
                  .when()
                  .get("/api/users")
                  .then()
                  .statusCode(401)
                  .extract()
                  .asString();
		  assertEquals("\"Inavlid Token\"",msg);
	  }
	  
	  
	@DataProvider(name="NewData")
	public Object[][] NewDataForregistration()	{
		Object[][] returnObj= {
				{"name1","Gszxyah11111@gmail.com","123456"},
				{"name2","Gszxyah21111@gmail.com","123456"},
				{"name3","Gszxyah31111@gmail.com","123457"},
				{"name4","Gszxyah41111@gmail.com","123456"},
				{"name5","Gszxyah51111@gmail.com","123456"}
		};
		return returnObj;
	}
	
	
	//@dependsOnMethods="testLogIn"
	@DataProvider(name="DataAfterLogIn")
	public Object[][] DataAfterLogIn(){
		Object[][] returnObj=new Object[TokenList.size()][2];
		int i=0;
		for(Map.Entry<Integer, String> e:TokenList.entrySet())
		{
			returnObj[i][0]=  e.getKey();
			returnObj[i][1]=e.getValue();
			i++;
		}
		return returnObj;
	}
	
	
	@DataProvider(name="InvalidUserNamesAndPasswords")
	public Object[][] InvalidUserNamesAndPasswords(){
		Object[][] returnObj= {
				{"FakeEmail1@fake.com","1234567"},
				{"FakeEmail2@fake.com","1234567"},
				{"FakeEmail3@fake.com","1234567"},
				{"FakeEmail4@fake.com","1234567"},
				{"FakeEmail5@fake.com","1234567"},
		};
		return returnObj;
	}
	
	
	@DataProvider(name="InvalidTokens")
	public Object[][] InvalidTokens(){
		Object[][] returnObj= {
				{"c9c1347c-698a-ab93-9504-43726nhf70c4"},	
				{"c9c1247c-698a-cd93-9504-43726adf70c4"},
				{"c9c1347c-698a-ef93-9504-43726dwf70c4"},
				{"c9c1647c-698a-gh93-9504-43726tyf70c4"},
				{"c9c1177c-698a-ij93-9504-43726zuf70c4"}
		};
		return returnObj;
	}
}
