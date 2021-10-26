package MainRunner;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;

import com.allEnums.DriverType;
import com.allEnums.Modules;
import com.allEnums.PlaceOrder;
import com.allEnums.TestGroup;

import AllAnnotations.TestDetails;
import AllAnnotations.TestModules;
import DriverUtils.Config;
import DriverUtils.FailedLog;
import DriverUtils.GetWD;
import DriverUtils.Log;
import DriverUtils.Zip;
import ReportManager.ReportMgr;
import excel.ExcelWriter;
import testOutput.Logger;

public class ConfigureAndRun {

	public static WebDriver wd;
	private static String patternMain = "dd-MM-yy";
	private static SimpleDateFormat simpleDateFormatMain = new SimpleDateFormat(patternMain);
	public static ReportMgr rpMgr = ReportMgr.getInstance();
	public static String executionStartedAt="";
	public static  int timeStarted=0;
	public static boolean RunFailedTestCase = false;
	public static int failedCounter1=0;
	public static int passedCounter1=0;
	public static int failedCounter=0;
	public static int passedCounter=0;
	public static List<String> testCases = new ArrayList<String>();
	public static int testCaseCounter = 0;
	public static List secondTimeFailedTestCase;
	public static int failTestCaseFlag = 0;
	public static int PassCounterSuit_1=0;
	public static int PassCounterFailedSuit=0;
	public static int FailCounterSuit_1=0;
	public static int FailCounterFailedSuit=0;
	public static ExcelWriter ec;

	//	private ConfigureAndRun() {
	//
	//	}

	public ConfigureAndRun get() {
		return new ConfigureAndRun();
	}

	public static WebDriver getRunDriver() {
		String RunSettings = Config.getConfigval("DriverName");
		if (RunSettings.equalsIgnoreCase("Chrome")) {
			GetWD driver = new GetWD(DriverType.Chrome);
			return wd = driver.getDriver();
		} else if (RunSettings.equalsIgnoreCase("Chromeheadless")) {
			GetWD driver = new GetWD(DriverType.ChromeHeadLess);
			return wd = driver.getDriver();	
		}
		else if(RunSettings.equalsIgnoreCase("ChromeMobile")) {
			GetWD driver = new GetWD(DriverType.ChromeMobile);
			return wd = driver.getDriver();	
		}
		else if (RunSettings.equalsIgnoreCase("firefox")) {
			GetWD driver = new GetWD(DriverType.FireFox);
			return wd = driver.getDriver();
		}
		else if(RunSettings.equalsIgnoreCase("AppiumAndroidWeb"))
		{
			GetWD driver = new GetWD(DriverType.AppiumAndroidWeb);
			return wd = driver.getDriver();
		}
		else if (RunSettings.equalsIgnoreCase("Remote")) {
			GetWD driver = new GetWD(DriverType.Remote);
			return wd = driver.getDriver();	
		}
		else {
			GetWD driver = new GetWD(DriverType.Chrome);
			return wd = driver.getDriver();
		}
	}

	public static void runTestCases(Boolean runFlag, String RunTxt) {


		ArrayList<Class<?>> unsortedClasses = new ArrayList<>();;
		ArrayList<String> failTestCasesID = new ArrayList<String>();
		Modules module = null;
		String RunSettings = Config.getConfigval("RunSettings");
		String runAll = Config.getConfigval("RunAll");
		String runRange = Config.getConfigval("RunRange");
		String RunSettingsModule = Config.getConfigval("Module");
		ec = ExcelWriter.getInstance();
		
		try {
			
			//all classes in package TestCases_GoIbibo taken
			Reflections ref = new Reflections("TestCases_GoIbibo", new Scanner[0]);	

			Logger.logMessage("-----------------------------Starting Test Suite------------------------------------------");

			//datetime and formatting for excel reporting
			dateTimeStamp();

			for (Class<?> cl : ref.getTypesAnnotatedWith(TestDetails.class)) {

				unsortedClasses.add(cl);

			}
			
			ArrayList<Class<?>> sortedClasses=sortClasses(unsortedClasses);

			if(sortedClasses!=null && !(sortedClasses.isEmpty())) {
			for(Class<?> cl : sortedClasses) {

				String className;
				String TestCaseID;
				TestGroup testgroup;
				TestDetails findable = (TestDetails) cl.getAnnotation(TestDetails.class);
				TestModules findable2 = (TestModules) cl.getAnnotation(TestModules.class);
				className = cl.getName();
				TestCaseID = findable.TestCaseID();
				testgroup = findable.testgroup();
				module = findable2.modules();
				
				if(runRange.equalsIgnoreCase("Y")) {
					int StartRange = Integer.parseInt(Config.getConfigval("StartRange"));
					int EndRange = Integer.parseInt(Config.getConfigval("EndRange"));
					int testCaseNow=Integer.parseInt(TestCaseID.replaceAll("[^0-9]", ""));
					//System.out.println("Running TestCases from Range"+StartRange+" to "+EndRange);
					if((testCaseNow>=StartRange)&&(testCaseNow<=EndRange)) {
						//System.out.println("Running TestCase_"+testCaseNow);
					}
					else {
						continue;
					}
				}

				if(runAll.equalsIgnoreCase("Y") ||
						( module.toString().equalsIgnoreCase(RunSettingsModule)&&testgroup.toString().equalsIgnoreCase(RunSettings)))
				{
					//executing for non-prod environment
					//first tc extraction
					String tcStatus="false";
					wd = getRunDriver();				
					tcStatus=executeTestCases(className,rpMgr);
					if(tcStatus.equalsIgnoreCase("false")) {
						System.out.println(className+" :This testcase has failed");
						failTestCasesID.add(TestCaseID);
						FailCounterSuit_1++;
					}
					else {
						System.out.println(className+" :This testcase has Passed");
						PassCounterSuit_1++;
					}

					wd.quit();
				}

				else {
					Log.addToLog("No Test Case to execute as per the Run setting in config file.");
				}
			}
			}else {
				System.out.println("No classes found");
			}

		} catch (Exception e) {
			Log.addToLog("Exception occured while executing all test cases." + "\n" + e.getMessage());
			e.printStackTrace();
			wd.quit();
		}finally{

			Date date = new Date();
			String executionStoppedAt = "Suit 1 Execution Stopped at "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			Logger.logMessage(executionStoppedAt);
			Logger.logMessage("Total testCases failed after first Suite "+FailCounterSuit_1);
			rpMgr.updateInfoGraphicsTotalTestCase((PassCounterSuit_1+FailCounterSuit_1)+"");
			rpMgr.updateInfoGraphicsExecutionStart(executionStartedAt);
			rpMgr.updateInfoGraphicsExecutionFinish(executionStoppedAt);
			rpMgr.updateInfoGraphicsTotalPassedTestCase(PassCounterSuit_1+"");
			rpMgr.updateInfoGraphicsTotalFailedTestCase(FailCounterSuit_1+"");
			rpMgr.BatchTearDown();

		}

		//*****************************************************************//

		//running failed testcases

		if(rerunFlag()&&(FailCounterSuit_1>0)) {
			System.out.println("");
			System.out.println(".........................");
			System.out.println("");
			System.out.println("");
			System.out.println("Running failed Test cases");
			System.out.println("");
			System.out.println(".........................");
			System.out.println("");
			System.out.println("");
			String header[] = {"Test Case ID","Test Case Name","Step #","Test Step name","Status","Date","Comments"};
			ec.createNewSheet("Round 2");
			ec.header(header);
			ReportMgr.resetInstance();
			Logger.logMessage("-------------------Failed Logs----------------------");
			ReportMgr failrpMgr=ReportMgr.getInstance();
			Reflections ref2 = new Reflections("TestCases", new Scanner[0]);
			Date date = new Date();
			String FailedExecution = "Failed Execution Started at "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			Logger.logMessage(FailedExecution);

			int noOFFailedtestCasesinRound1= failTestCasesID.size();


			for (Class<?> cl : ref2.getTypesAnnotatedWith(TestDetails.class)) {

				String className;
				String TestCaseID;
				TestDetails findable = (TestDetails) cl.getAnnotation(TestDetails.class);
				className = cl.getName();
				TestCaseID = findable.TestCaseID(); 


				for(int t=0;t<noOFFailedtestCasesinRound1;t++)
				{
					//System.out.println(TestCaseID+" : "+failTestCasesID.get(t));
					if(TestCaseID.equalsIgnoreCase(failTestCasesID.get(t)))
					{
						wd = getRunDriver();
						Run.wd=wd;
						String tcStatus ="false";
						tcStatus= executeTestCases(className,failrpMgr);
						if(tcStatus.equalsIgnoreCase("false")) {
							FailCounterFailedSuit++;
						}
						else {
							PassCounterFailedSuit++;
						}
						wd.quit();
					}                                      
				}
			}
			String executionStoppedAt = "Execution Stopped at "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			failrpMgr.updateInfoGraphicsTotalTestCase((PassCounterFailedSuit+FailCounterFailedSuit)+"");
			failrpMgr.updateInfoGraphicsExecutionStart(executionStartedAt);
			failrpMgr.updateInfoGraphicsExecutionFinish(executionStoppedAt);
			failrpMgr.updateInfoGraphicsTotalPassedTestCase(PassCounterFailedSuit+"");
			failrpMgr.updateInfoGraphicsTotalFailedTestCase(FailCounterFailedSuit+"");
			failrpMgr.BatchTearDown();
		}



		Date date= new Date();
		String FailedExecution2 = "Failed Execution Stopped at "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();      
		Logger.logMessage(FailedExecution2);
		Logger.logMessage("Total testCases Failed After Retrying :"+FailCounterFailedSuit);
		FailedLog.removeLogFile();
		Zip z= new Zip();
		z.zipDirectory();
		ec.createNewSheet("Count");
		String header[] = {"Round #","Test Cases Run","Pass","Fail"};
		ec.header(header);
		ec.createRow();
		ec.setData("Round 1", 0);
		ec.setData((PassCounterSuit_1+FailCounterSuit_1)+"", 1);
		ec.setData(PassCounterSuit_1+"", 2);
		ec.setData(FailCounterSuit_1+"", 3);
		ec.createRow();
		ec.setData("Round 2", 0);
		ec.setData((PassCounterFailedSuit+FailCounterFailedSuit)+"", 1);
		ec.setData(PassCounterFailedSuit+"", 2);
		ec.setData(FailCounterFailedSuit+"", 3);
		ec.closeSheet();


	}

	private static void dateTimeStamp() {
		Date date = new Date();
		String min = date.getMinutes()+"";
		String sec = date.getSeconds()+"";
		String pattern = "dd-MM-yyyy_HHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date2 = simpleDateFormat.format(new Date());
		File resultsFolder = new File(".\\ExcelResults");

		//creating a new Folder named ExcelResults
		if(!resultsFolder.exists())
		{
			boolean created =  resultsFolder.mkdir();
			if(created)
				System.out.println("excel results Folder was created !");
			else
				System.out.println("Unable to create results folder");	
		}
		ec.createFile("Round 1",".\\ExcelResults\\TestCaseExecution_"+date2+".xlsx");
		String header[] = {"Test Case ID","Test Case Name","Step #","Test Step name","Status","Date","Comments"};
		ec.header(header);
		if(min.length()<2)
			min="0"+min;
		if(sec.length()<2)
			sec="0"+sec;
		executionStartedAt = "Execution Started at "+date.getHours()+":"+min+":"+sec;
		timeStarted= date.getHours()*360 + date.getMinutes() * 60 + date.getSeconds();
		Logger.logMessage(executionStartedAt);
		
	}

	public static ArrayList<Class<?>> sortClasses(ArrayList<Class<?>> unsortedClasses) {

		ArrayList<Class<?>> sortedClasses = unsortedClasses;

		int n;


		n=sortedClasses.size();
		for (int i = 0; i < n; i++) 
		{
			for (int j = i + 1; j < n; j++) 
			{
				//System.out.println("Value of i "+i+" Value of j "+j);
				//System.out.println(sortedClasses.get(i).getSimpleName().compareTo(sortedClasses.get(j).getSimpleName()));
				if (sortedClasses.get(i).getSimpleName().replaceAll("[^0-9]", "").compareTo(sortedClasses.get(j).getSimpleName().replaceAll("[^0-9]", ""))>0 ) 
				{
					//System.out.println("Changing now from"+i+" to "+j);
					//System.out.println("before Change"+sortedClasses);
					Class tempClass = sortedClasses.get(i);
					sortedClasses.set(i, sortedClasses.get(j));
					sortedClasses.set(j, tempClass);
					// System.out.println("After Change"+sortedClasses);
				}
			}
		}

		return sortedClasses;

	}

	public static String executeTestCases(String clName,ReportMgr rpMgr) {

		System.out.println("class name is "+clName);
		String testCaseStatus="false";
		try {
			Class<?> cTestClass = Class.forName(clName);
			Constructor[] constructors = cTestClass.getConstructors();
			for (Constructor con : constructors) {
				con.newInstance(wd, rpMgr);
				Method[] methods = cTestClass.getMethods();
				for (Method method : methods) {
					method.invoke(wd, new Object[0]);

					testCaseStatus=rpMgr.getStatusOfTestCase();
				}
			}
		}
		catch (NullPointerException e) {

		}
		catch(InvocationTargetException tex) {

		}
		catch(IllegalMonitorStateException imx) {

		}
		catch(IllegalArgumentException iax) {

		}
		catch (Exception e) {
			System.out.println("Exception at executeTestCases :"+e.getMessage());
			e.printStackTrace();
		}
		return testCaseStatus;
	}

	public static boolean placeOrdertc(PlaceOrder isPlaceorderTC) {
		return isPlaceorderTC.toString().equalsIgnoreCase("Y");
	}

	public static boolean RunPlaceOrderTestCasesFlag() {
		boolean b=false;
		String PlaceOrderTestCases=Config.getConfigval("PlaceOrderTestCasesFlag");
		b=PlaceOrderTestCases.equalsIgnoreCase("Y");
		return b;
	}

	public static boolean rerunFlag() {
		boolean b=false;
		String PlaceOrderTestCases=Config.getConfigval("ReRrun");
		b=PlaceOrderTestCases.equalsIgnoreCase("Y");
		return b;
	}


}
