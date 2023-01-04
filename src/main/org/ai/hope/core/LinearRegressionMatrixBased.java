package org.ai.hope.core;

import org.ai.hope.matrix.nn.MatrixOperations;
import org.ai.hope.nn.Functions;

public class LinearRegressionMatrixBased {

	private double beta;

	private double[][] weights;

	private double learningRate = 0.001d;

	private int epochs;

	// private Function<T, R>

	public LinearRegressionMatrixBased(int featuresCount, int epochs) {
		weights = new double[featuresCount][1];
		this.epochs = epochs;
	}

	public double[][] predict(double[][] inputs) {
		if (inputs == null || inputs.length <= 0) {

			throw new RuntimeException("Input parameters can not be null or blank");
		}

		double[][] result = new double[weights.length][weights[0].length];

		result = MatrixOperations.MatrixMultiplication(inputs, weights);

		result = MatrixOperations.MatrixScalerOperation(result, beta, Functions.ADDITION);

		return result;
	}

	public void train(double[][] trainData, double[] result) {

	}

	public void trainSGD(double[][] trainData, double[] result) {

	}

	public void trainBGD(double[][] trainData, double[][] result) {
		// TODO Auto-generated method stub
		for (int e = 0; e < epochs; e++) {

			double[][] predictedResult = predict(trainData);
			double[][] error = MatrixOperations.MatrixScalerOperation(predictedResult, result, Functions.SUBSTRACTION);
			double[][] transposedData = MatrixOperations.matrixTranspose(trainData);
			double[][] derivativeWrtWeights = MatrixOperations.MatrixMultiplication(transposedData, error);
			double[][] derivativeWithLearningRate = MatrixOperations.MatrixScalerOperation(derivativeWrtWeights,
					learningRate, Functions.MULTIPLY);

			// TODO check based on implementation result if we need to divide by data size
			weights = MatrixOperations.MatrixScalerOperation(weights, derivativeWithLearningRate,
					Functions.SUBSTRACTION);

			double sum = 0;

			for (int i = 0; i < error.length; i++) {
				sum = sum + error[i][0];
			}

			beta = beta - learningRate * sum;

		}

	}

	public void trainMGD(double[][] trainData, double[] result) {
		// TODO Auto-generated method stub

	}

}
