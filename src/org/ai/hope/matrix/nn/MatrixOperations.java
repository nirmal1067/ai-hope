package org.ai.hope.matrix.nn;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MatrixOperations {

	// TODO u can do better , below codes time complexity id too bad.
	public static double[][] matrixMultiplication(double[][] input,double[][] weights)
	{
		if(input==null || weights==null || input[0].length<=0 || weights[0].length<=0)
		{
			throw new RuntimeException("Please provide proper matrix as input");
		}
		
		double[][] resultMatrix = new double[input.length][weights[0].length];
		
		for(int r=0;r<resultMatrix.length;r++)
		{
			for( int c=0;c<resultMatrix[0].length;c++)
			{
				int firstMatrixRow = r;
				int secondMatrixCol = c;
				int numberOfIterations =0;
				double value = 0;
				while(numberOfIterations<input[0].length)
				{
					value = value + input[firstMatrixRow][numberOfIterations] * weights[numberOfIterations][secondMatrixCol];
					numberOfIterations = numberOfIterations+1;
				}
				
				resultMatrix[r][c]= value;
			}
		}
		
		return resultMatrix;
	}
	
	
	

}
