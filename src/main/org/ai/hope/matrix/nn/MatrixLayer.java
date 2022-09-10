package org.ai.hope.matrix.nn;

import java.util.Random;

import org.ai.hope.core.util.Logger;
import org.ai.hope.nn.Functions;

public class MatrixLayer {

	private double[][] weights;

	private double[][] dWeights;

	private double[][] input;

	private double[][] output;

	private Random random = new Random();

	public MatrixLayer(int inputs, int outputs) {
		int size = 1 + inputs;
		
		// Following matrix multiplication rules
		this.weights = new double[size][outputs];
		this.input = new double[1][size];
		this.dWeights = new double[size][outputs];
		this.output = new double[1][outputs];

		// intitialize Random weights
		this.initializeLayer();

	}

	private void initializeLayer() {
		for (int r = 0; r < weights.length; r++) {
			for (int c = 0; c < weights[0].length; c++) {
				weights[r][c] = (random.nextDouble() - 0.5d) * 4d;
			}
		}
		Logger.debug(" while initlializing Input network size: " + input.length + " Output Layer Size: " + output.length
				+ " Weights size " + weights.length);
	}

	public double[][] predict(double[][] inputs) {
		System.arraycopy(inputs, 0, input, 0, inputs.length);
		input[0][inputs[0].length - 1] = 1;
		output = MatrixOperations.MatrixMultiplication(input, weights);
		output = MatrixOperations.ApplyActivationFunction(output, Functions.SIGMOID);
		return MatrixOperations.DeepCopy(output);
	}

	public double[][] train(double[][] errors, double learningRate, double momentum) {
		// TODO Auto-generated method stub
		double[][] nextLayerError = nextLayerError(errors,learningRate,momentum);
		
		// Check dimension of matrix before operations we may need transpose in few cases
		double[][] tempMatrix1 = MatrixOperations.ApplyActivationFunction(output, Functions.DSIGMOID);
		double[] [] tempMatrix2 = MatrixOperations.MatrixScalerOperation(errors, learningRate, Functions.MULTIPLY);
		tempMatrix2 = MatrixOperations.MatrixScalerOperation(tempMatrix1, tempMatrix1, Functions.MULTIPLY);
		
		// This is have correct diemnesion of weight matrix while multiplication
		tempMatrix1 = MatrixOperations.matrixTranspose(input);
		
		tempMatrix2 = MatrixOperations.MatrixMultiplication(tempMatrix1, tempMatrix2);
		
		double[][] previousDerivativeWithDelta = MatrixOperations.MatrixScalerOperation(dWeights, momentum, Functions.MULTIPLY);
		
		previousDerivativeWithDelta = MatrixOperations.MatrixScalerOperation(tempMatrix2, previousDerivativeWithDelta, Functions.ADDITION);
		this.weights = MatrixOperations.MatrixScalerOperation(weights, previousDerivativeWithDelta, Functions.ADDITION);
		previousDerivativeWithDelta = tempMatrix2;
		
		return nextLayerError;
	}
	
	
	private double[][] nextLayerError(double[][] errors, double learningRate, double momentum)
	{
		double[][] result  = new double[input.length][input[0].length];
		
		double[][] tempMatrix1 = MatrixOperations.ApplyActivationFunction(output, Functions.DSIGMOID);
		tempMatrix1 = MatrixOperations.MatrixScalerOperation(errors, tempMatrix1, Functions.MULTIPLY);
		
		// Transpose output as per matrix rules to get correct next layer error.
		
		tempMatrix1 = MatrixOperations.matrixTranspose(tempMatrix1);
		
		tempMatrix1 = MatrixOperations.MatrixMultiplication(tempMatrix1, weights);
		
		result = MatrixOperations.matrixTranspose(tempMatrix1);
		
		return result;
		
	}

}
