package MainRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import DriverUtils.Config;
import DriverUtils.Log;

public class RunConfig {
	static String userInputs = "";
	Config config = new Config();

	public static void taskill() {
		try {
			Runtime.getRuntime().exec("TASKKILL /F /IM chromedriver.exe");
		} catch (Exception e1) {
			System.out.println("Task kill Exception occured");
		}
	}
	
	public static Properties loadProperties(Properties prop) {
		try {
			prop = new Properties();
			InputStream input = new FileInputStream(new File(
					System.getProperty("user.dir") + "\\" + Config.getConfigval("SmartLogName") + ".properties"));
			prop.load(input);
		} catch (Exception e) {}
		return prop;
	}
	
	public static void RunMode() {
		
			try {
				ConfigureAndRun.runTestCases(false, "NA");

			} catch (Exception e) {
				Log.addToLog("Exception occured while executing all test cases." + "\n" + e.getMessage());
			
		}
	}
}
