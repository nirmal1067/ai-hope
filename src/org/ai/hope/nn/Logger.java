package org.ai.hope.nn;

public class Logger {
	
	public static boolean ENABLE_DEBUG_MODE=false;

	public static void info(String log)
	{
		System.out.println(log);
	}
	
	public static void debug(String log)
	{
		if(ENABLE_DEBUG_MODE)
		System.out.println(log);
	}
}
