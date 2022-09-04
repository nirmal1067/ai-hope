package org.ai.hope.nn;

public class NeuralNetwork implements INeuralNetwork {

	private ILayer[] layers;

	public NeuralNetwork(int inputs, int hiddenLayers, int[] hiddeLayerSizes, int outPuts) {
		layers = new Layer[hiddenLayers + outPuts];

		// Initialize neural network with proper layers
		for (int i = 0; i < layers.length; i++) {
			if (i == 0) {
				layers[i] = new Layer(inputs, hiddeLayerSizes[i]);
				continue;
			}

			if (i == layers.length - 1) {
				layers[i] = new Layer(hiddeLayerSizes[i - 1], outPuts);
				continue;
			}

			layers[i] = new Layer(hiddeLayerSizes[i - 1], hiddeLayerSizes[i]);

		}

	}
	
	public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
		layers = new Layer[2];
		layers[0] = new Layer(inputSize, hiddenSize);
		layers[1] = new Layer(hiddenSize, outputSize);
	}

	public double[] predict(double[] input) {
		// TODO Auto-generated method stub
		double[] outPut = input;
		for (int i = 0; i < layers.length; i++) {
			outPut = layers[i].predict(outPut);
		}
		return outPut;
	}

	public void train(double[] inputs, double[] expectedOutPuts, double learningRate, double momentum) {
		// TODO Auto-generated method stub

		double[] predictedValues = predict(inputs);

		double[] errors = new double[inputs.length];

		for (int i = 0; i < expectedOutPuts.length; i++) {
			errors[i] =  expectedOutPuts[i]-predictedValues[i] ;
		}

		for (int i = layers.length - 1; i >= 0; i--) {
			errors = layers[i].train(errors, learningRate, momentum);
		}

	}

}
