/**
 * 
 */
package TestCases_GoIbibo;

import org.openqa.selenium.WebDriver;

import com.allEnums.Modules;
import com.allEnums.TestGroup;

import AllAnnotations.TestDetails;
import AllAnnotations.TestModules;
import AllPages.*;
import DriverUtils.Config;
import ReportManager.ReportMgr;

/**
 * @author 
 * 
 * Priyobroto.Lahiri@cognizant.com
 *
 */

@TestDetails(TestCaseID = "Test Case 001", testgroup = TestGroup.Regression)
@TestModules(modules = Modules.homeModule)
public class TestCase001 {

	private static WebDriver wd;
	private static ReportMgr Report;
	private static GoIbiboHomePage ghome;
	//private static OnlineReportManager oReport;
	

	public TestCase001(WebDriver driver, ReportMgr Report) 
	{
		this.wd = driver;
		this.Report=Report;
		this.Report.setTestStepObjects();
		ghome = GoIbiboHomePage.using(wd, Config.getConfigval("TestDataFileName"), Report);
		//oReport = OnlineReportManager.getInstance();
	}
	
	public static void runTestCase001() 
	{
		try {
			Report.updateTestCaseDetails("TC_001","Verify Elements in the page");
			//oReport.updateTestScenarioDetails("SC_010", "Validate the presence of all elements  in Home page for  Guest User", "Regression");
			//oReport.updateTestCaseDetails("TC_010", "Orderserv_KFC Peru Validate the presence of the header elements  in Home page for  Guest User", "Orderserv", "KFC_Peru", "Release-1", "Sprint-1", "Home");
		} catch (Exception e) {
			System.out.println("exception in runtestcase001");
		}

		try {
			ghome.launchUrl().verifyElements();
			

		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}finally{
			Report.TearDown();
		}
	}
}
