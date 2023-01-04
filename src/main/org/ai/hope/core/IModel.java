package org.ai.hope.core;

import java.util.Optional;

public interface IModel {
	
	public Optional<Double> predict(double[] inputs);
	
	public void trainSGD(double[][] trainData, double[] result);
	
	public void trainBGD(double[][] trainData, double[] result);
	
	public void trainMGD(double[][] trainData, double[] result);

}
