package org.ai.hope.alogrithm.runner;

import org.ai.hope.core.util.Logger;
import org.ai.hope.nn.NeuralNetwork;



public class NeuralNetworkRunner {
	public static final double LEARNING_RATE = 0.3d;
	public static final double MOMENTUM = 0.6d;
	public static final int ITERATIONS = 300000;

	
	public static void main(String[] args)
	{
		
		double[][] trainingData = new double[][] { 
			new double[] { 0, 0 }, 
			new double[] { 0, 1 }, 
			new double[] { 1, 0 },
			new double[] { 1, 1 } 
	};

	double[][] trainingResults = new double[][] {
			new double[] { 0 }, 
			new double[] { 1 }, 
			new double[] { 1 },
			new double[] { 0 } 
	};
	
	int[] hiddenLayerSize = {50,50};
	//Logger.ENABLE_DEBUG_MODE=true;
	NeuralNetwork neuralNetwork = new NeuralNetwork(2,2,hiddenLayerSize,1);
	

	for (int iterations = 0; iterations < ITERATIONS; iterations++) {

		for (int i = 0; i < trainingResults.length; i++) {
			neuralNetwork.train(trainingData[i], trainingResults[i],
					LEARNING_RATE, MOMENTUM);
		}

		System.out.println();
		for (int i = 0; i < trainingResults.length; i++) {
			double[] t = trainingData[i];
			Logger.info("Epoch: "+ iterations + 1);
			Logger.info(" Training data " +  t[0]+","+ t[1] +" Actual data:  "+neuralNetwork.predict(t)[0]);
		}
	}
	
	}

}
