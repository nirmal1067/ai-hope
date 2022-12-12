package org.ai.hope.alogrithm.runner;

import org.ai.hope.rl.ValueBasedLearning;

public class ValueBasedLearningRunner {

	public static void main(String[] args)
	{
		ValueBasedLearning basedLearning = new ValueBasedLearning(5, 5, 0.01, 1.0);
		
		basedLearning.buildModel();
	}
}
