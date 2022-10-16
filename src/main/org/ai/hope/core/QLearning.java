package org.ai.hope.core;

import java.util.Random;

public class QLearning {

	private double[][][] qValues;

	private double[][][] rValues;

	private double learningRate;

	private double gamma;

	private int numberOfAction;

	private double[][] actualStates;

	/*
	 * 0-left 1-up 2-right 3-down
	 */

	public QLearning(int numberOfActions, int statesRow, int statesColumns, double learningRate, double gamma,
			double[][] actualStates) {
		qValues = new double[statesRow][statesColumns][numberOfActions];
		rValues = new double[statesRow][statesColumns][numberOfActions];
		this.learningRate = learningRate;
		this.numberOfAction = numberOfActions;
		this.gamma = gamma;
		this.actualStates = actualStates;
	}

	public void train(int episodes) {
		initializeRewards(actualStates);

		for (int i = 0; i < episodes; i++) {
			int r = randomValue(0, actualStates.length);
			int c = randomValue(0, actualStates[0].length);
			System.out.println("Episodes count " + i);
			int a;

			do {
				int[] states = findNextState(r, c);
				a = states[0];
				double qs = qValues[r][c][a];
				qs = qs + learningRate * (rValues[r][c][a] + (gamma * findMax(r, c)) - qs);
				qValues[r][c][a] = qs;
				r = states[1];
				c = states[2];
			} while (isNotTerminalState(r, c, a));

		}

	}

	/*
	 * 0-left 1-up 2-right 3-down
	 */

	private int[] findNextState(int tr, int tc) {
		// TODO Auto-generated method stub
		int a;
		int[] states = new int[3];
		boolean notValid = true;

		while (notValid) {
			a = randomValue(0, numberOfAction);
			int r = tr;
			int c = tc;
			
			if (a == 0) {
				c = c - 1;
			} else if (a == 1) {
				r = r - 1;
			} else if (a == 2) {
				c = c + 1;
			} else {
				r = r + 1;
			}

			if ((c >= 0 && c < qValues[0].length) && (r >= 0 && r < qValues.length)) {
				notValid = false;
				states[1] = r;
				states[2] = c;
				states[0] = a;
			}
		}
		return states;
	}

	private double findMax(int r, int c) {
		double max = 0;
		for (int i = 0; i < numberOfAction; i++) {
			if (qValues[r][c][i] > max) {
				max = qValues[r][c][i];
			}
		}
		return max;
	}

	private boolean isNotTerminalState(int r, int c, int a) {
		// TODO Auto-generated method stub
		return rValues[r][c][a] != 1 ? true : false;
	}

	private int randomValue(int inclusiveLow, int exclusiveHigh) {
		Random r = new Random();
		int low = inclusiveLow;
		int high = exclusiveHigh;
		int result = r.nextInt(high - low) + low;
		return result;
	}

	private void initializeRewards(double[][] actualStates) {
		for (int r = 0; r < actualStates.length; r++) {
			for (int c = 0; c < actualStates[0].length; c++) {
				if (actualStates[r][c] != 0) {
					// for left condition and it will be right for r values matrix
					if (r - 1 >= 0) {
						rValues[r - 1][c][3] = actualStates[r][c];
					}

					// for top condition and it will down condition for r value matrix

					if (c - 1 >= 0) {
						rValues[r][c - 1][2] = actualStates[r][c];
					}

					if (c + 1 < actualStates[0].length) {
						rValues[r][c + 1][0] = actualStates[r][c];
					}

					if (r + 1 < actualStates.length) {
						rValues[r + 1][c][1] = actualStates[r][c];
					}

				}
			}
		}
	}

	public double[][][] getqValues() {
		return qValues;
	}

	public void setqValues(double[][][] qValues) {
		this.qValues = qValues;
	}

}
