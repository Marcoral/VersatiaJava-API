package com.github.marcoral.versatia.core.api.tools.functions;

public interface ThrowingConsumer<V, E extends Throwable> {
	/**
	 * @param v Input
	 * @throws E Exception of expected type
	 */
	void accept(V v) throws E;
}