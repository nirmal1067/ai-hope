package org.ai.hope.alogrithm.runner;

import java.util.Arrays;

import org.ai.hope.core.util.Logger;
import org.ai.hope.matrix.nn.MatrixOperations;

public class MatrixOperationsRunner {
	
	public static void main(String[] args)
	{
		testMatrixMultiplication();
		
		//testMatrixTranspose();
	}
	
	private static void testMatrixMultiplication()
	{
		
		double[][] inputs = new double[][] { 
			new double[] { 2, 3 ,3}
			
	   };
	   
		double[][] weights = new double[][] { 
			new double[] { 2}, 
			new double[] { 5},
			new double[] { 2}, 
			new double[] { 5}
		};
		
		Logger.info("======Test0========");
		double[][] result = MatrixOperations.MatrixMultiplication(inputs, weights);
		
		Logger.printMatrix(result);
		
		 weights = new double[][] { 
			new double[] { 2, 3 ,3}
			
	   };
	   
		 inputs = new double[][] { 
			new double[] { 2}, 
			new double[] { 5},
			new double[] { 2}, 
			new double[] { 5}
		};
		
		Logger.info("======Test0.1========");
		
		
		result = MatrixOperations.MatrixMultiplication(inputs, weights);
		
		Logger.printMatrix(result);
		
		 inputs = new double[][] { 
			new double[] { 2, 3 }
			
	   };
	   
		 weights = new double[][] { 
			new double[] { 2,3,4 }, 
			new double[] { 5,6,7}
		};
		
		Logger.info("======Test1========");
		
		
		result = MatrixOperations.MatrixMultiplication(inputs, weights);
		
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
		
		double[] [] result2 = MatrixOperations.MatrixMultiplication(inputs, weights);
		Logger.printMatrix(result2);
	}
	
	
	private static void testMatrixTranspose()
	{
		double[][] inputs = new double[][] { 
			new double[] { 2, 3 }
			
	   };
	   
		double[][] weights = new double[][] { 
			new double[] { 2,3,4 }, 
			new double[] { 5,6,7}
		};
		
		Logger.info("======Test1========");
		
		
		double[][] result = MatrixOperations.matrixTranspose(inputs);
		
		Logger.printMatrix(result);
		
       Logger.info("======Test2========");
		
		
		result = MatrixOperations.matrixTranspose(weights);
		
		Logger.printMatrix(result);
		
		Logger.info("======Test3========");
		 inputs = new double[][] { 
			new double[] {1, 1, 1, 1},
			new double[] {2, 2, 2, 2},
			new double[] {3, 3, 3, 3},
			new double[] {4, 4, 4, 4}
			
	   };
	   
	   weights = new double[][] { 
			new double[] { 7,8}, 
			new double[] { 9,10},
			new double[] { 11,12}
		};
		
		double[] [] result2 = MatrixOperations.matrixTranspose(weights);;
		Logger.printMatrix(result2);
		
		Logger.info("======Test4========");
		
        result = MatrixOperations.matrixTranspose(inputs);
		
		Logger.printMatrix(result);
	}
	


}
