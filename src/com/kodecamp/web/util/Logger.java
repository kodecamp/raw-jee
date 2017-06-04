package com.kodecamp.web.util;

public class Logger {
	
	private String filePath;
	private static boolean isLoggingOn;
	private static Logger logger = null;
	private Logger(final String filePath){
		this.filePath = filePath;
	}
	
	public static Logger instance(final boolean isOn){
		isLoggingOn = isOn;
		if(logger != null){
			logger = new Logger("/webapp.log");
		}
		return logger;
	}
	
	public void log(Object target,final String method,final String message,Severity severity){
		
		if(Severity.ERROR.equals(severity) || isLoggingOn){
			System.out.println(target.getClass() + ": "+method + " --> " + message);
			
		}
		if(Severity.INFO.equals(severity) & isLoggingOn){
			System.out.println(target.getClass() + ": "+method + " --> " + message);
		}
		
	}
}
