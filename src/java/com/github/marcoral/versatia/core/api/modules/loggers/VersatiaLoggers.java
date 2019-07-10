package com.github.marcoral.versatia.core.api.modules.loggers;

import com.github.marcoral.versatia.core.api.VersatiaConstants;

public class VersatiaLoggers {
	public static VersatiaLogger getDefaultLogger() {
		return VersatiaConstants.VERSATIA.getLogger("VersatiaLogger");
	}
}