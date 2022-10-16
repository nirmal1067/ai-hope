package org.ai.hope.alogrithm.runner;

import org.ai.hope.core.QLearning;

public class QLearningRunner {


	
	public static void main(String[] args)
	{
		double[][] trainingData = new double[][] { 
			new double[] { 0, 0,0,1 }, 
			new double[] { 0, -1,0,0 }, 
			new double[] { 0, 0,0,-1 },
			new double[] { 0, 0,0,0 } 
		};
		
		QLearning learning = new QLearning(4, 4, 4, .99, 0.05, trainingData);
		
		learning.train(50000);
		
		System.out.println(learning.getqValues());
	}

}
