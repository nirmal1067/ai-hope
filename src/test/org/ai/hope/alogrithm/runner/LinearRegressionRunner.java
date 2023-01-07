package org.ai.hope.alogrithm.runner;

import static org.junit.Assert.assertTrue;

import org.ai.hope.core.LinearRegression;
import org.ai.hope.core.QLearning;
import org.ai.hope.core.util.CSVParser;

public class LinearRegressionRunner {
	
	private static LinearRegression linearRegression;

	// Tested with sample data at below link 
	// https://college.cengage.com/mathematics/brase/understandable_statistics/7e/students/datasets/slr/frames/frame.html
	public static void trainModel1() {
		CSVParser csvParser = new CSVParser("C:\\\\Codebase\\\\DataSet\\\\sample.csv", 0, 0, 1);
		csvParser.parse();
		
		double[][] trainSet = csvParser.trainData();
		double[] result = csvParser.results();

		linearRegression = new LinearRegression(trainSet[0].length, 1000);
		
		linearRegression.train(trainSet, result);
		// Logger.printMatrix3D(learning.getqValues());
	}

//	public static void main(String[] args) {
//		//trainModel();
//		trainModel1();
//		//testRandom();
//	}

	private static void testRandom() {
		double[][] trainingData = new double[][] { new double[] { 0, 0, 0, 1 }, new double[] { 0, -1, 0, 0 },
				new double[] { 0, 0, 0, -1 }, new double[] { 0, 0, 0, 0 } };

		QLearning learning = new QLearning(4, 4, 4, .99, 0.05, trainingData);

		for (int i = 0; i < 20; i++) {
			int value = learning.randomValue(0, 4);
			// System.out.println(value);
			assertTrue(value < 4);
		}

	}
	
	private static void trainModel()
	{
		double[][] trainSet = {{20},{16},{19.8},{18.4},{17.1},{15.5}};
		double[] result = {88.6,71.6,93.3,84.3,80.6,75.2};
		LinearRegression linearRegression = new LinearRegression(trainSet[0].length, 1000);
		linearRegression.train(trainSet, result);
		
	}
	
	public static void main(String[] args) {
		//trainModel();
		trainModel1();
		//testRandom();
	}

}
