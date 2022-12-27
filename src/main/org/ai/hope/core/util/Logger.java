package org.ai.hope.core.util;

import java.util.Arrays;

public class Logger {
	
	public static boolean ENABLE_DEBUG_MODE=true;

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
		if(ENABLE_DEBUG_MODE)
		for(int r=0;r<matrix.length;r++)
		{
			Logger.info(Arrays.toString(matrix[r]));
		}
	}
	
	public static void printMatrixDimensions(String matrixName,double[][] matrix)
	{
		if(ENABLE_DEBUG_MODE)
		Logger.info("Dimension of matrix: " +matrixName+ " Rows: "+ matrix.length +" Columns: " + matrix[0].length);
	}
	
	public static void printMatrix3D(double[][][] matrix)
	{
		if(ENABLE_DEBUG_MODE)
		for(int r=0;r<matrix.length;r++)
		{
			for(int c=0;c<matrix[0].length;c++)
			{
				Logger.info(Arrays.toString(matrix[r][c]));
			}
			
		}
	}
}
