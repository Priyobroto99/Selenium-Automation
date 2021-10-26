package DriverUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.allEnums.DriverType;


public class WebDriverSupplier {
	private static final Map<DriverType, Supplier<WebDriver>> driverMap = new HashMap<>();
	private static ChromeOptions option;
	private static DesiredCapabilities caps;
	
	public WebDriverSupplier() {

	}

	// chrome driver supplier
	private static final Supplier<WebDriver> chromeDriverSupplier = () -> {		
		  
		Map prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.geolocation", 1); // 1:allow 2:block

		

		
		option = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", Config.getConfigval("ChromePath"));
		option.setExperimentalOption("prefs", prefs);
		//System.out.println(Config.getConfigval("ChromePath"));
		option.addArguments("--disable-default-apps");
		option.addArguments("--disable-popup-blocking");		
		option.addArguments("--start-maximized");
		option.addArguments("--disable-infobars");
		option.addArguments("--disable-notifications");
		option.addArguments("--use-fake-ui-for-media-stream");
		//System.setProperty("webdriver.chrome.logfile", "logs.txt");		
		ChromeDriver driver =  new ChromeDriver(option);
		//((LocationContext)driver).setLocation(new Location(19.75, -99.098, 0));//999
		((LocationContext)driver).setLocation(new Location(19.345195, -99.149651, 0));//222
		//((LocationContext)driver).setLocation(new Location(22.155505, -101.002137, 0));//1090
		
		return driver;
	};
	
	// chrome driver supplier
		private static final Supplier<WebDriver> remoteDriverSupplier = () -> {		
			  
			DesiredCapabilities dc = new DesiredCapabilities().chrome();
			
			try {
				dc.setCapability("geo.enabled", true);
				return new RemoteWebDriver(new URL("http://15.206.85.115:4444/wd/hub"),dc);
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
				return null;
			}
			
		};
	
	// chrome mobile driver supplier
	private static final Supplier<WebDriver> mobilechromeDriverSupplier = () -> {		
		  
		Map<String, Object> deviceMetrics = new HashMap<>();
		deviceMetrics.put("width", 375);
		deviceMetrics.put("height", 667);
		deviceMetrics.put("pixelRatio", 3.0);
		Map<String, Object> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		/*mobileEmulation.put
		("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");*/
		option = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", Config.getConfigval("ChromePath"));
		option.setExperimentalOption("mobileEmulation", mobileEmulation);
		//System.out.println(Config.getConfigval("ChromePath"));
		option.addArguments("--disable-default-apps");
		option.addArguments("--disable-popup-blocking");		
		option.addArguments("--start-maximized");
		option.addArguments("--disable-infobars");
		option.addArguments("--use-fake-ui-for-media-stream");
		option.addArguments("--incognito");
		System.setProperty("webdriver.chrome.logfile", "logs.txt");		
		return new ChromeDriver(option);
	};
	// firefox driver supplier
	private static final Supplier<WebDriver> firefoxDriverSupplier = () -> 
    {
    	try
    	{
    		System.setProperty("webdriver.gecko.driver", Config.getConfigval("FFPath"));
            return new FirefoxDriver();
    	}catch(Exception ex)
    	{
    		Log.addToLog("Exception while creating the driver. Please check the webdriver supplier class." + "\n" + ex.toString());
    		return null;
    	}        
    };
    
    //Chrome headless driver supplier
	private static final Supplier<WebDriver> ChromeheadlessSupplier = () -> {
		
		
		option = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", Config.getConfigval("ChromePath"));
		//System.out.println(Config.getConfigval("ChromePath"));
		option.addArguments("--disable-default-apps");
		option.addArguments("--disable-popup-blocking");		
		option.addArguments("--start-maximized");
		option.addArguments("--disable-infobars");
		option.addArguments("--use-fake-ui-for-media-stream");
		option.addArguments("--incognito");
		option.addArguments("--headless");
		System.setProperty("webdriver.chrome.logfile", "logs.txt");		
		return new ChromeDriver(option);
	};
	
	static
	{
		driverMap.put(DriverType.Chrome,chromeDriverSupplier);
		driverMap.put(DriverType.FireFox,firefoxDriverSupplier);
		driverMap.put(DriverType.ChromeHeadLess,ChromeheadlessSupplier);
		driverMap.put(DriverType.ChromeMobile,mobilechromeDriverSupplier);
		driverMap.put(DriverType.Remote,remoteDriverSupplier);
	}

	// return a new driver from the map
	public static final WebDriver getDriver(DriverType type) {
		return driverMap.get(type).get();
	}
}
