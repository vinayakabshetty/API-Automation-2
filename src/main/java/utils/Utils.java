package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification requestSpec;

	public static RequestSpecification requestSpecifications() throws IOException {
		if (requestSpec == null) {
			PrintStream fos = new PrintStream(new FileOutputStream(new File("./api_logs.txt")));
			requestSpec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseUrl"))
					.addQueryParam("key", "qaclick123").setContentType("application/json")
					.addFilter(RequestLoggingFilter.logRequestTo(fos))
					.addFilter(ResponseLoggingFilter.logResponseTo(fos)).build();
			return requestSpec;
		}
		return requestSpec;
	}
	
	public static String SimpleJsonParser_GetJsonValue(String responseAsString, String key) {
		JsonPath js = new JsonPath(responseAsString);
		String value = js.getString(key);
		return value;
	}
}
