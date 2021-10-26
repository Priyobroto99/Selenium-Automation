package DriverUtils;

import java.util.ArrayList;

public class ConsolePrint {

	private static ConsolePrint single=null;

	ArrayList<String> errors = new ArrayList();

	public static ConsolePrint getInstance() {
		if(single == null) {
			single = new ConsolePrint();
			return single;
		}else {
			return single;
		}
	}
	private  ConsolePrint() {

	}

	public void addError(Exception e) {
		try {
			errors.add(e.getMessage());
		} catch (Exception e1) {

		}
	}

	public void printErrors() {
		errors.forEach(System.out::println);
	}

}
