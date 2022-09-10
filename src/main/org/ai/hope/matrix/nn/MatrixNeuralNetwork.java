package org.ai.hope.matrix.nn;

import org.ai.hope.nn.Functions;

public class MatrixNeuralNetwork {

	private MatrixLayer[] layers;
	
	public MatrixNeuralNetwork(int inputs, int hiddenLayers, int[] hiddeLayerSizes, int outPuts)
	{
		layers= new MatrixLayer[hiddenLayers+1];
	}

	public double[][] predict(double[][] input) {
		double[][] layeredOutputs = null;
		for (int i = 0; i < layers.length; i++) {
			layeredOutputs = layers[i].predict(input);
			input = layeredOutputs;
		}
		return layeredOutputs;
	}

	public void train(double[][] inputs, double[][] expectedOutPuts, double learningRate, double momentum) {
		// TODO Auto-generated method stub
		
		double[][] modelOutPut = this.predict(inputs);
		double[][] errors  = MatrixOperations.MatrixScalerOperation(expectedOutPuts, modelOutPut, Functions.SUBSTRACTION);
		
	    for(int i = layers.length-1;i<=0;i--)
	    {
	    	errors = layers[i].train(errors, learningRate, momentum);
	    }
		

	}

	public void initilizeLayers(int inputs, int hiddenLayers, int[] hiddeLayerSizes, int outPuts) {
		// TODO Auto-generated method stub

	}

}
