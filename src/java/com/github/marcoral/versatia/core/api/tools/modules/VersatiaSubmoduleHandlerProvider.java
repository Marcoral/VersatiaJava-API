package com.github.marcoral.versatia.core.api.tools.modules;

import org.bukkit.plugin.Plugin;

import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaSubmodule;

public interface VersatiaSubmoduleHandlerProvider {	
	/**
	 * @return Returns registerer of this handler
	 */
	Plugin getRegisterer();
	
	/**
	 * Creates handler for given module.
	 * @param module Module
	 * @return Submodule handler created for {@code module}
	 */
	VersatiaSubmodule createHandler(VersatiaModule module);
}