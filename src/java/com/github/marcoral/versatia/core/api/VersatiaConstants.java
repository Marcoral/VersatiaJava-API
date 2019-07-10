package com.github.marcoral.versatia.core.api;

import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.tools.ExternalDependency;

public class VersatiaConstants {
    public static final String NOT_LOADED_YET = "This element is not available yet! Have you added VersatiaCore as the dependency?";
    
    @ExternalDependency("pluginName")
    public static final String VERSATIA_CORE_NAME = null;
    
    @ExternalDependency("versatiaModule")
    public static final VersatiaModule VERSATIA = null;
    
    public static class Path {
    	public static final String MESSAGES_DIRECTORY = "messages";
    	public static final String LOGGERS_DIRECTORY = "loggers";
    }
}