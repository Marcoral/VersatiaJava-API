package com.github.marcoral.versatia.core.api.tools.functions;

public interface ThrowingBiConsumer<V1, V2, E extends Throwable> {
	/**
	 * @param v1 Input 1
	 * @param v2 Input 2
	 * @throws E Exception of expected type
	 */
	void accept(V1 v1, V2 v2) throws E;
}