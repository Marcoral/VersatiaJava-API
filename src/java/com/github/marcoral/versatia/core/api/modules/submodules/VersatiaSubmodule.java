package com.github.marcoral.versatia.core.api.modules.submodules;

public interface VersatiaSubmodule {
	/**
	 * Reloads the submodule
	 */
    void reload();
    
    /**
     * Makes submodule cleanup
     */
    default void shutdown() {}	//Hook
}