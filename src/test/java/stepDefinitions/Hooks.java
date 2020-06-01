package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//Create code to run add place and extract place_id for deletePlace api.
		//mechanism to run this code only when placeid is null
		
		StepDefinition sd = new StepDefinition();
		if (StepDefinition.placeID == null){
			sd.add_Place_Payload_with(100, "asia", "english", "naveen", "997766554433", "www.rahulshettyacademy.com");
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_matches_to_Naveen_using("naveen", "getPlaceAPI");
		
		}
	}
}
