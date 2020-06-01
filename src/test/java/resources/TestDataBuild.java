package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Locations;

public class TestDataBuild {

	public AddPlace AddPlacePayLoad(int accuracy, String address, String language, String name, String phone_number, String website) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(accuracy);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number(phone_number);
		ap.setWebsite(website);
		//==============		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		ap.setTypes(mylist);
		//==============		
		Locations l = new Locations();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		return ap;
	}
	
	public String deletePlacePayload(String placeID) {
		return "{\r\n    \"place_id\":\""+placeID+"\"\r\n}";
	}
}
