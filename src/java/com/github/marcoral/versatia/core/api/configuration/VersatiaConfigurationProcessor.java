package com.github.marcoral.versatia.core.api.configuration;

import java.util.List;


import org.bukkit.configuration.ConfigurationSection;

public interface VersatiaConfigurationProcessor extends ConfigurationSection {
	/**
	 * Calls to {@link ConfigurationSection#getBoolean(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getBoolean(String)}
	 */
    boolean getBooleanOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getBooleanList(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getBooleanList(String)}
	 */
    List<Boolean> getBooleanListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getByteList(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getByteList(String)}
	 */
    List<Byte> getByteListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getCharacterList(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getCharacterList(String)}
	 */
    List<Character> getCharacterListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getDouble(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getDouble(String)}
	 */
    double getDoubleOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getDoubleList(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getDoubleList(String)}
	 */
    List<Double> getDoubleListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getFloatList(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getFloatList(String)}
	 */
    List<Float> getFloatListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getInt(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getInt(String)}
	 */
    int getIntOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getIntegerList(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getIntegerList(String)}
	 */
    List<Integer> getIntegerListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getLong(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getLong(String)}
	 */
    long getLongOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getLongList(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getLongList(String)}
	 */
    List<Long> getLongListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getString(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getString(String)}
	 */
    String getStringOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getStringList(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getStringList(String)}
	 */
    List<String> getStringListOrThrow(String key, String exceptionMessage);

    /**
	 * Calls to {@link ConfigurationSection#getConfigurationSection(String)}.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getConfigurationSection(String)}
	 */
    VersatiaConfigurationProcessor moveToSectionOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getConfigurationSection(String)} and applies it result to {@link org.bukkit.ChatColor#translateAlternateColorCodes(char, String) translateAlternateColorCodes('&amp;' result)} method.
	 * Throws {@link java.lang.NullPointerException} if path is not found.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getBoolean(String)}
	 */
    String getColoredStringOrThrow(String key, String exceptionMessage);

	/**
	 * Calls to {@link #getColoredStringOrThrow(String, String) getColoredStringOrThrow(key, exceptionMessage)} and replaces every {@link com.github.marcoral.versatia.core.api.colors.VersatiaColor} reference key with corresponding translation.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getBoolean(String)}
	 */
    String getVersatiaStringOrThrow(String key, String exceptionMessage);
}