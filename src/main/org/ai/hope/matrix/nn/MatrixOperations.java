package org.ai.hope.matrix.nn;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MatrixOperations {

	// TODO u can do better , below codes time complexity id too bad.
	public static double[][] MatrixMultiplication(double[][] input,double[][] weights)
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
	
	
	public static double[][] matrixTranspose(double[][] input)
	{
		if(input==null || input[0].length<=0)
		{
			throw new RuntimeException("Please provide proper matrix as input");
		}
		
		double[][] result = new double[input[0].length][input.length];
		
		for(int r=0;r<input.length;r++)
		{
			for(int c=0;c<input[0].length;c++)
			{
				result[c][r]= input[r][c];
			}
		}
		
		return result;
	}
	
	
	public static double[][] ApplyActivationFunction(double[][] matrix, Function<Double, Double> activationFunction)
	{
		double[][] resultMatrix = new double[matrix.length][matrix[0].length];
		for( int r=0;r<matrix.length;r++)
		{
			for(int c=0;c<matrix[0].length;c++)
			{
				resultMatrix[r][c]= activationFunction.apply(matrix[r][c]);
			}
		}
		
		return resultMatrix;
	}
	
	
	public static double[][] MatrixScalerOperation(double[][] leftMatrix,double[][] rightMatrix, BiFunction<Double, Double,Double> opertaion)
	{
		double[][] resultMatrix = new double[leftMatrix.length][leftMatrix[0].length];
		
		for(int r=0;r<resultMatrix.length;r++)
		{
			for(int c=0;c<resultMatrix[0].length;c++)
			{
				resultMatrix[r][c]= opertaion.apply(leftMatrix[r][c], rightMatrix[r][c]);
			}
		}
		
		return resultMatrix;
	}
	
	
	public static double[][] MatrixScalerOperation(double[][] leftMatrix,double value, BiFunction<Double, Double,Double> opertaion)
	{
		double[][] resultMatrix = new double[leftMatrix.length][leftMatrix[0].length];
		
		for(int r=0;r<resultMatrix.length;r++)
		{
			for(int c=0;c<resultMatrix[0].length;c++)
			{
				resultMatrix[r][c]= opertaion.apply(leftMatrix[r][c], value);
			}
		}
		
		return resultMatrix;
	}
	
	public static double[][] DeepCopy(double[][] source)
	{
		double[][] resultMatrix = new double[source.length][source[0].length];
		for(int r =0;r<source.length;r++)
		{
			for(int c=0;c<source[0].length;c++)
			{
				resultMatrix[r][c]= source[r][c];
			}
		}
		
		return resultMatrix;
	}
	

}
