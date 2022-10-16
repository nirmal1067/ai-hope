package org.ai.hope.matrix.nn;

import java.util.Random;

import org.ai.hope.core.util.Logger;
import org.ai.hope.nn.Functions;

public class MatrixLayer {

	private double[][] weights;

	private double[][] dWeights;

	private double[][] input;

	private double[][] output;
	
	private int layer=0;

	private Random random = new Random();

	public MatrixLayer(int inputs, int outputs,int layerNumber) {
		int size = 1 + inputs;
		this.layer = layerNumber;
		
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
		Logger.debug("Layer Number: "+ this.layer  +" while initlializing Input network size: " + input.length+","+input[0].length + " Output Layer Size: " + output.length +","+output[0].length
				+ " Weights size " + weights.length+","+weights[0].length);
	}

	public double[][] predict(double[][] inputs) {
		//System.arraycopy(inputs, 0, input, 0, inputs.length);
		MatrixOperations.DeepCopyingValues(inputs, input);
		input[0][input[0].length - 1] = 1;
		output = MatrixOperations.MatrixMultiplication(input, weights);
		output = MatrixOperations.ApplyActivationFunction(output, Functions.SIGMOID);
		Logger.debug("Layer Number: " + this.layer + " Printing output below");
		Logger.printMatrix(output);
		return MatrixOperations.DeepCopy(output);
	}

	public double[][] train(double[][] errors, double learningRate, double momentum) {
		// TODO Auto-generated method stub
		double[][] nextLayerError = nextLayerError(errors,learningRate,momentum);
		double[][] internalOutputs = this.output;
		
		
		// Check dimension of matrix before operations we may need transpose in few cases
		double[][] tempMatrix1 = MatrixOperations.ApplyActivationFunction(internalOutputs, Functions.DSIGMOID);
		tempMatrix1 = MatrixOperations.MatrixScalerOperation(tempMatrix1, learningRate, Functions.MULTIPLY);
		double[] [] tempMatrix2 = MatrixOperations.MatrixScalerOperation(errors, learningRate, Functions.MULTIPLY);
		tempMatrix2 = MatrixOperations.MatrixScalerOperation(tempMatrix1, tempMatrix2, Functions.MULTIPLY);
		
		// This is have correct diemnesion of weight matrix while multiplication
		tempMatrix1 = MatrixOperations.matrixTranspose(input);
		
		tempMatrix2 = MatrixOperations.MatrixMultiplication(tempMatrix1, tempMatrix2);
		
		double[][] previousDerivativeWithDelta = MatrixOperations.MatrixScalerOperation(dWeights, momentum, Functions.MULTIPLY);
		
		previousDerivativeWithDelta = MatrixOperations.MatrixScalerOperation(tempMatrix2, previousDerivativeWithDelta, Functions.ADDITION);
		this.weights = MatrixOperations.MatrixScalerOperation(weights, previousDerivativeWithDelta, Functions.ADDITION);
		dWeights = tempMatrix2;
		
		Logger.debug("Layer Number: " + this.layer + " Printing weights below");
		Logger.printMatrix(weights);
		Logger.debug("Dweights");
		Logger.printMatrix(dWeights);
		return nextLayerError;
	}
	
	
	

	private double[][] performAdjustmentByIncreasing(double[][] internalOutputs, double[][] errors) {
		double[][] finalArray = new double[errors.length][errors[0].length];
		
		for(int r=0;r<finalArray.length;r++)
		{
			for(int c=0;c<finalArray[0].length;c++)
			{
				if(c==finalArray.length-1)
				{
					finalArray[r][c]=1;
					continue;
				}
				
				finalArray[r][c]= errors[r][c];
			}
		}
		return finalArray;
	}
	
	
	private double[][] performAdjustmentByDecreasing(double[][] internalOutputs) {
		double[][] finalArray = new double[internalOutputs.length-1][internalOutputs[0].length];
		
		for(int r=0;r<finalArray.length;r++)
		{
			for(int c=0;c<finalArray[0].length;c++)
			{
				finalArray[r][c]= internalOutputs[r][c];
			}
		}
		return finalArray;
	}

	private double[][] nextLayerError(double[][] errors, double learningRate, double momentum)
	{
		double[][] result  = new double[input.length][input[0].length];
		
		//if(this.layer>0)
	//	{
			double[][] tempMatrix1 = MatrixOperations.ApplyActivationFunction(output, Functions.DSIGMOID);
			tempMatrix1 = MatrixOperations.MatrixScalerOperation(errors, tempMatrix1, Functions.MULTIPLY);
			Logger.printMatrixDimensions("ErrorMatrix", tempMatrix1);
			// Transpose output as per matrix rules to get correct next layer error.
			
			//tempMatrix1 = MatrixOperations.matrixTranspose(tempMatrix1);
			double[] [] actualWeights = performAdjustmentByDecreasing(weights);
			double[][] tempMatrix2 = MatrixOperations.matrixTranspose(actualWeights);
			Logger.printMatrixDimensions("ErrorMatrix", tempMatrix1);
			tempMatrix1 = MatrixOperations.MatrixMultiplication(tempMatrix1, tempMatrix2);
			
			Logger.printMatrixDimensions("ErrorMatrix", tempMatrix1);
			Logger.debug("Print Error matrix before Transpose");
			Logger.printMatrix(tempMatrix1);
			//result = MatrixOperations.matrixTranspose(tempMatrix1);
			result = tempMatrix1;
			Logger.debug("Layer Number: " + this.layer + " Printing error for previous Layer");
			Logger.printMatrix(result);
		//}
		
		
		return result;
		
	}

}
