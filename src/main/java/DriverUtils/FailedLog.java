package DriverUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import testOutput.Logger;

public class FailedLog {

	static String filePath = ".\\Logs\\FaliedTestCases.properties";
	static File file;
	static ArrayList<String> ftcs=new ArrayList<String>();


	public static void logFalied(String tcID,String status)
	{



		File logsFolder = new File(".\\Logs");

		//creating a new Folder named Logs
		if(!logsFolder.exists())
		{
			boolean created =  logsFolder.mkdir();
			if(created)
				System.out.println("Log Folder was created !");
			else
				System.out.println("Unable to create Log folder");            
		}


		//Providing File path
		try
		{
			file = new File(filePath);
			if(!(file.exists()))
			{
				file.createNewFile();
			}
			if (status.equalsIgnoreCase("false")) {
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(tcID.replaceAll(" ", ""));
				ftcs.add(tcID);
				Logger.logMessage(tcID+" Failed");
				System.out.println(tcID+" was added to failed test Log");
				bufferedWriter.newLine();
				bufferedWriter.close();
			}
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static void removeLogFile() {

		try {
			if(file!=null) {
			if(file.exists()) {
				System.out.println("Removing Log File");
				file.delete();
			}
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	static Properties property;

	static BufferedReader reader;

	public static ArrayList<String> getFailedTestCases() {
		System.out.println(ftcs.size()+" TestCase(s) Failed");
		Logger.logMessage(ftcs.size()+" TestCase(s) Failed");
		return ftcs;

	}


}
