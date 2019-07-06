package com.github.marcoral.versatia.core.api.modules;


import com.github.marcoral.versatia.core.api.configuration.VersatiaConfigurationFile;
import com.github.marcoral.versatia.core.api.modules.messages.VersatiaMessageEntry;
import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModuleReloadResult;
import com.github.marcoral.versatia.core.api.modules.commands.CommandPriority;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaCommandBuilder;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaCommandFamilyBuilder;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaCommandHandler;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaPlayerCommandFamilyBuilder;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaPlayerCommandHandler;

public interface VersatiaModule {
	/**
	 * Reloads both configuration and submodules
	 */
    default void reloadAll() {
        regenerateConfiguration();
        reloadEverySubmodule();
    }


    /**
     * Registers root-level command of given name and priority.
     * @param name Command name
     * @param handler Command invocation handler
     * @param priority Command priority
     * @return Command builder object
     */
    VersatiaCommandBuilder registerGenericCommand(String name, VersatiaCommandHandler handler, CommandPriority priority);
    
    /**
     * Convenience method. Calls to <code>registerGenericCommand(name, handler, CommandPriority.NORMAL)</code>.
     * @param name Command name
     * @param handler Command invocation handler
     * @return Command builder object
     */
    default VersatiaCommandBuilder registerGenericCommand(String name, VersatiaCommandHandler handler) {
        return registerGenericCommand(name, handler, CommandPriority.NORMAL);
    }

    /**
     * Registers root-level player-only command of given name and priority.
     * @param name Command name
     * @param handler Player-only command invocation handler
     * @param priority Command priority
     * @return Command builder object
     */
    VersatiaCommandBuilder registerPlayerOnlyCommand(String name, VersatiaPlayerCommandHandler handler, CommandPriority priority);
    
    /**
     * Convenience method. Calls to <code>registerPlayerOnlyCommand(name, handler, CommandPriority.NORMAL)</code>.
     * @param name Command name
     * @param handler Command invocation handler
     * @return Command builder object
     */
    default VersatiaCommandBuilder registerPlayerOnlyCommand(String name, VersatiaPlayerCommandHandler handler) {
        return registerPlayerOnlyCommand(name, handler, CommandPriority.NORMAL);
    }

    /**
     * Registers root-level commands family of given name and priority.
     * @param commandFamilyLabel Commands family name
     * @param priority Commands family priority
     * @return Commands family builder object
     */
    VersatiaCommandFamilyBuilder registerGenericCommandsFamily(String commandFamilyLabel, CommandPriority priority);
    
    /**
     * Convenience method. Calls to <code>registerGenericCommandsFamily(name, CommandPriority.NORMAL)</code>.
     * @param name Commands family name
     * @return Commands family builder object
     */
    default VersatiaCommandFamilyBuilder registerGenericCommandsFamily(String name) {
        return registerGenericCommandsFamily(name, CommandPriority.NORMAL);
    }

    /**
     * Registers root-level players-only commands family of given name and priority.
     * @param commandFamilyLabel Commands family name
     * @param priority Commands family priority
     * @return Player-only commands family builder object
     */
    VersatiaPlayerCommandFamilyBuilder registerPlayerOnlyCommandsFamily(String commandFamilyLabel, CommandPriority priority);
    
    /**
     * Convenience method. Calls to <code>registerPlayerOnlyCommandsFamily(name, CommandPriority.NORMAL)</code>.
     * @param name Commands family name
     * @return Player-only commands family builder object
     */
    default VersatiaPlayerCommandFamilyBuilder registerPlayerOnlyCommandsFamily(String name) {
        return registerPlayerOnlyCommandsFamily(name, CommandPriority.NORMAL);
    }

    /**
     * Calls <code>reload()</code> method on every submodule.
     * @return Object which provides information about reloading result
     */
    VersatiaModuleReloadResult reloadEverySubmodule();
    
    /**
     * Calls <code>reload()</code> method on every submodule of requested name or belonging to group of such name.
     * @param submodules Submodules/groups names to reload
     * @return Object which provides information about reloading result
     */
    VersatiaModuleReloadResult reloadSubmodules(String... submodules);
    
    /**
     * Generates missing configuration files in plugin's directory.
     */
    void regenerateConfiguration();
    
    /**
     * Overwrites every configuration files in plugin's directory. Use with caution.
     */
    void overwriteConfiguration();
    
    /**
     * @param path Path to configuration file
     * @return Object wrapping <code>File</code> object created using specified path
     */
    VersatiaConfigurationFile getConfig(String path);
    
    /**
     * @param key Message template's key
     * @return Message template object
     */
    VersatiaMessageEntry getMessageTemplateEntry(String key);
    
    /**
     * Returns message template of specified key
     * @param key Message template's key
     * @return Message template String
     */
    default String getMessageTemplate(String key) {
    	VersatiaMessageEntry entry = getMessageTemplateEntry(key);
    	return entry == null? null : entry.getTemplateString();
    }
}