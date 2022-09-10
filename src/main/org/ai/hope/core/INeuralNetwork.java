package org.ai.hope.core;

public interface INeuralNetwork {
	
	public double[] predict(double[] input);
	
	public void train(double[] inputs,double[] expectedOutPuts,double learningRate,double momentum);
	
	public void initilizeLayers(int inputs, int hiddenLayers, int[] hiddeLayerSizes, int outPuts);

}
