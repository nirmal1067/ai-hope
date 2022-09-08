package org.ai.hope.core;

public interface ILayer {

	public double[] predict(double[] inputs);
	
	
	public double[] train(double[] errors, double learningRate, double momentum);
	
}
