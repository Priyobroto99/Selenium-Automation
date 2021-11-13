/**
 * 
 */
package AllPages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
public class GoIbiboHomePage {

	public static WebDriver wd;
	public static String DataSetName;
	GetData data;
	private static ReportMgr Report;
	private static WebDriverWait wait;

	//private OnlineReportManager oReport;

	private GoIbiboHomePage (WebDriver WD, String datasetname, ReportMgr report) 
	{
		wd = WD;
		PageFactory.initElements(wd, this);
		DataSetName = datasetname;
		data = new GetData(datasetname);
		Report = report;
		@SuppressWarnings("deprecation")
		long num = new Long(data.getData("waitTime"));
		wait = new WebDriverWait(wd,num);
		//oReport = OnlineReportManager.getInstance();
	}

	public static GoIbiboHomePage using(WebDriver driver, String datasetname, ReportMgr report) 
	{
		return new GoIbiboHomePage(driver, datasetname, report);
	}


	@CacheLookup
	@FindAll({ 
		@FindBy(id = "gosuggest_inputSrc"),
		@FindBy(xpath = "//input[@class='inputSrch']") 
	})
	private WebElement sourceTexBox;

	@CacheLookup
	@FindAll({ 
		@FindBy(id = "gosuggest_inputDest") 
	})
	private WebElement destTexBox;

	@CacheLookup
	@FindAll({ 
		@FindBy(id = "departureCalendar"),
		@FindBy(className = "inputSrch curPointFlt") 
	})
	private WebElement travelDate;

	@CacheLookup
	@FindAll({ 
		@FindBy(css = "span[aria-label='Next Month']")
	})
	private WebElement nxtMnthBtn;

	@CacheLookup
	@FindAll({ 
		@FindBy(xpath = "//div[@aria-disabled='false']")
	})
	private WebElement nextDate;

	@CacheLookup
	@FindAll({ 
		@FindBy(id = "gi_search_btn")
	})
	private WebElement searchBtn;

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


	public GoIbiboHomePage launchUrl() {

		try {

			wd.get(data.getData("url"));
			wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			Report.updateTestLog("Navigate to url", "User is able to navigate to url "+wd.getCurrentUrl(), true);

		} catch (Exception e) {

			Report.updateTestLog("Navigate to url", "User is unable to navigate to specified url ", false);
		}

		return this;
	}


	public GoIbiboHomePage verifyElements() {

		try {

			if(this.sourceTexBox.isDisplayed() && this.destTexBox.isDisplayed() && this.searchBtn.isDisplayed()) {

				Report.updateTestLog("Verify the presence of Input Element", "Input elements visible", true);
			}else {
				Report.updateTestLog("Verify the presence of Input Element", "Input elements not present", false);
			}

		} catch (Exception e) {

			Report.updateTestLog("Verify the presence of Input Element", "Input elements not present", false);e.printStackTrace();
		}
		return this;
	}
	

	public GoIbiboHomePage enterSourceLocation() {

		try {
			sendkeys(this.sourceTexBox,data.getData("src"));
			sendkeys(this.sourceTexBox,Keys.ARROW_DOWN);
			sendkeys(this.sourceTexBox,Keys.RETURN);
			Report.updateTestLog("Enter Source Location", "Source Location "+data.getData("src")+" entered", true);

		} catch (Exception e) {
			Report.updateTestLog("Enter Source Location", "Unable to enter "+data.getData("src"), false);
			e.printStackTrace();
		}
		return this;
	}
	

	public GoIbiboHomePage enterDestLocation() {

		try {
			sendkeys(this.destTexBox,data.getData("dst"));
			sendkeys(this.destTexBox,Keys.ARROW_DOWN);
			sendkeys(this.destTexBox,Keys.RETURN);
			Report.updateTestLog("Enter Destination Location", "Destination Location "+data.getData("dst")+" entered", true);

		} catch (Exception e) {
			Report.updateTestLog("Enter Destination Location", "Unable to enter "+data.getData("dst"), false);
			e.printStackTrace();
		}
			return this;
	}


	
	
	public GoIbiboHomePage clickdepartureTXTbx() {

		try {
			delay(5);
			this.travelDate.click();
			Report.updateTestLog("Click departure textbox", "User is able to click departure textbox in Home page", true);
		} catch (Exception e) {
			Report.updateTestLog("Click departure textbox", "User is unable to click departure textbox in Home page", false);
			e.printStackTrace();
		}
		return this;
	}


	public GoIbiboHomePage clickNxtMnthBtn() {

		try {		
			this.nxtMnthBtn.click();
			Report.updateTestLog("Click '>' icon", "User is able to click '>' icon in Home page", true);
		} catch (Exception e) {
			Report.updateTestLog("Click '>' icon", "User is unable to click '>' icon in Home page", false);
			e.printStackTrace();
		}
		return this;
	}

	public GoIbiboHomePage clickNextDate() {

		try {
			clickJS((this.nextDate));
			Report.updateTestLog("Select earliest date", "User is able to select the first date of the month", true);
		} catch (Exception e) {
			Report.updateTestLog("Select earliest date", "User is unable to select the first date of the month", false);
			e.printStackTrace();
		}
		return this;
	}
	public GoIbiboHomePage clickSearchBtn() {

		try {
			
			clickJS((this.searchBtn));
			Report.updateTestLog("Click Search Button", "User is able to click on search Flights Button", true);
		} catch (Exception e) {
			Report.updateTestLog("Click Search Button", "User is unable to click on search Flights Button", false);
			e.printStackTrace();
		}
		return this;
	}
	public GoIbiboHomePage clickBookBtn() {

		try {
			
			this.bookBtn.click();
			Report.updateTestLog("Click Book Button", "User is able to click on Book Flights Button", true);
		} catch (Exception e) {
			Report.updateTestLog("Click Book Button", "User is unable to click on Book Flights Button", false);
			e.printStackTrace();
		}
		return this;
	}

	public GoIbiboHomePage getFlightpriceDisplayed() {

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



	void sendkeysJS(WebElement e,String s)
	{
		wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(e));
		JavascriptExecutor js = (JavascriptExecutor)wd;
		js.executeScript("document.getElementById('"+e.getAttribute("id")+"').value='"+s+"'");
		wait.until(ExpectedConditions.textToBePresentInElementValue(e, s));
	}
	
	void sendkeys(WebElement e,String s)
	{
		wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(e));
		e.sendKeys(s);
		wait.until(ExpectedConditions.textToBePresentInElementValue(e, s));
		delay(1);
	}
	
	void sendkeys(WebElement e,Keys k)
	{
		wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(e));
		e.sendKeys(k);
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
