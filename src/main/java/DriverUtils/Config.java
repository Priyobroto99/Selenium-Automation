package DriverUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static Properties prop;
	private static InputStream input;

	public Config() {
		try {
			prop = new Properties();
			input = new FileInputStream(System.getProperty("user.dir") + "\\" + "Config.properties");
			prop.load(input);
		} catch (Exception ex) {
			Log.addToLog("Unable to load the Config properties file." + "\n" + ex.toString());

		}

	}

	public static String getConfigval(String key) {
		try {
			return prop.getProperty(key).trim();
		} catch (Exception ex) {
			Log.addToLog("Config key isn't present in the config file." + "\n" + ex.toString());
			return null;
		}
	}
}
