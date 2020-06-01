Feature: Validating place API

@AddPlace
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
Given Add Place Payload with <accuracy> "<address>" "<language>" "<name>" "<phone_number>" "<website>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created matches to "<name>" using "getPlaceAPI"

Examples:

	| accuracy | address   | language	|    name	  |	  phone_number	 |             website	            |	
	|	100    | Bangalore | English    |    Naveen   | (+91) 9989876546 | https://rahulshettyacademy.com   |
	#|	10     | Bangalore1| English1   | Testing API1| (+91) 9989876547 | https://rahulshettyacademy.co.in |
	

@DeletePlace	
Scenario: Verify if delete place api working status
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"