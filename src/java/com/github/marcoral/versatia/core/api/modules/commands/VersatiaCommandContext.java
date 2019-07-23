package com.github.marcoral.versatia.core.api.modules.commands;


import org.bukkit.command.CommandSender;

import com.github.marcoral.versatia.core.api.VersatiaConstants;
import com.github.marcoral.versatia.core.api.modules.messages.VersatiaMessageDescriptor;

public interface VersatiaCommandContext {
	/**
	 * @return Commands argument used
	 */
    int getArgsCount();
    
    /**
     * @return Family name accessor used count
     */
    int getFamilyNameAccessorsUsedCount();
    
    /**
     * @param index Argument index
     * @return Argument at requested position
     */
    String getArgument(int index);
    
    /**
     * @param index Argument index
     * @return Family name accessor at requested position
     */
    String getFamilyNameAccessorUsed(int index);
    
    /**
     * @return Command executor
     */
    CommandSender getExecutor();
    
    /**
     * Sends message with given template and arguments to command executor. This is a convenience method.
     * @param messageTemplateKey Message template key
     * @param args Message template arguments
     */
    void replyToExecutor(String messageTemplateKey, String... args);
    
    /**
     * Sends message of given descriptor and arguments to command executor. This is a convenience method.
     * @param messageDescriptor Message descriptor
     * @param args Message arguments
     */
    void replyToExecutor(VersatiaMessageDescriptor messageDescriptor, String... args);
    
    /**
     * Convenience method. Calls to {@link #replyToExecutor(String, String...) replyToExecutor(VersatiaConstants.VERSATIA.getMessageDescriptor("CommandErrorNoModuleFound"), moduleName)}.
     * @param moduleName Module name
     */
    default void printModuleNotFoundMessage(String moduleName) {
    	replyToExecutor(VersatiaConstants.VERSATIA.getMessageDescriptor("CommandErrorNoModuleFound"), moduleName);
    }
}