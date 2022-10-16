package org.ai.hope.alogrithm.runner;

import java.util.List;

import org.ai.hope.core.util.Logger;
import org.ai.hope.matrix.nn.MatrixNeuralNetwork;
import org.ai.hope.nn.NeuralNetwork;



public class MatrixNeuralNetworkRunner {
	public static final double LEARNING_RATE = 0.3d;
	public static final double MOMENTUM = 0.6d;
	public static final int ITERATIONS = 100000;

	
	public static void singleSetTest()
	{
		double[][] trainingData = new double[][] { 
			new double[] { 0, 0 }
		 
		};

	double[][] trainingResults = new double[][] {
			new double[] { 0 }
			
		};
		
		int[] hiddenLayerSize = {50,50};
		//Logger.ENABLE_DEBUG_MODE=true;
		NeuralNetwork neuralNetwork = new NeuralNetwork(2,2,hiddenLayerSize,1);
		
		MatrixNeuralNetwork matrixNeuralNetwork = new MatrixNeuralNetwork(2, 2, hiddenLayerSize, 1);
		
		

		for (int iterations = 0; iterations < ITERATIONS; iterations++) {

			for (int i = 0; i < trainingResults.length; i++) {
				matrixNeuralNetwork.train(trainingData, trainingResults,
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
	//Logger.ENABLE_DEBUG_MODE=true;
	//NeuralNetwork neuralNetwork = new NeuralNetwork(2,2,hiddenLayerSize,1);
	//Logger.ENABLE_DEBUG_MODE=true;
	MatrixNeuralNetwork matrixNeuralNetwork = new MatrixNeuralNetwork(2, 1, hiddenLayerSize, 1);
	
	

	for (int iterations = 0; iterations < ITERATIONS; iterations++) {

		for (int i = 0; i < trainingResults.length; i++) {
			double[][] tempTrain = new double[1][trainingData[0].length];
			double[][] tempOutPut = new double[1][trainingResults[0].length];
			
			tempTrain[0][0]=trainingData[i][0];
			tempTrain[0][1]=trainingData[i][1];
			tempOutPut[0][0]=trainingResults[i][0];
			matrixNeuralNetwork.train(tempTrain, tempOutPut,LEARNING_RATE, MOMENTUM);
		}

		System.out.println();
		for (int i = 0; i < trainingResults.length; i++) {
			double[][] tempTrain = new double[1][trainingData[0].length];
			tempTrain[0][0]=trainingData[i][0];
			tempTrain[0][1]=trainingData[i][1];
			Logger.info("Epoch: "+ iterations + 1);
			Logger.info(" Training data " +  tempTrain[0][0]+","+ tempTrain[0][1] +" Actual data:  "+matrixNeuralNetwork.predict(tempTrain)[0][0]);
		}
	}
	
	}

}
