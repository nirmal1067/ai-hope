package org.ai.hope.nn;

public interface ILayer {

	public double[] predict(double[] inputs);
	
	
	public double[] train(double[] errors, double learningRate, double momentum);
	
}
