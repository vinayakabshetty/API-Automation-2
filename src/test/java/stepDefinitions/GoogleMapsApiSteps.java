package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleMaps_AddPlace;
import pojo.GoogleMaps_DeletePlace;
import pojo.GoogleMaps_UpdatePlace;
import resources.TestData;
import utils.Resources;
import utils.Utils;

public class GoogleMapsApiSteps {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;
	TestData data;
	GoogleMaps_AddPlace addPlacePayLoad;
	GoogleMaps_UpdatePlace updatePlacePayLoad;
	GoogleMaps_DeletePlace deletePlacePayLoad;

	@Given("add place to google maps api payload")
	public void add_place_to_google_maps_api_payload() throws IOException {
		// Get test data
		data = new TestData();
		addPlacePayLoad = data.addPlacePayLoad();

		// Add, Get, Delete place - Request spec builders
		requestSpec = Utils.requestSpecifications();
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String apiRequest, String httpRequest) {
		String resourceURL = Resources.valueOf(apiRequest).getResource();
		response = given().log().all().spec(requestSpec).body(data.addPlacePayLoad()).when().log().all()
				.post(resourceURL);
	}

	@Then("the api request is successfull with status code as {int}")
	public void the_api_request_is_successfull_with_status_code_as(int statusCode) {
		// Response - Response spec builder
		responseSpec = new ResponseSpecBuilder().expectStatusCode(statusCode).build();
		response.then().log().all().spec(responseSpec);
	}

	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String jsonPathkey, String expectedValue) {
		String responseString = response.then().extract().asString();
		String actualValue = Utils.SimpleJsonParser_GetJsonValue(responseString, jsonPathkey);
		Assert.assertTrue(expectedValue.equals(actualValue));
	}

	@Given("place id of a place in google maps api is known and added to api request header")
	public void place_id_of_a_place_in_google_maps_api_is_known_and_added_to_api_request_header() throws IOException {
		String addPlaceResourceURL = Resources.valueOf("AddPlaceAPI").getResource();
		data = new TestData();
		String addPlaceResponse = given().log().all().spec(Utils.requestSpecifications()).body(data.addPlacePayLoad())
				.when().log().all().post(addPlaceResourceURL).then().log().all().extract().asString();
		String placeId = Utils.SimpleJsonParser_GetJsonValue(addPlaceResponse, "place_id");

		requestSpec = Utils.requestSpecifications();
		requestSpec.spec(Utils.requestSpecifications()).queryParam("placeId", placeId);
	}

	@Given("update place to maps api payload")
	public void update_place_to_maps_api_payload() throws IOException {
		String addPlaceResourceURL = Resources.valueOf("AddPlaceAPI").getResource();
		String addPlaceResponse = given().log().all().spec(Utils.requestSpecifications()).body(data.addPlacePayLoad())
				.when().log().all().post(addPlaceResourceURL).then().log().all().extract().asString();
		String placeId = Utils.SimpleJsonParser_GetJsonValue(addPlaceResponse, "place_id");

		data = new TestData();
		data.updatePlacePayLoad(placeId, "Test123");
		
		// Add, Get, Delete place - Request spec builders
		requestSpec = Utils.requestSpecifications().body(data);
	}
	
	@Given("delete place in maps api payload")
	public void delete_place_in_maps_api_payload() throws IOException {String addPlaceResourceURL = Resources.valueOf("AddPlaceAPI").getResource();
	data = new TestData();
	String addPlaceResponse = given().log().all().spec(Utils.requestSpecifications()).body(data.addPlacePayLoad())
			.when().log().all().post(addPlaceResourceURL).then().log().all().extract().asString();
	String placeId = Utils.SimpleJsonParser_GetJsonValue(addPlaceResponse, "place_id");

	data.deletePlacePayLoad(placeId);
	
	// Add, Get, Delete place - Request spec builders
	requestSpec = Utils.requestSpecifications().body(data.deletePlaceDetails);
	}

}
