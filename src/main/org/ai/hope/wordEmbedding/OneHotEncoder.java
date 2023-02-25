package org.ai.hope.wordEmbedding;

import java.util.*;

public class OneHotEncoder {

	private List<String> inputs;

	private HashSet<String> stopWords;

	public OneHotEncoder(List<String> ins, HashSet<String> words) {
		this.inputs = ins;
		stopWords = words;
	}

	private HashSet<String> uniqueWords = new HashSet<>();

	private List<String[]> wordsWithOutStopWords() {
		List<String[]> list = new ArrayList<>();

		for (int i = 0; i < inputs.size(); i++) {
			String temp = inputs.get(i).toLowerCase();
			String[] words = temp.split(" ");

			for (int j = 0; j < words.length; j++) {
				if (stopWords.contains(words[j])) {
					words[j] = null;
				} else {
					uniqueWords.add(words[j]);
				}
			}

			list.add(words);
		}

		return list;
	}

	private List<String[]> createBigrams(List<String[]> words) {
		List<String[]> list = new ArrayList<>();

		for (int i = 0; i < words.size(); i++) {
			String[] array = words.get(i);

			for (int j = 0; j < array.length; j++) {
				if (array[j] == null) {
					continue;
				}

				for (int k = 0; k < array.length; k++) {
					if (k == j || array[k] == null) {
						continue;
					} else {
						String[] bigrams = new String[2];

						bigrams[0] = array[j];
						bigrams[1] = array[k];
						list.add(bigrams);
					}
				}
			}
		}

		return list;
	}

	public List<Integer[][]> generateBigramsEncoding() {
		List<Integer[][]> doubles = new ArrayList<>();
		List<String[]> temp = wordsWithOutStopWords();
		List<String[]> grams = createBigrams(temp);

		HashMap<String, Integer[]> hashMap = oneHotEncoder();

		System.out.println(hashMap);

		for (int i = 0; i < grams.size(); i++) {
			String[] str = grams.get(i);
			Integer[][] array = new Integer[2][uniqueWords.size()];

			array[0] = hashMap.get(str[0]);
		}
		return doubles;
	}

	public HashMap<String, Integer[]> oneHotEncoder() {
		int i = 0;
		HashMap<String, Integer[]> encoder = new HashMap<>();
		for (String key : uniqueWords) {
			Integer[] values = new Integer[uniqueWords.size()];
			values[i] = 1;
			encoder.put(key, values);
			i = i++;

		}
		return encoder;
	}

	public static void main(String[] args) {
		String str = "The king is strong";
		String str1 = "The queen is beautiful";
		String str2 = "The king is man";
		String str3 = "The queen is woman";
		String str4 = "Thie king is stupid";
		String str5 = "The queen is intelligent";
		String str6 = "Thie king is average";
		String str7 = "The queen is excpetional";

		List<String> words = new ArrayList<>();

		words.add(str);
		words.add(str1);
		words.add(str2);
		words.add(str3);
		words.add(str4);
		words.add(str5);
		words.add(str6);
		words.add(str7);

		HashSet<String> set = new HashSet<>();

		set.add("the");
		set.add("is");
		set.add("a");

		OneHotEncoder encoder = new OneHotEncoder(words, set);
		encoder.generateBigramsEncoding();

	}

}
