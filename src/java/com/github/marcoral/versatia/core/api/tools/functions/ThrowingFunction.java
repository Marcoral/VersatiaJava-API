package com.github.marcoral.versatia.core.api.tools.functions;

public interface ThrowingFunction<V, T, E extends Throwable> {
	/**
	 * @param v Input
	 * @return Output of expected type
	 * @throws E Exception of expected type
	 */
	T apply(V v) throws E;
}