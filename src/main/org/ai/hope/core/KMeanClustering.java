package org.ai.hope.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class KMeanClustering {
	private List<Double[]> featuresData;

	private HashMap<String, List<Double[]>> clusters;

	private HashMap<String, Double[]> clusterCentroidValues;

	private int numberOfClusters;

	private int numberOfFeatures;

	private int numberEpochs = 1000;

	public KMeanClustering(List<Double[]> featuresData, int numberOfClusters, int numberOfFeatures) {
		this.featuresData = featuresData;
		this.numberOfClusters = numberOfClusters;
		this.numberOfFeatures = numberOfFeatures;
		this.initializeModel();

	}

	public HashMap<String, Double[]> buildCluster() {
		initialClustersCreation();
		return refiningKClusters();
	}

	public int getRandomNumberUsingNextInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	/*
	 * This method creates k clusters and initialize their value to initial random
	 * values
	 * 
	 */
	private void initializeModel() {
		clusters = new HashMap<>();
		clusterCentroidValues = new HashMap<>();
		for (int i = 0; i < numberOfClusters; i++) {
			List<Double[]> list = new ArrayList<>();
			int randomIndex = getRandomNumberUsingNextInt(0, featuresData.size());
			Double[] initialValues = featuresData.get(randomIndex);
			String clusterId = "" + i;
			clusterCentroidValues.put(clusterId, initialValues);
			System.out.println(" Initial  centroid value for Cluster: " + clusterId + " Values : "
					+ Arrays.toString(initialValues));
			clusters.put(clusterId, list);
		}
	}

	/*
	 * This method classify data points into cluster for first time .After this
	 * method execution will have datapoints classified/divided into initial k
	 * clusters.
	 */
	public void initialClustersCreation() {
		coreClassifyingLogic(featuresData);
	}

	/*
	 * This is core processing logic in which we keep iterating over data set and
	 * keep classifying them based on euclidean distance from centroid of cluster.
	 * The centroid of cluster also keeps getting changed based on mean datapoints
	 * in that cluster. This is important step to keep cluerer improving. The
	 * process stops when we reach number of epochs defined . The stopping logic can
	 * be driven by number epochs , centroid values not changing substancially ,
	 * data points not moving across cluster . For simiplicity sake epoch has been
	 * used.
	 */
	private HashMap<String, Double[]> refiningKClusters() {
		while (keepOnClassifying()) {
			for (String key : clusters.keySet()) {
				List<Double[]> tempfeaturesData = clusters.get(key);

				// This is move cluster value based on mean of the data points in the cluster to
				// improve clustering further
				// TODO this could be easily done in core processing method for code reuse
				// perspective
				Double[] clusterValues = clusterCentroidValues.get(key);
				Double[] newCentriodValues = new Double[clusterValues.length];
				Arrays.fill(newCentriodValues, 0.0d);
				for (int i = 0; i < tempfeaturesData.size(); i++) {
					Double[] data = tempfeaturesData.get(i);
					for (int j = 0; j < newCentriodValues.length; j++) {
						newCentriodValues[j] = newCentriodValues[j] + data[j];
					}
				}

				// TODO this can be easily done in previous loop from code optimization
				// perspective
				for (int i = 0; i < newCentriodValues.length; i++) {
					newCentriodValues[i] = newCentriodValues[i] / tempfeaturesData.size();
				}

				System.out.println(" Old centroid value for Cluster: " + key + " Old Value : "
						+ Arrays.toString(clusterValues) + " New Values: " + Arrays.toString(newCentriodValues)
						+ " Previous Number of datapoints in this cluster: " + tempfeaturesData.size());

				// Reassign new mean values to centroids based on mean calculation done above.
				clusterCentroidValues.put(key, newCentriodValues);

				// This is method does reassignment of Cluster based on eulucdean distance
				coreClassifyingLogic(tempfeaturesData, key);

			}

		}

		return clusterCentroidValues;
	}

	/*
	 * 
	 */
	private boolean keepOnClassifying() {

		if (numberEpochs <= 0) {
			return false;
		}
		numberEpochs = numberEpochs - 1;
		// TODO Add logic to consider other parameter lik clsutercentroid value not
		// changes and cluser data point not shuffling between clusters
		return true;

	}

	/*
	 * 
	 */
	private void coreClassifyingLogic(List<Double[]> tempfeaturesData) {
		for (int i = 0; i < tempfeaturesData.size(); i++) {
			Double euclideanDistance = Double.MAX_VALUE;
			String destinationCluster = "";
			Double[] data = tempfeaturesData.get(i);

			for (String clusterKey : clusterCentroidValues.keySet()) {
				Double[] clusterCentroids = clusterCentroidValues.get(clusterKey);
				Double sumSquareDistance = 0.00d;
				for (int k = 0; k < data.length; k++) {

					Double tempDistance = (clusterCentroids[k] - data[k]);
					Double squareDistance = Math.pow(tempDistance, 2);
					sumSquareDistance = sumSquareDistance + squareDistance;
				}

				Double clusterSpecificDistance = Math.sqrt(sumSquareDistance);

				if (clusterSpecificDistance.compareTo(euclideanDistance) < 0) {
					euclideanDistance = clusterSpecificDistance;
					destinationCluster = clusterKey;
				}
			}

			List<Double[]> classifiedData = clusters.get(destinationCluster);

			if (classifiedData == null) {
				classifiedData = new ArrayList<>();
			}

			classifiedData.add(data);
		}
	}

	/*
	 * 
	 */
	private void coreClassifyingLogic(List<Double[]> tempfeaturesData, String previousKey) {

		List<Integer> indexToBeRemoved = new ArrayList<>();
		for (int i = 0; i < tempfeaturesData.size(); i++) {
			Double euclideanDistance = Double.MAX_VALUE;
			String destinationCluster = "";
			Double[] data = tempfeaturesData.get(i);

			for (String clusterKey : clusterCentroidValues.keySet()) {
				Double[] clusterCentroids = clusterCentroidValues.get(clusterKey);
				Double sumSquareDistance = 0.00d;
				for (int k = 0; k < data.length; k++) {

					Double tempDistance = (clusterCentroids[k] - data[k]);
					Double squareDistance = Math.pow(tempDistance, 2);
					sumSquareDistance = sumSquareDistance + squareDistance;
				}

				Double clusterSpecificDistance = Math.sqrt(sumSquareDistance);

				if (clusterSpecificDistance.compareTo(euclideanDistance) < 0) {
					euclideanDistance = clusterSpecificDistance;
					destinationCluster = clusterKey;
				}
			}

			if (!destinationCluster.equalsIgnoreCase(previousKey)) {
				List<Double[]> classifiedData = clusters.get(destinationCluster);

				if (classifiedData == null) {
					classifiedData = new ArrayList<>();
				}

				classifiedData.add(data);
				// Cannot remove index in same iteration because of concurrent modification
				// exception
				indexToBeRemoved.add(i);
			}

		}

		for (int i = 0; i < indexToBeRemoved.size(); i++) {
			tempfeaturesData.remove(i);
		}

	}

	public String predictCategory(Double[] data) {
		Double euclideanDistance = Double.MAX_VALUE;
		String destinationCluster = "";

		for (String clusterKey : clusterCentroidValues.keySet()) {
			Double[] clusterCentroids = clusterCentroidValues.get(clusterKey);
			Double sumSquareDistance = 0.00d;
			for (int k = 0; k < data.length; k++) {

				Double tempDistance = (clusterCentroids[k] - data[k]);
				Double squareDistance = Math.pow(tempDistance, 2);
				sumSquareDistance = sumSquareDistance + squareDistance;
			}

			Double clusterSpecificDistance = Math.sqrt(sumSquareDistance);

			if (clusterSpecificDistance.compareTo(euclideanDistance) < 0) {
				euclideanDistance = clusterSpecificDistance;
				destinationCluster = clusterKey;
			}
		}

		return destinationCluster;
	}

	public static void main(String[] args) {
		// Data set
		List<Double[]> dataSet = new ArrayList<>();

		Double[] data1 = { .23d, .34d, .67d };
		Double[] data2 = { .23d, .84d, .47d };
		Double[] data3 = { .21d, .64d, .97d };
		Double[] data4 = { .13d, .84d, .899d };

		dataSet.add(data4);
		dataSet.add(data3);
		dataSet.add(data2);
		dataSet.add(data1);

		KMeanClustering clustering = new KMeanClustering(dataSet, 2, 3);

		HashMap<String, Double[]> clusterCentroidValues = clustering.buildCluster();

		clusterCentroidValues.forEach((k, val) -> {

			System.out.println(" ClusterId/CategoryId: " + k + " Centriod values: " + Arrays.toString(val));
		});

		// Test with new point
		Double[] data5 = { .131d, .841d, .89d };

		String category = clustering.predictCategory(data5);

		System.out.println(category);

	}

}
