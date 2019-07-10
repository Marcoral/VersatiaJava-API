package com.github.marcoral.versatia.core.api.modules;


import com.github.marcoral.versatia.core.api.configuration.VersatiaConfigurationFile;
import com.github.marcoral.versatia.core.api.modules.messages.VersatiaMessageEntry;
import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModuleReloadResult;

public interface VersatiaModule extends UnloadedModuleAccessSave {
	/**
	 * Reloads both configuration and submodules
	 */
    default void reloadAll() {
        regenerateConfiguration();
        reloadEverySubmodule();
    }
    
    /**
     * Unregister root-level command of specified name.
     * @param name Name of root-level command to unregister
     */
    void unregisterRootCommand(String name);

    /**
     * Calls {@link com.github.marcoral.versatia.core.api.modules.submodules.VersatiaSubmodule#reload()} method on every submodule.
     * @return Object which provides information about reloading result
     */
    VersatiaModuleReloadResult reloadEverySubmodule();
    
    /**
     * Calls {@link com.github.marcoral.versatia.core.api.modules.submodules.VersatiaSubmodule#reload()} method on every submodule of requested name or belonging to group of such name.
     * @param submodules Submodules/groups names to reload
     * @return Object which provides information about reloading result
     */
    VersatiaModuleReloadResult reloadSubmodules(String... submodules);
    
    /**
     * Generates missing configuration files in plugin's directory.
     */
    void regenerateConfiguration();

    /**
     * @param key Message template's key
     * @return Message template object
     */
    VersatiaMessageEntry getMessageTemplateEntry(String key);
    
    /**
     * @param path Path to configuration file
     * @return Object wrapping {@link org.bukkit.configuration.file.FileConfiguration} object created using specified path
     */
    VersatiaConfigurationFile getConfig(String path);

    /**
     * Returns message template of specified key. 
     * @param key Message template's key. For convenience it can be null.
     * @return Message template String. If passed <code>null</code> as <code>key</code> it will return an empty String
     */
    default String getMessageTemplate(String key) {
    	if(key == null)
    		return "";
    	VersatiaMessageEntry entry = getMessageTemplateEntry(key);
    	return entry == null? null : entry.getTemplateString();
    }
}