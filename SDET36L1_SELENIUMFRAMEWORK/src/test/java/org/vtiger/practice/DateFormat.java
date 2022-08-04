package org.vtiger.practice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	public static void main(String[] args) {
		
		Date thisDate=new Date();
		SimpleDateFormat dateForm=new SimpleDateFormat("dd/MM/yyy");
		System.out.println(dateForm.format(thisDate));

	}

}
