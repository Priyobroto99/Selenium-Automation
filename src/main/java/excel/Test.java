package excel;

import java.io.File;

public class Test {
	
	public static void main(String[] args) {
		
		String a[] = {"h1","h2"};
		ExcelWriter ec = ExcelWriter.getInstance();
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
		ec.createFile("s1", ".\\ExcelResults\\Ex.xlsx");
		ec.header(a);
		ec.createNewSheet("Count");
		ec.header(a);
		ec.createRow();
		ec.setData("d1", 0);
		ec.closeSheet();
	}

}
