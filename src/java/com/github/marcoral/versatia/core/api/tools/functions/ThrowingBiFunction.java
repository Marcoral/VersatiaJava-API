package com.github.marcoral.versatia.core.api.tools.functions;

public interface ThrowingBiFunction<V1, V2, T, E extends Throwable> {
	/**
	 * @param v1 Input 1
	 * @param v2 Input 2
	 * @return Output of expected type
	 * @throws E Exception of expected type
	 */
	T apply(V1 v1, V2 v2) throws E;
}