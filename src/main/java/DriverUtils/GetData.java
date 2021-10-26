package DriverUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class GetData 
{
	private static Properties prop;
	private static InputStream input;

	public GetData(String PropFileName) {
		try {
			prop = new Properties();
			input = new FileInputStream(System.getProperty("user.dir") + "\\" + PropFileName + ".properties");
			prop.load(input);
		} catch (Exception ex) {
			Log.addToLog("Unable to load the Config properties file." + "\n" + ex.toString());

		}

	}

	public String getData(String key) {
		try {
			return prop.getProperty(key);
		} catch (Exception ex) {
			Log.addToLog("Config key isn't present in the config file." + "\n" + ex.toString());
			return null;
		}
	}

}
