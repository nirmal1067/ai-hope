package org.ai.hope.alogrithm.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.ai.hope.core.KMeanClustering;

public class KMeanClusteringRunner {
	
	public static void main(String[] args) {
		// Data set
		List<Double[]> dataSet = new ArrayList<>();

		Double[] data1 = { .23d, .34d, .67d };
		Double[] data2 = { .23d, .84d, .47d };
		Double[] data3 = { .04d, .06d, .08d };
		Double[] data4 = { .04d, .05d, .08d };
		
		dataSet.add(data4);
		dataSet.add(data3);
		dataSet.add(data2);
		dataSet.add(data1);

		KMeanClustering clustering = new KMeanClustering(dataSet, 2, 3);

		HashMap<String, Double[]> clusterCentroidValues = clustering.buildCluster();

		clusterCentroidValues.forEach((k, val) -> {

			System.out.println(" ClusterId: " + k + " Centriod values: " + Arrays.toString(val));
		});
	}

}
