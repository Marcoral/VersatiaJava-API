package com.github.marcoral.versatia.core.api.configuration;

import java.util.List;
import java.util.function.Consumer;

import org.bukkit.configuration.ConfigurationSection;

import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.messages.VersatiaMessageDescriptor;
import com.github.marcoral.versatia.core.api.tools.VersatiaTools;

public interface VersatiaConfigurationProcessor extends ConfigurationSection {
	/**
	 * Calls to {@link ConfigurationSection#getBoolean(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getBoolean(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    boolean getBooleanOrThrow(String key, String exceptionMessage);
    
    /**
     * Convenience method.
     * Checks whether <code>Boolean</code> value exists at given path.
     * If yes, it will perform specified action with the value.
     * @param key Path key
     * @param action Action to take if the value is present
     */
    default void ifBooleanPresent(String key, Consumer<Boolean> action) {
    	if(isBoolean(key))
    		action.accept(getBoolean(key));
    }
    
	/**
     * Convenience method.
	 * Calls to {@link ConfigurationSection#getBooleanList(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getBooleanList(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    List<Boolean> getBooleanListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getByteList(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getByteList(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    List<Byte> getByteListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getCharacterList(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getCharacterList(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    List<Character> getCharacterListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getDouble(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getDouble(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    double getDoubleOrThrow(String key, String exceptionMessage);
    
    /**
     * Convenience method.
     * Checks whether <code>Double</code> value exists at given path.
     * If yes, it will perform specified action with the value.
     * @param key Path key
     * @param action Action to take if the value is present
     */
    default void ifDoublePresent(String key, Consumer<Double> action) {
    	if(isDouble(key))
    		action.accept(getDouble(key));
    }
    
	/**
	 * Calls to {@link ConfigurationSection#getDoubleList(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getDoubleList(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    List<Double> getDoubleListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getFloatList(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getFloatList(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    List<Float> getFloatListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getInt(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getInt(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    int getIntOrThrow(String key, String exceptionMessage);
    
    /**
     * Convenience method.
     * Checks whether <code>Integer</code> value exists at given path.
     * If yes, it will perform specified action with the value.
     * @param key Path key
     * @param action Action to take if the value is present
     */
    default void ifIntPresent(String key, Consumer<Integer> action) {
    	if(isInt(key))
    		action.accept(getInt(key));
    }
    
	/**
	 * Calls to {@link ConfigurationSection#getIntegerList(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getIntegerList(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    List<Integer> getIntegerListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getLong(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getLong(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    long getLongOrThrow(String key, String exceptionMessage);
    
    /**
     * Convenience method.
     * Checks whether <code>Long</code> value exists at given path.
     * If yes, it will perform specified action with the value.
     * @param key Path key
     * @param action Action to take if the value is present
     */
    default void ifLongPresent(String key, Consumer<Long> action) {
    	if(isLong(key))
    		action.accept(getLong(key));
    }
    
	/**
	 * Calls to {@link ConfigurationSection#getLongList(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getLongList(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    List<Long> getLongListOrThrow(String key, String exceptionMessage);
    
	/**
	 * Calls to {@link ConfigurationSection#getString(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getString(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    String getStringOrThrow(String key, String exceptionMessage);
    
    /**
     * Convenience method.
     * Checks whether <code>String</code> value exists at given path.
     * If yes, it will perform specified action with the value.
     * @param key Path key
     * @param action Action to take if the value is present
     */
    default void ifStringPresent(String key, Consumer<String> action) {
    	if(isString(key))
    		action.accept(getString(key));
    }
    
	/**
	 * Calls to {@link ConfigurationSection#getStringList(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getStringList(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    List<String> getStringListOrThrow(String key, String exceptionMessage);
    
    @Override
    VersatiaConfigurationProcessor getConfigurationSection(String key);

    /**
	 * Calls to {@link ConfigurationSection#getConfigurationSection(String)}.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getConfigurationSection(String)}
     * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    VersatiaConfigurationProcessor getConfigurationSectionOrThrow(String key, String exceptionMessage);
    
    /**
	 * Calls to {@link ConfigurationSection#getConfigurationSection(String)} and changes current object state.
	 * @param key Path key
	 * @param exceptionMessage Exception message
     * @throws {@link java.lang.NullPointerException} if path is not found
	 */
    void moveToSectionOrThrow(String key, String exceptionMessage);
    
    /**
	 * Calls to {@link ConfigurationSection#getConfigurationSection(String)} and changes current object state.
	 * @param key Path key Path key
	 * @return Returns whether object state changed (i.e. configuration section was found at specified path)
	 */
    boolean moveToSectionIfPossible(String key);
    
    /**
     * Convenience method.
     * Checks whether given path is configuration section.
     * If yes, it will perform specified action with new processor at specified path. 
     * @param key Path key
     * @param action Action to take if the value is present
     */
    default void ifSectionPresent(String key, Consumer<VersatiaConfigurationProcessor> action) {
    	if(isConfigurationSection(key))
    		action.accept(VersatiaTools.wrapConfigurationToVersatiaProcessor(getConfigurationSection(key)));
    }
    
    /**
     * @param key Path key
     * @param defaultModule Default module if no qualifier found
     * @return Descriptor build of a String from given path or <code>null</code> if no message quantifiers ($) were found or no message template was found
     */
    VersatiaMessageDescriptor getMessageDescriptor(String key, VersatiaModule defaultModule);
    
    /**
	 * Calls to {@link #getStringOrThrow(String, String) getStringOrThrow(key, exceptionMessage)} and parses it as {@link VersatiaMessageDescriptor} object.
     * @param key Path key
     * @param defaultModule Default module if no qualifier found
     * @return Descriptor build of a String from given path
     * @throws {@link java.lang.NullPointerException} if path is not found
     */
    VersatiaMessageDescriptor getMessageDescriptorOrThrow(String key, VersatiaModule defaultModule, String exceptionMessage);
    
    /**
     * Convenience method.
     * Checks whether message descriptor can be obtained from given path.
     * If yes, it will perform specified action with the value. 
     * @param key Path key
     * @param defaultModule Default module if no qualifier found
     * @param action Action to take if the value is present
     */
    default void ifMessageDescriptorPresent(String key, VersatiaModule defaultModule, Consumer<VersatiaMessageDescriptor> action) {
    	if(!isString(key))
    		return;
    	VersatiaMessageDescriptor descriptor = getMessageDescriptor(key, defaultModule);
    	if(descriptor != null)
    		action.accept(descriptor);
    }
    
	/**
	 * Calls to {@link #getStringOrThrow(String, String) getStringOrThrow(key, exceptionMessage)} and applies its result to {@link org.bukkit.ChatColor#translateAlternateColorCodes(char, String) translateAlternateColorCodes('&amp;' result)} method.
	 * @param key Path key
	 * @param exceptionMessage Exception message
	 * @return Result of {@link ConfigurationSection#getBoolean(String)}
	 * @throws {@link java.lang.NullPointerException} if path is not found
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