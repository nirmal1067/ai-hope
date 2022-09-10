package org.ai.hope.nn;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Functions {

	public static Function<Double, Double> SIGMOID = (n) -> {

		return (Double) (1 / (1 + Math.exp(-n)));
	};

	public static BiFunction<Double[], Double[], Double[]> DEEP_COPY = (s, d) -> {

		System.arraycopy(s, 0, d, 0, d.length);
		return d;
	};
	
	public static Function<Double, Double> DSIGMOID = (x) -> {

		return x*(1-x);
	};
	
	public static BiFunction<Double, Double, Double> ADDITION = (s, d) -> {

		return s+d;
	};
	
	public static BiFunction<Double, Double, Double> SUBSTRACTION = (s, d) -> {

		return s-d;
	};
	
	public static BiFunction<Double, Double, Double> MULTIPLY = (s, d) -> {

		return s*d;
	};

	
}
