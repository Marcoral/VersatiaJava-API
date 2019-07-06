package com.github.marcoral.versatia.core.api.tools;

import com.github.marcoral.versatia.core.api.colors.VersatiaColor;
import com.github.marcoral.versatia.core.api.configuration.VersatiaConfigurationFile;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import com.github.marcoral.versatia.core.api.VersatiaConstants;

public abstract class VersatiaTools {
	/**
	 * Injects value to field annotated with {@link com.github.marcoral.versatia.core.api.tools.ExternalDependency}
	 * @param <T> expected type of returned value
	 * @param clazz Class containing annotated field
	 * @param instance Instance to inject field to
	 * @param keyOfExternal Annotation value
	 * @param value Value to inject
	 * @param fieldSurelyExists Whether you are sure that field exists
	 * @throws RuntimeException Thrown if <code>fieldSurelyExists</code> parameter is set to <code>true</code>, but no field have been found
	 */
    public static <T> void injectExternalDependency(Class<? super T> clazz, T instance, String keyOfExternal, Object value, boolean fieldSurelyExists) throws RuntimeException {
        getInstance().injectExternalDependencyImpl(clazz, instance, keyOfExternal, value, fieldSurelyExists);
    }

    /**
     * @param filePath Path to file
     * @return {@link com.github.marcoral.versatia.core.api.configuration.VersatiaConfigurationFile} object to process configuration file
     */
    public static VersatiaConfigurationFile searchForConfigurationFile(String filePath) {
        return getInstance().searchForConfigurationFileImpl(filePath);
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
    @ExternalDependency("INSTANCE")
    private static VersatiaTools INSTANCE = null;

    private static VersatiaTools getInstance() {
        if (INSTANCE == null)
            throw new RuntimeException(VersatiaConstants.NOT_LOADED_YET);
        return INSTANCE;
    }

    protected abstract <T> void injectExternalDependencyImpl(Class<? super T> clazz, T instance, String keyOfExternal, Object value, boolean fieldSurelyExists) throws RuntimeException;
    protected abstract VersatiaConfigurationFile searchForConfigurationFileImpl(String filePath);    
    protected abstract void unpackFilesImpl(Plugin module, boolean overrideExisting);
}