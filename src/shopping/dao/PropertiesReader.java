package shopping.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	public PropertiesReader() {

	}

	public static void main(String[] args) {
		System.out.println(getInfo("driver"));
	}

	public static String getInfo(String key) {
		//		String filename = "src/shopping/dao/common.properties";
		String filename = "/WEB-INF/common.properties";

		Properties properties = new Properties();
		try {
			FileInputStream file = new FileInputStream(filename);
			properties.load(file);
		} catch (IOException e) {
			System.err.println("Cannot open " + filename + ".");
			e.printStackTrace();
			System.exit(-1);
		}
		return properties.getProperty(key);
	}
}