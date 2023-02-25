package org.ai.hope.alogrithm.runner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		// int[][] array = {{1,2,3},{0,2},{0,1,3},{0,2}};
		// int[][] array = { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } };

		int[][] array = { {}, { 3 }, {}, { 1 }, {} };

		System.out.println(isBipartite(array));
	}

	public static boolean isBipartite(int[][] graph) {

		if (graph == null || graph.length <= 0) {
			return false;
		}

		Queue<Integer> q = new LinkedList<>();
		HashSet<Integer> blue = new HashSet<>();
		HashSet<Integer> red = new HashSet<>();
		boolean flag = true;
		// [[1,3],[0,2],[1,3],[0,2]]
		for (int i = 0; i < graph.length; i++) {
			if (!blue.contains(i) && !red.contains(i)) {

				boolean[] v = new boolean[graph.length];
				q.offer(i);
				blue.add(i);
				while (q.size() > 0 && flag) {
					System.out.println("Outer Index " + i);
					System.out.println(q);
					int index = q.poll();
					v[index] = true;
					for (int r = 0; r < graph[index].length; r++) {
						if (!v[graph[index][r]]) {
							if ((blue.contains(index) && blue.contains(graph[index][r]))
									|| (red.contains(index) && red.contains(graph[index][r]))) {
								flag = false;
								// System.out.print( " Break " + Arrays.toString(graph[index]) + " Index " +
								// index);
								break;
							}

							if (blue.contains(index)) {
								q.offer(graph[index][r]);
								red.add(graph[index][r]);
							}

							if (red.contains(index)) {
								q.offer(graph[index][r]);
								blue.add(graph[index][r]);
							}

						}
					}
				}
			}

		}

		System.out.println(blue);
		System.out.println(red);
		return flag;
	}

}
