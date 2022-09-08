package org.ai.hope.alogrithm.runner;

import java.util.Arrays;

import org.ai.hope.core.util.Logger;
import org.ai.hope.matrix.nn.MatrixOperations;

public class MatrixOperationsRunner {
	
	public static void main(String[] args)
	{
		double[][] inputs = new double[][] { 
			new double[] { 2, 3 }
			
	   };
	   
		double[][] weights = new double[][] { 
			new double[] { 2,3,4 }, 
			new double[] { 5,6,7}
		};
		
		Logger.info("======Test1========");
		
		
		double[][] result = MatrixOperations.matrixMultiplication(inputs, weights);
		
		Logger.printMatrix(result);
		
		Logger.info("======Test2========");
		 inputs = new double[][] { 
			new double[] { 1,2, 3 },
			new double[] { 4, 5,6 }
			
	   };
	   
	   weights = new double[][] { 
			new double[] { 7,8}, 
			new double[] { 9,10},
			new double[] { 11,12}
		};
		
		double[] [] result2 = MatrixOperations.matrixMultiplication(inputs, weights);
		Logger.printMatrix(result2);
	}
	
	


}
