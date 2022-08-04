package org.vtiger.practice;

public class ReplaceValueInString {

	public static void main(String[] args) {

	//	String statement="My name is ###";
	//	String result = statement.replace("###", "Hari");
	//	System.out.println();
		
		
		
		String statement = "my name is %s";
		String result = String.format(statement, "hari");
		System.out.println(result);
	}

}
