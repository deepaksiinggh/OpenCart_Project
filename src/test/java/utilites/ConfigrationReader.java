package utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigrationReader {

	public static String readDataFromConfig(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
		prop.load(fs);
		String data = prop.getProperty(key);
		return data;
		
	}
}
