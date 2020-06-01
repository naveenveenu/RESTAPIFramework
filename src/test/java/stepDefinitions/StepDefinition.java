package stepDefinitions;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String placeID;
	
	@Given("Add Place Payload with {int} {string} {string} {string} {string} {string}")
	public void add_Place_Payload_with(int accuracy, String address, String language, String name, String phone_number, String website) throws IOException {
		//req and response specifications....
		res = given().spec(requestSpecification()).body(data.AddPlacePayLoad(accuracy, address, language, name, phone_number, website));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String apiName, String httpReqType) {
		// enum related code here...from utils. Constructor will be called with the value of apiName which you pass...
		APIResources resourceAPI = APIResources.valueOf(apiName);
		System.out.println(resourceAPI.getResource());
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (httpReqType.equalsIgnoreCase("POST")) 
			response= res.when().post(resourceAPI.getResource());
		else if(httpReqType.equalsIgnoreCase("GET"))
			response= res.when().get(resourceAPI.getResource());
		else if(httpReqType.equalsIgnoreCase("PUT"))
			response= res.when().put(resourceAPI.getResource());	
		
		System.out.println(response.asString());
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer code) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(200, response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	   // String resp = response.asString();
	    //JsonPath js = new JsonPath(resp);
	    String actualValue = getJsonPath(response, keyValue);
	    //String actualValue = js.getString(keyValue);
	    assertEquals(actualValue, expectedValue);
	    
	}
	
	@Then("verify place_id created matches to {string} using {string}")
	public void verify_place_id_created_matches_to_Naveen_using(String expectedName, String apiName) throws IOException {
		
		//prepare request spec.
		//successully constructed api
		placeID = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeID);
		
		//call the http request 
		user_calls_with_http_request(apiName, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		
	    res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeID));
	    System.out.println(res.toString());
	}
	
	
	
}































