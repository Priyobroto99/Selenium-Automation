package ReportManager;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import MainRunner.ConfigureAndRun;
import MainRunner.Run;

public class ReportUtil extends ConfigureAndRun{

	private String ReportSummary_path;
	private String DetailReport_Path;
	private int i =0;
	public static String path="";
	
	
	ReportUtil() {
		this.ReportSummary_path = getResultSummaryAbosolutePath();
		path=this.ReportSummary_path;
		this.DetailReport_Path = getResultDetailsAbosolutePath();
	}

	/**
	 * 
	 * @return DetailReport_Path
	 * 
	 */
	public String getResultDetailPath() {
		return DetailReport_Path;

	}

	/**
	 *
	 * @return Current Date Time
	 */
	public String getDateTimeStamp() {
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HHmmss");
		return current.format(format);

	}

	/**
	 *
	 * @return Path of ResultSummary
	 */
	public String getResultSummaryAbosolutePath() {
		String formatedDateTime = getDateTimeStamp();
		String path="";
		int runFailTestCase;
		runFailTestCase = failTestCaseFlag;
		if(runFailTestCase == 1)
		{
			path = "ResultSummary\\Second_Round_Report_" + formatedDateTime;
		}
		else
		{
			path = "ResultSummary\\First_Round_Report_" + formatedDateTime;
		}	
		
		return path;
	}

	/**
	 *
	 * @return Path of ResultDetails
	 */
	public String getResultDetailsAbosolutePath() {
		String formatedDateTime = getDateTimeStamp();
		String Path = "ResultDetails\\RUN_TEST_STEPS_" + formatedDateTime;
		return Path;
	}
		//**ResultDetails\\RUN_TEST_STEPS_
	/**
	 *
	 * @return Path of Screenshot
	 */
	public String getScreenshotAbosolutePath() {
		 String formatedDateTime=getDateTimeStamp();
		String Path = "ResultSummary"+"\\SCREENSHOT";
		return Path;
	}

	/**
	 *
	 * @param Path
	 *            where Directory is created
	 */
	public void createDirectory(String Path) {
		File folder = new File(Path);
		if (!folder.exists()) {
			if (folder.mkdirs()) {
				// System.out.println("Directory is created!");
			} else {
				// System.out.println("Failed to create directory!");
			}
		}
	}

	/**
	 * 
	 * @param Src
	 *            - Source file location input
	 * @param Dest
	 *            - Destination file location
	 */
	public void copyFileName(String source, String dest) {
		File src = new File(source);
		File dst = new File(dest);
		try {
			FileUtils.copyDirectory(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 *
	 * @param Report
	 *            Object Write to JSON File
	 */
	@SuppressWarnings("unused")
	public void writeJSON(List<ResultSummary> rs, List<ResultDetails> rd, List<InfoGraphics> ir) {
		Report rps = new Report();
		rps.setResultSummary(rs);
		Report rpd = new Report();
		rpd.setResultDetails(rd);
		Report rpi = new Report();
		rpi.setInfoGraphics(ir);
		
		createDirectory(ReportSummary_path);
		createDirectory(DetailReport_Path);

		// Create JSON output for Summary Reporting
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String strJson = gson.toJson(rps);

		FileWriter writerToJson = null;
		FileWriter writerToHTML = null;
		try {
			writerToJson = new FileWriter(ReportSummary_path + "\\ReportSummary.json");
			copyFileName("src\\main\\resources\\HTMLTemplateSummary", ReportSummary_path);
			writerToJson.write(strJson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writerToJson != null) {
				try {
					writerToJson.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// Create JSON output for Detail Reporting
		Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
		String strJson1 = gson1.toJson(rpd);

		FileWriter writerToJson1 = null;
		FileWriter writerToHTML1 = null;
		try {
			writerToJson1 = new FileWriter(DetailReport_Path + "\\ReportDetails.json");
			writerToJson1 = new FileWriter(ReportSummary_path + "\\ReportDetails.json");
			copyFileName("src\\main\\resources\\HTMLTemplateDetails", DetailReport_Path);
			writerToJson1.write(strJson1);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writerToJson1 != null) {
				try {
					writerToJson1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
		String strJson2 = gson2.toJson(rpi);
		
		FileWriter writerToJson2 = null;
		FileWriter writerToHTML2 = null;
		try
		{
			writerToJson2 = new FileWriter(ReportSummary_path + "\\InfoGraphics.json");
			writerToJson2.write(strJson2);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if (writerToJson2 != null) {
				try {
					writerToJson2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		

	}

	public void takeScreenshot(String path) {
		try {
			createDirectory(getScreenshotAbosolutePath());
			Thread.sleep(5);
			Robot r = new Robot();
			// Used to get ScreenSize and capture image
			Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage Image = r.createScreenCapture(capture);
			ImageIO.write(Image, "jpg", new File(path));

		} catch (AWTException | IOException | InterruptedException ex) {
			// System.out.println(ex);
		}
	}

}
