package resources;
//enum is a special class in java which has collection of special constants and methods..
public enum APIResources {
		
	AddPlaceAPI("/maps/api/place/add/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	getPlaceAPI("/maps/api/place/get/json");
	
	private String resource;
	
	//constructor
	APIResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
	
}
