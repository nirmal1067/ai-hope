package org.ai.hope.rl;

public class ValueBasedLearning {

	private double[][] value;
	private double[][] rewards;
	private double[][] policy;

	private double learningRate;

	private double acceptedError;

	public ValueBasedLearning(int r, int c,double lr ,double error) {
		value = new double[r][c];
		policy = new double[r][c];
		learningRate = lr;
		acceptedError=error;
	}

	public void initializeRewards(double[][] array) {
		if (array == null || array.length <= 0) {
			throw new RuntimeException("Please pass proper value");
		}

		rewards = array;
	}

	public void buildModel() {
		boolean errorLarge = true;
		while (errorLarge) {
			errorLarge = false;
			for (int r = 0; r < rewards.length; r++) {
				for (int c = 0; c < rewards[0].length; c++) {
					double previousValue = value[r][c];
					if (!(rewards[r][c] == 1 || rewards[r][c] == -1)) {
						double max = 0;
						int move = 1;
						for (int i = 1; i < 5; i++) {
							double mValue = moveValue(r, c, i);

							if (mValue > max) {
								move = i;
							}
						}

						double result = rewards[r][c] + learningRate * max;

						double diff = result - previousValue;
						value[r][c] = result;
						policy[r][c]=move;

						if (diff > acceptedError) {
							errorLarge = true;
						}
					}
				}
			}
		}

	}
	
	
	public int moveDirection(int r ,int c)
	{
		return (int) policy[r][c];
	}

	private double moveValue(int r, int c, int move) {
		if (move == 1) {
			if (r == 0) {
				return 0;
			} else {
				r = r - 1;
			}
		} else if (move == 2) {
			if (r == rewards[0].length) {
				return 0;
			} else {
				r = r + 1;
			}
		} else if (move == 3) {
			if (c == 0) {
				return 0;
			} else {
				c = c - 1;
			}
		} else {
			if (c == rewards.length) {
				return 0;
			} else {
				c = c + 1;
			}
		}

		return value[r][c];
	}
}
