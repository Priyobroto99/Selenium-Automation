/**
 * 
 */
package DriverUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 
 * 
 * Priyobroto.Lahiri@gmail.com
 *
 */
public class FailedTestCaseLog {

	private static SimpleDateFormat sdf;
	private static Date date;

	private static String fileName = "FailedTestCasesLog";
	private static File file  = new File(System.getProperty("user.dir") + "\\" + fileName);
	private static HashMap<String,String> map = new HashMap<String,String>();
	private static String flag;
	private static String testCaseID;
	private static String value;



	public FailedTestCaseLog() 
	{

	}

	public static void addToFailLog(String flag, String testCaseID)
	{
		date = new Date();

		map.put(testCaseID,flag);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		formatter.format(date);

		if (!(file.exists())) 
		{
			try 
			{
				System.out.println(file.getAbsolutePath());
				file.createNewFile();
			} catch (IOException e) 
			{
				System.out.println(e.toString());
			}
		}

		try 
		{

			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fileWritter);
			//bw.append("\n");
			bw.append(map.toString());
			bw.newLine();
			bw.close();
			map.remove(testCaseID);
		} catch (Exception EX) {
			System.out.println("Exception Occured while writing in logfile");
		}

	}

	public ArrayList<String> getFailTestCasesID() throws IOException
	{

		//HashMap<String, String> hashMap = new HashMap<String, String>();
		ArrayList<String> failList = new ArrayList<String>();
		String filePath = ".\\FailedTestCasesLog";
		String line;
		String key;
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		line = reader.readLine();
		while(( line != null))
		{

			String[] parts = line.split("=");
			if(parts.length >= 1)
			{
				String setKey = parts[1].replaceAll("\\}", "");
				String setValue = parts[0].replaceAll("\\{", "");
				System.out.println(setKey+setValue);
				if(setKey.equalsIgnoreCase("false"))
				{

					failList.add(setValue);
				}

			}
			else
			{
				System.out.println("ignoring line : "+ line);
			}
			line = reader.readLine();
			//System.out.println("hello map :"+hashMap.size());
		}
		for(String s : failList)
		{
			System.out.println(s);
		}
		return failList;

	}

	public static void removeFailedtestCases() {
		if (file.exists()) {
			file.delete();
		}
	}







}
