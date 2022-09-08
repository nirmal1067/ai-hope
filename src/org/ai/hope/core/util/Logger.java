package org.ai.hope.core.util;

import java.util.Arrays;

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
	
	
	public static void printMatrix(double[][] matrix)
	{
		for(int r=0;r<matrix.length;r++)
		{
			Logger.info(Arrays.toString(matrix[r]));
		}
	}
}
