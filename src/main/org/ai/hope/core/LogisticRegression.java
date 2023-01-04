package org.ai.hope.core;

import java.util.Arrays;
import java.util.Optional;

import org.ai.hope.core.util.Logger;
import org.ai.hope.nn.Functions;

public class LogisticRegression implements IModel {

	private double beta;

	private double[] weights;

	private double learningRate = 0.001d;

	private int epochs;

	public LogisticRegression(int featuresCount, int epochs) {
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
		result = Functions.SIGMOID.apply(result);
		return Optional.of(result);
	}

	public void trainSGD(double[][] trainData, double[] result) {
		if (trainData == null || trainData.length <= 0) {
			throw new RuntimeException("Input data can not be null");
		}

		// Stochastic Gradient descent

		for (int e = 0; e < epochs; e++) {
			double entropyLoss = 0d;
			for (int i = 0; i < trainData.length; i++) {
				double[] tempInput = trainData[i];

				Optional<Double> predictedValueOptional = this.predict(tempInput);
				double predictedValue = predictedValueOptional.get();
				double error = predictedValue - result[i];
				
				entropyLoss = -(result[i] * Math.log(predictedValue))- ((1-result[i]) * Math.log(1-predictedValue)) + entropyLoss;

				for (int j = 0; j < weights.length; j++) {
					weights[j] = weights[j] - learningRate * error * tempInput[j];

				}
				beta = beta - learningRate * error;

			}

			entropyLoss = entropyLoss / trainData.length;
			Logger.info(" Cost Function " + entropyLoss + " Weights " + Arrays.toString(weights) + " Beta " + beta);
		}

	}

	@Override
	public void trainBGD(double[][] trainData, double[] result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trainMGD(double[][] trainData, double[] result) {
		// TODO Auto-generated method stub
		
	}

}
