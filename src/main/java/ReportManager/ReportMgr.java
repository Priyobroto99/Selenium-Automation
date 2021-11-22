package ReportManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import DriverUtils.Config;
import MainRunner.Run;
import excel.ExcelWriter;
import onlineReport.MongoDBConnect;
import onlineReport.RepOnline;
import testOutput.Logger;

public class ReportMgr extends Run {
	
	private static ReportMgr single_instance = null;
	private int stepCount=1;
	private List<ResultSummary> addResultSummaryObjs;
	private List<ResultDetails> addResultDetailObjs;
	private List<InfoGraphics> addInfoGraphicsObjs;
	private List<TestStep> addtestStepsObjs;
	ResultDetails td;ResultSummary t1; 
	InfoGraphics i1 = new InfoGraphics ();
	public TestStep ts;
	public ReportUtil util=new ReportUtil();
	ArrayList<RepOnline.Step> m = new ArrayList<RepOnline.Step>();
	Boolean testCaseStatus = true;
	static Config config = new Config();
	ExcelWriter ec = ExcelWriter.getInstance();
	int testCaseStartedAtLine=0;
	int testCaseEndedAtLine=0;
	JSONObject Steps = null;
	
	
	
	
	public ReportMgr() {
		addResultSummaryObjs = new ArrayList<>();
		addResultDetailObjs = new ArrayList<>();
		addInfoGraphicsObjs = new ArrayList<>();
		MongoDBConnect.mongoDBConnect();
	}
	
	public static ReportMgr getInstance() {
		if (single_instance == null)
			single_instance = new ReportMgr();
		return single_instance;
	} 
	

	public void addResultSummaryObjects(ResultSummary rs){
		addResultSummaryObjs.add(rs);
      	}
	
	public void addResultDetailsObjects(ResultDetails rd){
		addResultDetailObjs.add(rd);
	}
	
	public void addInfoGraphicsObjects(InfoGraphics ir)
	{
		addInfoGraphicsObjs.add(ir);
	}
	
	/**
	 * 
	 * Set new ArrayList object object
	 * Invoke in Test Case Constructor to hold test step objects
	 * 
	 */
	public void setTestStepObjects(){
		addtestStepsObjs = new ArrayList<>();
	}
	/**
	 * 
	 * Add Test Step object
	 * 
	 */
	public void addTestStepObjects(TestStep ts){
 		addtestStepsObjs.add(ts);
	}

	/**
	 * @return TestStep objects as List 
	 */
	public List<TestStep> getTestSteps(){
		return addtestStepsObjs;
	}
	
	public void BatchTearDown(){
		util.writeJSON(addResultSummaryObjs,addResultDetailObjs,addInfoGraphicsObjs);
		//System.out.println("Write to JSON Completed");
	}
	
	
	//Method to mention TestCase Objectives
	public void updateTestCaseDetails(String TCID,String TestCaseDesc){
		
		td = new ResultDetails();
		t1 = new ResultSummary();
		t1.setTCID(TCID);
		t1.setTCDesc(TestCaseDesc);
		//t1.setStatus(true);
		td.setTCID(TCID);
		td.setTCDesc(TestCaseDesc);
		Logger.logMessage2("");
		Logger.logMessage2(TCID+":"+TestCaseDesc);
		
		System.out.println(TCID+" "+TestCaseDesc);
		
		ec.createRow();
		testCaseStartedAtLine = ec.getRowNumber();
		ec.setData(TCID,0);
		ec.setData(TestCaseDesc,1);
		
		Steps= new JSONObject();
		
	}
	
	
	//updating InfoGraphics properties /// 
	
	public void updateInfoGraphicsTotalTestCase(String totalTestCasesCount)
	{
		//i1 = new InfoGraphics();
		i1.setTotalTestCasesCount(totalTestCasesCount);
		//addInfoGraphicsObjects(i1);
	}
	
	public void updateInfoGraphicsTotalFailedTestCase( String totalFaliedTestCaseCount)
	{
		//i1 = new InfoGraphics();
		i1.setTotalFaliedTestCaseCount(totalFaliedTestCaseCount);
		addInfoGraphicsObjects(i1);
	}
	
	public void updateInfoGraphicsTotalPassedTestCase( String totalPassedTestCaseCount)
	{
		//i1 = new InfoGraphics();
		i1.setTotalPassedTestCaseCount(totalPassedTestCaseCount);
		//addInfoGraphicsObjects(i1);
	}
	
	public void updateInfoGraphicsExecutionStart( String executionStartTime)
	{
		//i1 = new InfoGraphics();
		i1.setExecutionStartTime(executionStartTime);
		//addInfoGraphicsObjects(i1);
	}
	
	public void updateInfoGraphicsExecutionFinish( String executionFinsihTime)
	{
		//i1 = new InfoGraphics();
		i1.setExecutionFinsihTime(executionFinsihTime);
		//addInfoGraphicsObjects(i1);
	}
	
	
	
	
	
	//Tear Down at Test Cases Level
	public void TearDown(){
		try {
			
			
			TestStep ts = getTestSteps().stream().filter(testStep -> testStep.getStatus().equals(false)).findFirst()
					.get();
			t1.setStatus(ts.getStatus());
		} catch (Exception ex) {
			t1.setStatus(true);
		}
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			RepOnline.getInstance(config.getConfigval("token"))
			.insertTestCase(t1.getTCID(), t1.getTCDesc(), "", testCaseStatus?"PASS":"FAIL", m);
		
			MongoDBConnect.insertTestCaseMongoDB
			(t1.getTCID(), td.getTCDesc(), testCaseStatus?"PASS":"FAIL", t1.getTCDesc(), Steps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Set Path
		t1.setPath(util.getResultDetailPath());
		td.setTestSteps(getTestSteps());
		addResultDetailsObjects(td);
		addResultSummaryObjects(t1);
		testCaseEndedAtLine = ec.getRowNumber();
		ec.mergeSteps(testCaseStartedAtLine, testCaseEndedAtLine);
		stepCount = 1;
		
		testCaseStatus = true;
		m.clear();
		Steps.clear();
		
	}
	
	//Update Test Log
	public void updateTestLog(String StepName,String StepDesc,boolean Status){
		ts = new TestStep();
		//ts.setStepID(TSID);
		ts.setStepID(stepCount);
		ts.setStepName(StepName);
		ts.setStepDesc(StepDesc);
		ts.setStatus(Status);
		String captureTime=util.getDateTimeStamp();
		String s=ts.getScreenshot();
		//System.out.println(s);
		
		Steps.put(StepName, Status);
		m.add(RepOnline.getInstance(config.getConfigval("token")).new Step(StepName, Status));
		Logger.logMessage2(StepName+":"+Status);
		System.out.println(StepName+":"+Status);
		
		if (!Status) {
			testCaseStatus = false;
			try {
				wd.close();
			} catch (Exception e) {
				
			}
			if (s==null) {
				util.takeScreenshot(util.getScreenshotAbosolutePath() + "//Screenshot_" + captureTime + ".jpg");
			}
			
			ts.setScreenshot(util.getScreenshotAbosolutePath()+"//Screenshot_"+captureTime+".jpg");
			
			//System.out.println(ts.getScreenshot());
			
		}
		else{
			ts.setScreenshot("");
			
		}
		
        		addTestStepObjects(ts);
        		ec.createRow();
        		ec.setData(stepCount+"", 2);
        		ec.setData(ts.getStepName(),3);
        		ec.setData((ts.getStatus()?"PASS":"FAIL"),4);
		stepCount++;
	}

	public String getStatusOfTestCase() {
		return t1.getStatus().toString();
		
	}
	
	public String getNameOfTestCase()
	{
		return t1.getTCDesc().toString();
	}

	public static void resetInstance() {
		single_instance = null;
		
	}
	
	

}
