package org.ai.hope.alogrithm.runner;

import java.util.ArrayList;
import java.util.List;

import org.ai.hope.core.DecesionTree;

public class DecesionTreeRunner {


	
	public static void main(String[] args)
	{
		List<Double[]> dataSet = new ArrayList<>();

		Double[] data1 = { .23d, .34d, .67d ,0.1d};
		Double[] data2 = { .23d, .84d, .47d ,0.1d};
		Double[] data3 = { .21d, .64d, .97d ,0.1d};
		Double[] data4 = { .13d, .84d, .47d ,0.2d};
		Double[] data5 = { .13d, .88d, .99d ,0.2d};
		
		dataSet.add(data4);
		dataSet.add(data3);
		dataSet.add(data2);
		dataSet.add(data1);
		dataSet.add(data5);
		
		DecesionTree tree = new DecesionTree();
		tree.buildTree(dataSet, data1.length-1);
		System.out.println("Tree building completed");
	}

}
