package com.github.marcoral.versatia.core.api.tools.functions;

public interface ThrowingRunnable<E extends Throwable> {
	/**
	 * @throws E Exception of expected type
	 */
	void run() throws E;
}