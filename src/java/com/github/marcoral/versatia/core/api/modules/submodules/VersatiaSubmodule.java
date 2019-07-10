package com.github.marcoral.versatia.core.api.modules.submodules;

public interface VersatiaSubmodule {
	/**
	 * @return Submodule name.
	 */
	String getName();
	
	/**
	 * Loads module for the first time.
	 * <b>Should NOT be manually invoked!</b> 
	 */
	void load();
	
	/**
	 * Reloads the submodule.
	 */
    default void reload() {
    	unload();
    	load();
    }
    
    /**
     * Called just before {@link #reload()} method.
     * It's the best place to clear lists/maps.
	 * <b>Should NOT be manually invoked!</b>
     */
    default void unload() {}	//Hook
    
    /**
     * Makes submodule cleanup.
     * It's the best place to close any IO connections.
	 * <b>Should NOT be manually invoked!</b> 
     */
    default void shutdown() {}	//Hook
}