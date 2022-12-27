package org.ai.hope.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {

	private String filePath;

	private double[][] dataSet;

	private double[] result;

	private int startIndex;

	private int endIndex;

	private int resultIndex;

	public CSVParser(String path, int startIndex, int endIndex, int resultIndex) {
		this.filePath = path;
		this.resultIndex = resultIndex;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public void parse() {
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

			// CSV file delimiter
			String DELIMITER = ",";

			List<double[]> listData = new ArrayList<>();

			List<Double> listResult = new ArrayList<>();
			// read the file line by line
			String line;
			boolean isHeader = true;
			while ((line = br.readLine()) != null) {

				// convert line into columns
				String[] columns = line.split(DELIMITER);
				if (isHeader) {
					System.out.println(columns[0]);
					isHeader = false;
					continue;
				}

				double[] data = new double[(endIndex - startIndex) + 1];

				int index = 0;
				for (int i = startIndex; i <= endIndex; i++) {
					data[index] = Double.parseDouble(columns[i]);
					index = index + 1;
				}

				listData.add(data);
				listResult.add(Double.parseDouble(columns[resultIndex]));

				// print all columns
				System.out.println(Arrays.toString(data));
			}

			dataSet = new double[listData.size()][(endIndex - startIndex) + 1];
			result = new double[listResult.size()];

			for (int i = 0; i < listData.size(); i++) {
				dataSet[i] = listData.get(i);
				result[i] = listResult.get(i);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public double[][] trainData() {
		return dataSet;
	}

	public double[] results() {
		return result;
	}

	public static void main(String[] args) {
		CSVParser csvParser = new CSVParser("C:\\\\Codebase\\\\DataSet\\\\Fish.csv", 1, 5, 6);
		csvParser.parse();
	}
}
