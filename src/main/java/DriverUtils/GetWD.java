package DriverUtils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.allEnums.DriverType;

public class GetWD {
	private static WebDriver wd;
	private static SmartWD smartWD;
	public static boolean isMobile;

	public GetWD(DriverType type) {
		String s=type.toString().toLowerCase();
		if(s.contains("mobile")) {
			isMobile = true;
		}
		this.wd = WebDriverSupplier.getDriver(type);
		// smartWD = new SmartWD(driver);
	}

	public WebDriver getDriver() {
		try {
			// wd = WebDriverSupplier.getDriver(type);
			EventFiringWebDriver eventDriver = new EventFiringWebDriver(wd);
			SmartWD webdriver = new SmartWD(wd);
			eventDriver.register(webdriver);
			if (!isMobile) {
				eventDriver.manage().window().maximize();
			}else {
				Dimension targetSize = new Dimension(400,800);
				eventDriver.manage().window().setSize(targetSize);
			}
			return eventDriver;

		} catch (Exception ex) {
			Log.addToLog("Unable to return Smart webdriver. Please check the setup file and SmartWD class");
			return null;
		}
	}

}
