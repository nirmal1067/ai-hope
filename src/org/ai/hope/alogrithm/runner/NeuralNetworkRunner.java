package org.ai.hope.alogrithm.runner;

import org.ai.hope.nn.NeuralNetwork;



public class NeuralNetworkRunner {
	public static final double LEARNING_RATE = 0.3d;
	public static final double MOMENTUM = 0.6d;
	public static final int ITERATIONS = 100000;

	
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
	
	int[] hiddenLayerSize = {3};
	
	//NeuralNetwork neuralNetwork = new NeuralNetwork(2,1,hiddenLayerSize,1);
	
	NeuralNetwork neuralNetwork = new NeuralNetwork(2, 3,1);

	for (int iterations = 0; iterations < ITERATIONS; iterations++) {

		for (int i = 0; i < trainingResults.length; i++) {
			neuralNetwork.train(trainingData[i], trainingResults[i],
					LEARNING_RATE, MOMENTUM);
		}

		System.out.println();
		for (int i = 0; i < trainingResults.length; i++) {
			double[] t = trainingData[i];
			System.out.printf("%d epoch\n", iterations + 1);
			System.out.printf("%.1f, %.1f --> %.3f\n", t[0], t[1], neuralNetwork.predict(t)[0]);
		}
	}
	
	}

}
