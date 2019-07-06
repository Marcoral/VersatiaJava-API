package com.github.marcoral.versatia.core.api;

import com.github.marcoral.versatia.core.api.tools.ExternalDependency;

public class VersatiaConstants {
    public static String NOT_LOADED_YET = "This element is not available yet! Have you added VersatiaCore as the dependency?";
    
    @ExternalDependency("pluginName")
    public static String VERSATIA_CORE_NAME = "VersatiaCore";
}