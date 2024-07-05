@GoogleMapsAPI
Feature: Google maps api
  I want to use this verify google maps api

  @AddPlaceToMap
  Scenario: Add place to google map through api request
    Given add place to google maps api payload
    When user calls "AddPlaceAPI" with "POST" http request
    Then the api request is successfull with status code as 200
    And "status" in the response body is "OK"
    And "scope" in the response body is "APP"

  @GetPlaceDetailsInMap
  Scenario: Get place details in google map through api request
    Given place id of a place in google maps api is known and added to api request header
    When user calls "GetPlaceAPI" with "GET" http request
    Then the api request is successfull with status code as 200
    
  @UpdatePlaceAddressInMap
  Scenario: Update place details in google map through api request
    Given place id of a place in google maps api is known and added to api request header
    And update place to maps api payload
    When user calls "UpdatePlaceAPI" with "PUT" http request
    Then the api request is successfull with status code as 200
    
  @DeletePlaceInMap
  Scenario: Delete place details in google map through api request
    Given delete place in maps api payload
    When user calls "DeletePlaceAPI" with "DELETE" http request
    Then the api request is successfull with status code as 200