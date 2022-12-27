package org.ai.hope.alogrithm.runner;

import static org.junit.Assert.assertTrue;

import org.ai.hope.core.LinearRegression;
import org.ai.hope.core.QLearning;
import org.ai.hope.core.util.CSVParser;

public class LinearRegressionRunner {
	
	private static LinearRegression linearRegression;

	public static void trainModel() {
		CSVParser csvParser = new CSVParser("C:\\\\Codebase\\\\DataSet\\\\Fish.csv", 1, 5, 6);
		csvParser.parse();
		
		double[][] trainSet = csvParser.trainData();
		double[] result = csvParser.results();

		linearRegression = new LinearRegression(trainSet[0].length, 1);
		
		linearRegression.train(trainSet, result);
		// Logger.printMatrix3D(learning.getqValues());
	}

	public static void main(String[] args) {
		trainModel();
		//testRandom();
	}

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

}
