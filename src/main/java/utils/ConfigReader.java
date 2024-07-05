package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop;

	public static String getProperty(String key) throws IOException {
		prop = readConfig(".\\src\\test\\resources\\config\\global.properties");
		String value = prop.getProperty(key);
		return value;
	}
	
	public static Properties readConfig(String filePath) throws IOException {
		Properties property_obj = new Properties();
		FileInputStream fis = new FileInputStream(new File(filePath));
		property_obj.load(fis);
		return property_obj;
	}
}