package com.github.marcoral.versatia.core.api.modules.submodules;

import java.util.Set;

public interface VersatiaModuleReloadResult {
	/**
	 * @return Set of names of submodules successfully reloaded
	 */
    Set<String> getReloadedSubmodulesNames();
    
    /**
     * @return Submodule names which were requested to be reloaded, but were not found 
     */
    Set<String> getUnknownSubmodulesNames();
}