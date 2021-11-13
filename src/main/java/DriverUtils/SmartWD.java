package DriverUtils;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.allEnums.DriverType;

import ReportManager.ReportMgr;
import ReportManager.ReportUtil;
import ReportManager.TestStep;
import testOutput.Logger;

public  class SmartWD  implements WebDriverEventListener
{
	private static WebDriverWait wait;
	private static int waitTime;
	private static WebDriver wd;
	ReportMgr reportManager;
	int counter = 0;
	public SmartWD(WebDriver driver)
	{
		this.wd = driver;
		waitTime = Integer.parseInt(Config.getConfigval("WaitTime"));
		wait = new WebDriverWait(wd, waitTime);
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) 
	{
		Log.addToLog("Clicked on " + arg0);
		//System.out.println("Clicked on " + arg0);
		arg1.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) 
	{
		Log.addToLog("Waiting for Element to be in clickable state & Will wait for  " + waitTime + " seconds");
		//System.out.println("Waiting for Element to be in clickable state & Will wait for  " + waitTime + " seconds");
		wait.until(ExpectedConditions.elementToBeClickable(arg0));
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Log.addToLog("Element found in clickable state");		
	}
	@Override
	public void onException(Throwable arg0, WebDriver arg1) 
	{
		wd.quit();
	}

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		Log.addToLog("Clicking on the alert to accept");

	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		Log.addToLog("Clicking on the alert dismiss");

	}



	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		Log.addToLog("Navigated to " + arg0);

	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}
}