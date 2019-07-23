package com.github.marcoral.versatia.core.api.modules.commands;


import org.bukkit.Location;
import org.bukkit.command.CommandSender;

public interface VersatiaTabCompletionContext {
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
     * @return The position looked at by the sender, or null if none
     */
    Location getLocation();
}