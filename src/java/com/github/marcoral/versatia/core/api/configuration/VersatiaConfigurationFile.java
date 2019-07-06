package com.github.marcoral.versatia.core.api.configuration;


public interface VersatiaConfigurationFile {
	/**
	 * @return <code>true</code> if underlying config exists, <code>false</code> otherwise
	 */
    boolean exists();
    
	/**
	 * Saves changes. Creates file if neccessary.
	 */
    void saveData();
    
    /**
     * @return Returns object which will help with processing config
     */
    VersatiaConfigurationProcessor getProcessor();
}