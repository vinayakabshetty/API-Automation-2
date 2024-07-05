package utils;

public enum Resources {

	AddPlaceAPI("/maps/api/place/add/json"), GetPlaceAPI("/maps/api/place/get/json"),
	UpdatePlaceAPI("/maps/api/place/update/json"), DeletePlaceAPI("/maps/api/place/delete/json");

	private String resource;

	Resources(String loadResource) {
		this.resource = loadResource;
	}

	public String getResource() {
		return resource;
	}
}