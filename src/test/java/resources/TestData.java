package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleMaps_AddPlace;
import pojo.GoogleMaps_DeletePlace;
import pojo.GoogleMaps_UpdatePlace;
import pojo.Location;

public class TestData {
	
	public GoogleMaps_DeletePlace deletePlaceDetails;
	public GoogleMaps_AddPlace addPlaceDetails;

	public GoogleMaps_AddPlace addPlacePayLoad() {
		
		Location location;

		// Body - Serialization concept
		addPlaceDetails = new GoogleMaps_AddPlace();
		location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlaceDetails.setLocation(location);
		addPlaceDetails.setAccuracy(50);
		addPlaceDetails.setName("Frontline house");
		addPlaceDetails.setPhone_number("(+91) 983 893 3937");
		addPlaceDetails.setAddress("29, side layout, cohen 09");

		List<String> types = new ArrayList<>();
		types.add("shoe park");
		types.add("shop");
		addPlaceDetails.setTypes(types);

		addPlaceDetails.setWebsite("http://google.com");
		addPlaceDetails.setLanguage("French-IN");

		return addPlaceDetails;
	}
	
	public GoogleMaps_UpdatePlace updatePlacePayLoad(String placeId, String updatedAddress) {
		// Body - Serialization concept
		GoogleMaps_UpdatePlace updatePlaceDetails = new GoogleMaps_UpdatePlace();
		updatePlaceDetails.setPlaceId(placeId);
		updatePlaceDetails.setAddress(updatedAddress);
		return updatePlaceDetails;
	}
	
	public GoogleMaps_DeletePlace deletePlacePayLoad(String placeId) {
		// Body - Serialization concept
		deletePlaceDetails = new GoogleMaps_DeletePlace();
		deletePlaceDetails.setPlaceId(placeId);
		return deletePlaceDetails;
	}
}
