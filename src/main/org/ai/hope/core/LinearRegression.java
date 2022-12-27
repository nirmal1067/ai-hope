package org.ai.hope.core;

import java.util.Arrays;
import java.util.Optional;

public class LinearRegression implements IModel {

	private double beta;

	private double[] weights;

	private double learningRate = 0.001d;

	private int epochs;

	public LinearRegression(int featuresCount, int epochs) {
		weights = new double[featuresCount];
		this.epochs = epochs;
	}

	public Optional<Double> predict(double[] inputs) {
		if (inputs == null || inputs.length <= 0) {
			return Optional.empty();
		}

		double result = 0d;
		for (int i = 0; i < inputs.length; i++) {
			result = inputs[i] * weights[i] + result;
		}

		result = result + beta;

		return Optional.of(result);
	}

	public void train(double[][] trainData, double[] result) {
		if (trainData == null || trainData.length <= 0) {
			throw new RuntimeException("Input data can not be null");
		}

		// Stochastic Gradient descent

		for (int e = 0; e < epochs; e++) {
			for (int i = 0; i < trainData.length; i++) {
				double[] tempInput = trainData[i];

				Optional<Double> predictedValueOptional = this.predict(tempInput);

				double predictedValue = predictedValueOptional.get();

				double error = predictedValue - result[i];
				
				System.out.println("Error: " + error);
				
				//System.out.println(" Input : " + Arrays.toString(tempInput));

				for (int j = 0; j < weights.length; j++) {
					weights[j] = weights[j] - learningRate * error * tempInput[j];
					

				}
				System.out.println(" Weights: " + Arrays.toString(weights));
				beta = beta - learningRate * error;
				System.out.println(" Beta: " + beta);
				
			}
		}

	}

}
