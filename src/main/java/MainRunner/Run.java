package MainRunner;

import java.util.Properties;
import org.openqa.selenium.WebDriver;

import DriverUtils.Config;


public class Run {
	static Config config = new Config();
	static Properties prop;
	public static WebDriver wd;
	static String Environment="";


	public static void main(String[] args) {

		RunConfig.taskill();

		prop=RunConfig.loadProperties(prop);

		RunConfig.RunMode();
		RunConfig.taskill();
	}
}

