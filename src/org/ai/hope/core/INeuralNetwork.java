package org.ai.hope.core;

public interface INeuralNetwork {
	
	public double[] predict(double[] input);
	
	public void train(double[] inputs,double[] expectedOutPuts,double learningRate,double momentum);

}
