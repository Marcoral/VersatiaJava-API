package com.github.marcoral.versatia.core.api.tools;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import com.github.marcoral.versatia.core.api.VersatiaConstants;
import com.github.marcoral.versatia.core.api.colors.VersatiaColor;
import com.github.marcoral.versatia.core.api.configuration.VersatiaConfigurationFile;
import com.github.marcoral.versatia.core.api.configuration.VersatiaConfigurationProcessor;
import com.github.marcoral.versatia.core.api.tools.modules.ModuleDevelopmentTools;

public abstract class VersatiaTools {
    /**
     * @param file File
     * @return {@link com.github.marcoral.versatia.core.api.configuration.VersatiaConfigurationFile} object to process configuration file
     */
    public static VersatiaConfigurationFile searchForConfigurationFile(File file) {
        return getInstance().searchForConfigurationFileImpl(file);
    }
    
    /**
     * Decorates {@link ConfigurationSection} with Versatia wrapper.
     * @param configurationSection Configuration section to wrap
     * @return Configuration section decorated by Versatia wrapper
     */
    public static VersatiaConfigurationProcessor wrapConfigurationToVersatiaProcessor(ConfigurationSection configurationSection) {
    	return getInstance().wrapConfigurationToVersatiaProcessorImpl(configurationSection);
    }

	/**
     * Unpacks files from "resources" directory of plugin jar
     * @param module Bukkit plugin instance
     * @param overrideExisting Whether existing configuration should be overwritten
     */
    public static void unpackFiles(Plugin module, boolean overrideExisting) {
        getInstance().unpackFilesImpl(module, overrideExisting);
    }

    /**
     * Convenience method. You should use it over {@link ChatColor#translateAlternateColorCodes(char, String)}, as it would make VersatiaCore more consistent
     * @param input Input String
     * @return Colored String
     */
    public static String getColoredString(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    /**
     * Replaces every {@link VersatiaColor} reference key with corresponding translation and calls to {@link #getColoredString(String) getColoredString(input)} afterwards.
     * @param input Input String
     * @return Versatia-like String
     */
    public static String getVersatiaString(String input) {
        for(VersatiaColor color : VersatiaColor.values())
            input = input.replace(color.getReferenceKey(), color.toString());
        return getColoredString(input);
    }

    /* -------
     * BOILERPLATE
     * -------- */
    @ExternalDependency(ModuleDevelopmentTools.INSTANCE_KEY)
    private static VersatiaTools INSTANCE = null;

    private static VersatiaTools getInstance() {
        if (INSTANCE == null)
            throw new RuntimeException(VersatiaConstants.NOT_LOADED_YET);
        return INSTANCE;
    }
    
    public static class NoBridgeFieldException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public NoBridgeFieldException(String fieldKey, Class<?> containingClass) {
    		super(String.format("No external field with key %s in class %s have been found!", fieldKey, containingClass));
    	}
    }

    protected abstract VersatiaConfigurationFile searchForConfigurationFileImpl(File file);
    protected abstract VersatiaConfigurationProcessor wrapConfigurationToVersatiaProcessorImpl(ConfigurationSection configurationSection);
    protected abstract void unpackFilesImpl(Plugin module, boolean overrideExisting);
}