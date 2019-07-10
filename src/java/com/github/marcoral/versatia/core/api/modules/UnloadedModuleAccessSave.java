package com.github.marcoral.versatia.core.api.modules;

import java.util.function.BiConsumer;

import com.github.marcoral.versatia.core.api.configuration.VersatiaConfigurationFile;
import com.github.marcoral.versatia.core.api.modules.commands.CommandPriority;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaCommand;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaCommandFamilyBuilder;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaGenericCommand;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaPlayerCommand;
import com.github.marcoral.versatia.core.api.modules.commands.VersatiaPlayerCommandFamilyBuilder;
import com.github.marcoral.versatia.core.api.modules.loggers.VersatiaLogger;
import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaSubmodule;

public interface UnloadedModuleAccessSave {
	/**
	 * Registers specified module and calls {@link VersatiaSubmodule#reload()} on it.
	 * @param submodule Submodule to register
	 */
    void addSubmodule(VersatiaSubmodule submodule);
    
    /**
     * Groups given submodules, so as whole group can be reloaded as one submodule.
     * @param groupKey Name for group
     * @param submoduleNames Names of participants of group
     */
    void groupSubmodules(String groupKey, String... submoduleNames);

    /**
     * Sets processor which may manually modify messages from message configs.
     * If passed <code>null</code> as a parameter, none will be used.
     * Note that it will reload whole <b><i>messages</i></b> submodule.
     * @param processor Processor to set
     */
    void setMessageTemplatesProcessor(BiConsumer<String, String> processor);

    /**
     * Registers root-level command of given name and priority.
     * @param command Command to register
     * @param priority Command priority
     */
    void registerGenericCommand(VersatiaGenericCommand command, CommandPriority priority);
    
    /**
     * Convenience method. Calls to {@link #registerGenericCommand(VersatiaGenericCommand, CommandPriority) registerGenericCommand(handler, CommandPriority.NORMAL)}.
     * @param command Command to register
     */
    default void registerGenericCommand(VersatiaGenericCommand command) {
        registerGenericCommand(command, CommandPriority.NORMAL);
    }

    /**
     * Registers root-level player-only command of given name and priority.
     * @param command Command to register
     * @param priority Command priority
     */
    void registerPlayerOnlyCommand(VersatiaPlayerCommand command, CommandPriority priority);
    
    /**
     * Convenience method. Calls to {@link #registerPlayerOnlyCommand(VersatiaPlayerCommand, CommandPriority) registerPlayerOnlyCommand(handler, CommandPriority.NORMAL)}.
     * @param command Command to register
     */
    default void registerPlayerOnlyCommand(VersatiaPlayerCommand command) {
        registerPlayerOnlyCommand(command, CommandPriority.NORMAL);
    }

    /**
     * Registers root-level commands family of given metric and priority.
     * @param commandDescriptor Descriptor providing base informations about family
     * @param priority Commands family priority
     * @return Commands family builder object
     */
    VersatiaCommandFamilyBuilder registerGenericCommandsFamily(VersatiaCommand commandDescriptor, CommandPriority priority);
    
    /**
     * Convenience method. Calls to {@link #registerGenericCommandsFamily(VersatiaCommand, CommandPriority) registerGenericCommandsFamily(commandDescriptor, CommandPriority.NORMAL)}.
     * @param commandDescriptor Descriptor providing base informations about family
     * @return Commands family builder object
     */
    default VersatiaCommandFamilyBuilder registerGenericCommandsFamily(VersatiaCommand commandDescriptor) {
        return registerGenericCommandsFamily(commandDescriptor, CommandPriority.NORMAL);
    }

    /**
     * Registers root-level players-only commands family of given metric and priority.
     * @param commandDescriptor Descriptor providing base informations about family
     * @param priority Commands family priority
     * @return Player-only commands family builder object
     */
    VersatiaPlayerCommandFamilyBuilder registerPlayerOnlyCommandsFamily(VersatiaCommand commandDescriptor, CommandPriority priority);
    
    /**
     * Convenience method. Calls to {@link #registerPlayerOnlyCommandsFamily(VersatiaCommand, CommandPriority) registerPlayerOnlyCommandsFamily(commandDescriptor, CommandPriority.NORMAL)}.
     * @param commandDescriptor Descriptor providing base informations about family
     * @return Player-only commands family builder object
     */
    default VersatiaPlayerCommandFamilyBuilder registerPlayerOnlyCommandsFamily(VersatiaCommand commandDescriptor) {
        return registerPlayerOnlyCommandsFamily(commandDescriptor, CommandPriority.NORMAL);
    }
    
    /**
     * Overwrites every configuration files in plugin's directory. Use with caution.
     */
    void overwriteConfiguration();
    
    /**
     * @param path Path to configuration file
     * @return Object wrapping {@link org.bukkit.configuration.file.FileConfiguration} object created using specified path
     */
    VersatiaConfigurationFile getConfig(String path);
    
    
    /**
     * @param loggerName Logger name
     * @return Logger object, which can be used for logging messages onto the console
     * @since 1.1
     */
    VersatiaLogger getLogger(String loggerName);
}
