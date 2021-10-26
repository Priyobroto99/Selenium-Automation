package DriverUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {


	public static void logMessage(String log)
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
		String filePath = ".\\Logs\\testLogs.txt";

		try
		{
			File file = new File(filePath);
			if(!(file.exists()))
			{
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String pattern = "dd-MM-yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());

			String logMessage = date+"| "+log;
			bufferedWriter.write(logMessage);
			bufferedWriter.newLine();
			bufferedWriter.close();
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}



	}

	public Logger() {
		super();
		// TODO Auto-generated constructor stub
	}

}
