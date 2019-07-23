package com.github.marcoral.versatia.core.api.configuration;

import java.io.File;

public interface VersatiaConfigurationFile {
	/**
	 * @return Returns {@link File} described by this object
	 */
	File getUnderlyingFile();
	
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

    /**
     * @return Returns object which will help with processing config.
     * In opposite to {@link #getProcessor()}, it ignores content of underlying file.
     */
	VersatiaConfigurationProcessor getProcessorIgnoreContent();
}