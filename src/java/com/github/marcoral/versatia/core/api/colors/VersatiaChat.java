package com.github.marcoral.versatia.core.api.colors;

import java.text.MessageFormat;

import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModules;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.marcoral.versatia.core.api.VersatiaConstants;

public class VersatiaChat {    
	/**
	 * Sends Versatia-style message to player. This is a convenience method.
	 * @param receiver Message receiver
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void sendVersatiaMessageToPlayer(Player receiver, String messageTemplate, String... args) {
        receiver.sendMessage(VersatiaModules.getModule(VersatiaConstants.VERSATIA_CORE_NAME).getMessageTemplate("PrefixPlayer") + MessageFormat.format(messageTemplate, (Object[]) args));
    }
    
	/**
	 * Sends Versatia-style command output message to player. This is a convenience method.
	 * @param receiver Message receiver
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void sendCommandOutputToPlayer(Player receiver, String messageTemplate, String... args) {
        receiver.sendMessage(VersatiaModules.getModule(VersatiaConstants.VERSATIA_CORE_NAME).getMessageTemplate("PrefixPlayerCommand") + MessageFormat.format(messageTemplate, (Object[]) args));
    }
    
	/**
	 * Sends Versatia-style message to console. This is a convenience method.
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void sendVersatiaMessageToConsole(String messageTemplate, String... args) {
    	Bukkit.getConsoleSender().sendMessage(VersatiaModules.getModule(VersatiaConstants.VERSATIA_CORE_NAME).getMessageTemplate("PrefixConsole") + MessageFormat.format(messageTemplate, (Object[]) args));
    }
    
	/**
	 * Broadcasts Versatia-style message to every player. This is a convenience method.
	 * @param messageTemplate VersatiaCore message template
	 * @param args Message template arguments
	 */
    public static void broadcastMessage(String messageTemplate, String... args) {
    	Bukkit.broadcastMessage(VersatiaModules.getModule(VersatiaConstants.VERSATIA_CORE_NAME).getMessageTemplate("PrefixBroadcast") + MessageFormat.format(messageTemplate, (Object[]) args));
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
}
