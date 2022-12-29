package org.ai.hope.core;

import java.util.Arrays;
import java.util.Optional;

import org.ai.hope.core.util.Logger;

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
			double mse = 0d;
			for (int i = 0; i < trainData.length; i++) {
				double[] tempInput = trainData[i];

				Optional<Double> predictedValueOptional = this.predict(tempInput);

				double predictedValue = predictedValueOptional.get();

				double error = predictedValue - result[i];
				mse = error * error + mse;

				for (int j = 0; j < weights.length; j++) {
					weights[j] = weights[j] - learningRate * error * tempInput[j];

				}
				beta = beta - learningRate * error;

			}

			mse = (Math.sqrt(mse)) / trainData.length;

			Logger.info(" MSE " + mse + " Weights " + Arrays.toString(weights) + " Beta " + beta);
		}

	}

}
