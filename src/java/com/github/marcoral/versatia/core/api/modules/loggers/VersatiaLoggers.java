package com.github.marcoral.versatia.core.api.modules.loggers;

import com.github.marcoral.versatia.core.api.tools.ExternalDependency;

public class VersatiaLoggers {
	public static VersatiaLogger getDefaultLogger() {
		return PRIMARY_LOGGER;
	}
	
	/* -------
     * BOILERPLATE
     * -------- */
    @ExternalDependency("PRIMARY_LOGGER")
    private static VersatiaLogger PRIMARY_LOGGER = null;
}