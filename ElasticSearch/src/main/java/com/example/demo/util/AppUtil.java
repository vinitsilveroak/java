package com.example.demo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AppUtil {

	public static String getLogSupport(Exception e) {
	
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	e.printStackTrace(pw);
	return sw.toString(); 
	}
}
