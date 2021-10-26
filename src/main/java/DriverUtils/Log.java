package DriverUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Log {
	private static SimpleDateFormat formatter;
	private static Date date;
	private static String filename;
	private static File fso;

	public Log(String text) 
	{
		
	}

	public static void addToLog(String text) 
	{
		filename = "Log.txt";
		date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		formatter.format(date);
		fso = new File(System.getProperty("user.dir") + "\\" + filename);
		if (!(fso.exists())) 
		{
			try 
			{
				System.out.println(fso.getAbsolutePath());
				fso.createNewFile();
			} catch (IOException e) 
			{
				System.out.println(e.toString());
			}
		}

		try 
		{
			FileWriter fileWritter = new FileWriter(fso, true);
			BufferedWriter bw = new BufferedWriter(fileWritter);
			bw.append("\n");
			bw.append("----------------------------------------------Smart Automation Logs--------------------------------------");
			bw.newLine();
			bw.append(formatter.format(date));
			bw.newLine();
			bw.append(text);
			bw.newLine();
			bw.append("---------------------------------------------Smart Automation Logs---------------------------------------");
			bw.append("\n");
			bw.close();
		} catch (Exception EX) {
			System.out.println("Exception Occur while writing in logfile");
		}
	}
}
