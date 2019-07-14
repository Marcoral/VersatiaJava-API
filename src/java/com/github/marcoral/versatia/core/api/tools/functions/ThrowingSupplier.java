package com.github.marcoral.versatia.core.api.tools.functions;

public interface ThrowingSupplier<T, E extends Throwable> {
	/**
	 * @return Output of expected type
	 * @throws E Exception of expected type
	 */
	T get() throws E;
}