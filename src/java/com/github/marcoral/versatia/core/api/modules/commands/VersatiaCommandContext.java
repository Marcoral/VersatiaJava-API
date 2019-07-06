package com.github.marcoral.versatia.core.api.modules.commands;


import org.bukkit.command.CommandSender;

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
}