package org.ai.hope.nn;

import java.util.Arrays;
import java.util.Random;

public class Layer implements ILayer {

	private double[] weights;

	private double[] dWeights;

	private double[] input;

	private double[] output;
	
	private Random random = new Random();
	
	public Layer(int inputs,int outputs)
	{
		int size = (1+inputs)*outputs;
		this.input = new double[inputs+1];
		this.output = new double[outputs];
		this.dWeights = new double[size];
		this.weights = new double[size];
		
		for(int i =0;i<weights.length;i++)
		{
			//weights[i] = random.nextdouble();
			weights[i] = (random.nextDouble() - 0.5d) * 4d;
		}
	}
	


	


	public double[] train(double[] errors, double learningRate, double momentum) {
		// TODO Auto-generated method stub
		int offset = 0;
		double[] nextLayerErrors = new double[input.length];
		for (int i = 0; i < output.length; i++) {
			double partialDerivative = errors[i] * Functions.DSIGMOID.apply(output[i]);

			for (int j = 0; j < input.length; j++) {
				// This is for next layer becuse we need to consider it for next layer as well
				// in that it can be
				int previousWeightIndex = offset + j;
				nextLayerErrors[j] = nextLayerErrors[j] + (partialDerivative * weights[previousWeightIndex]);

				// This for for parttial derviate if u check the math then we need to multiple
				// by actual inputs at this stage
				double dw = learningRate * partialDerivative * input[j];

				weights[previousWeightIndex] +=  dw + momentum * dWeights[previousWeightIndex];

				dWeights[previousWeightIndex] = dw;
			}

			offset = offset + input.length;

		}
		return nextLayerErrors;
	}


//	@Override
//	public double[] predict(double[] inputs) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public double[] train(double[] errors, double learningRate, double momentum) {
//		// TODO Auto-generated method stub
//		return null;
//	}



  
  
	public double[] predict(double[] inputs) {
		// TODO Auto-generated method stub

		System.arraycopy(inputs, 0, input, 0, inputs.length);
		input[input.length - 1] = 1;
		int offset = 0;

		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < input.length; j++) {
				output[i] = output[i] + (input[j] * weights[offset + j]);
				
			}
			output[i] = Functions.SIGMOID.apply(output[i]);
			offset = offset+input.length;
		}

		return Arrays.copyOf(output, output.length);
	}

	

}
