package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//create for perform Create,Read,Update,Delete requests to the user API.
public class UserEndPoints 
{
	public static Response createUser(User payload)
	{
	Response respose=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
	
	return respose;
	}
	
	public static Response readUser(String userName)
	{
	Response respose=given()
			.pathParam("username",userName)
		
		.when()
		.post(Routes.get_url);
	
	return respose;
	}
	
	public static Response updateUser(String userName,User payload)
	{
	Response respose=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username",userName)
		.body(payload)
		
		.when()
		.put(Routes.update_url);
	
	return respose;
	}
	
	public static Response deleteUser(String userName)
	{
	Response respose=given()
			.pathParam("username",userName)
		
		.when()
		.delete(Routes.delete_url);
	
	return respose;
	}
}
