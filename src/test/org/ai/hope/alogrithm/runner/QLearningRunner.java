package org.ai.hope.alogrithm.runner;

import static org.junit.Assert.assertTrue;

import org.ai.hope.core.QLearning;
import org.ai.hope.core.util.Logger;

public class QLearningRunner {


	public static void trainModel()
	{
		double[][] trainingData = new double[][] { 
			new double[] { 0, 0,0,1 }, 
			new double[] { 0, 0,0,-1 }, 
			new double[] { 0, 0,0,0 },
			new double[] { 0, 0,0,0 } 
		};
		
		QLearning learning = new QLearning(4, 4, 4, .99, 0.05, trainingData);
		
		learning.train(50);
		
		//Logger.printMatrix3D(learning.getqValues());
	}
	
	public static void main(String[] args)
	{
		trainModel();
		testRandom();
	}
	
	private static void testRandom()
	{
		double[][] trainingData = new double[][] { 
			new double[] { 0, 0,0,1 }, 
			new double[] { 0, -1,0,0 }, 
			new double[] { 0, 0,0,-1 },
			new double[] { 0, 0,0,0 } 
		};
		
		QLearning learning = new QLearning(4, 4, 4, .99, 0.05, trainingData);
		
		for(int i =0;i<20;i++)
		{
		  int value = learning.randomValue(0, 4);
		  //System.out.println(value);
		  assertTrue(value<4);
		}
		
	}

}
