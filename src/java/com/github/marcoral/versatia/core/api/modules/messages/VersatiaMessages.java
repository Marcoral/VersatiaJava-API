package com.github.marcoral.versatia.core.api.modules.messages;

import java.text.MessageFormat;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.marcoral.versatia.core.api.VersatiaConstants;
import com.github.marcoral.versatia.core.api.colors.CommandSenderColor;
import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModules;
import com.github.marcoral.versatia.core.api.tools.ExternalDependency;

public abstract class VersatiaMessages {    
	/**
	 * Sends Versatia-style message to player. This is a convenience method.
	 * @param receiver Message receiver
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void sendVersatiaMessageToPlayer(Player receiver, String messageTemplate, String... args) {
        receiver.sendMessage(VersatiaConstants.VERSATIA.getMessageTemplate("PrefixPlayer") + MessageFormat.format(messageTemplate, (Object[]) args));
    }
    
	/**
	 * Sends Versatia-style command output message to player. This is a convenience method.
	 * @param receiver Message receiver
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void sendCommandOutputToPlayer(Player receiver, String messageTemplate, String... args) {
        receiver.sendMessage(VersatiaConstants.VERSATIA.getMessageTemplate("PrefixPlayerCommand") + MessageFormat.format(messageTemplate, (Object[]) args));
    }
    
	/**
	 * Sends Versatia-style message to console. This is a convenience method.
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void sendVersatiaMessageToConsole(String messageTemplate, String... args) {
    	Bukkit.getConsoleSender().sendMessage(VersatiaConstants.VERSATIA.getMessageTemplate("PrefixConsole") + MessageFormat.format(messageTemplate, (Object[]) args));
    }
    
	/**
	 * Broadcasts Versatia-style message to every player. This is a convenience method.
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void broadcastMessage(String messageTemplate, String... args) {
    	Bukkit.broadcastMessage(VersatiaConstants.VERSATIA.getMessageTemplate("PrefixBroadcast") + MessageFormat.format(messageTemplate, (Object[]) args));
    }
    
	/**
	 * Sends Versatia-style message to desired receiver as it was the command output message. This is a convenience method.
	 * @param commandSender Message receiver
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void sendVersatiaMessageToCommandSender(CommandSender commandSender, String messageTemplate, String... args) {
    	for(CommandSenderColor color : CommandSenderColor.values())
    		messageTemplate = messageTemplate.replace(color.getReferenceKey(), color.toString(commandSender));
    	if(commandSender instanceof Player)
    		sendCommandOutputToPlayer((Player) commandSender, messageTemplate, args);
    	else
    		sendVersatiaMessageToConsole(messageTemplate, args);
    }
    
    /**
     * Convenience method. It calls to {@link #createTemplateDescriptorImpl(VersatiaModule, String)} after resolving <code>VersatiaModule</code> object.
     * @param moduleName Module name, which message template originates from
     * @param messageTemplateKey Message template key
     * @return Object which uniquely identifies message template originating from specified module. Returns <code>null</code> if not VersatiaModule is found with the specified name.
     */
    public static VersatiaMessageDescriptor createTemplateDescriptor(String moduleName, String messageTemplateKey) {
    	VersatiaModule module = VersatiaModules.getModule(moduleName);
    	if(module == null)
    		return null;
    	return getInstance().createTemplateDescriptorImpl(module, messageTemplateKey);
    }
    
    /**
     * @param module Module, which message template originates from
     * @param messageTemplateKey Message template key
     * @return Object which uniquely identifies message template originating from specified module.
     */
    public static VersatiaMessageDescriptor createTemplateDescriptor(VersatiaModule module, String messageTemplateKey) {
    	return getInstance().createTemplateDescriptorImpl(module, messageTemplateKey);
    }


	/* -------
     * BOILERPLATE
     * -------- */
    @ExternalDependency("INSTANCE")
    private static VersatiaMessages INSTANCE = null;

    private static VersatiaMessages getInstance() {
        if (INSTANCE == null)
            throw new RuntimeException(VersatiaConstants.NOT_LOADED_YET);
        return INSTANCE;
    }
    
    protected abstract VersatiaMessageDescriptor createTemplateDescriptorImpl(VersatiaModule module, String messageTemplateKey);
}
