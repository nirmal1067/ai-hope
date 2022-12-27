package org.ai.hope.core;

import java.util.Optional;

public interface IModel {
	
	public Optional<Double> predict(double[] inputs);
	
	public void train(double[][] trainData, double[] result);

}
