/**
 * 
 */
package AllPages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverUtils.GetData;
import ReportManager.ReportMgr;

/**
 * @author 
 * 
 * Priyobroto.Lahiri@gmail.com
 *
 */
public class FlightsPage {

	public static WebDriver wd;
	public static String DataSetName;
	GetData data;
	private static ReportMgr Report;
	private static WebDriverWait wait;

	//private OnlineReportManager oReport;

	private FlightsPage (WebDriver WD, String datasetname, ReportMgr report) 
	{
		wd = WD;
		PageFactory.initElements(wd, this);
		DataSetName = datasetname;
		data = new GetData(datasetname);
		Report = report;
		long num = new Long(data.getData("waitTime"));
		wait = new WebDriverWait(wd,num);
		//oReport = OnlineReportManager.getInstance();
	}


	public static FlightsPage using(WebDriver driver, String datasetname, ReportMgr report) 
	{
		return new FlightsPage(driver, datasetname, report);
	}


	@CacheLookup
	@FindAll({ 
		@FindBy(xpath = "//button[contains(.,'BOOK')]")
	})
	private WebElement bookBtn;
	
	@CacheLookup
	@FindAll({ 
		@FindBy(xpath = "//span[@class='font20']")
	})
	private WebElement priceText;
	
	
	
	public FlightsPage clickBookBtn() {
		
		try {
			this.bookBtn.click();
			Report.updateTestLog("Click Book Button", "User is able to click on Book Flights Button", true);
		} catch (Exception e) {
			Report.updateTestLog("Click Book Button", "User is unable to click on Book Flights Button", false);
			e.printStackTrace();
		}
		return this;
	}
	
	public FlightsPage getFlightpriceDisplayed() {

		try {
			
			wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(priceText));
			Actions ac = new Actions(wd);
			ac.moveToElement(priceText);
			String s = this.priceText.getText().toString().replaceAll("[^0-9]", "");
			
			Report.updateTestLog("Get price of earliest flight next month", "Price of Flight "+s, true);
		
		} catch (Exception e) {
			Report.updateTestLog("Get price of earliest flight next month", "Price of Flight unavailable", false);
			e.printStackTrace();
		}
		return this;
	}

	
	

	
	void clickJS(WebElement e) {
		wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor)wd;
		js.executeScript("arguments[0].click();", e);
	}

	void delay(int i) {
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
