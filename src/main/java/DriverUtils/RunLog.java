package DriverUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class RunLog 
{
	private static Properties propRun;
	private static InputStream inputRun;
	private static FileOutputStream fileOut = null;
	private static String pattern = "dd-MM-yy";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private static String date;
	private static FileInputStream fileIn = null;
	static Random rand = new Random();
	public RunLog() 
	{
		try {
			date = simpleDateFormat.format(new Date());
			propRun = new Properties();
			inputRun = new FileInputStream(System.getProperty("user.dir") + "\\" + "RunLog.properties");
			propRun.load(inputRun);
		} catch (Exception ex) {
			Log.addToLog("Unable to load the Config properties file." + "\n" + ex.toString());

		}
	}
	public static String getRunLog(String key) {
		try {
			return propRun.getProperty(key);
		} catch (Exception ex) {
			Log.addToLog("Config key isn't present in the config file." + "\n" + ex.toString());
			return null;
		}
	}
	public static void setRunLog(String Value) {
		try {
			File fso = new File(System.getProperty("user.dir") + "\\" + "RunLog.properties");
			fileIn = new FileInputStream(fso);
			propRun.load(fileIn);
			propRun.setProperty(rand.nextInt(1000)+"-" + date, Value);
			fileOut = new FileOutputStream(new File(System.getProperty("user.dir") + "\\" + "RunLog.properties"));
			propRun.store(fileOut, "sample properties");
		} catch (Exception ex) {
			Log.addToLog("Config key isn't present in the config file." + "\n" + ex.toString());
		}finally{
			try{
				fileOut.close();
			}catch(Exception em){
				Log.addToLog("Exception while closing the file out in Run log class" + "\n" + em.getMessage());
			}
			
		}
	}
}
