package com.github.marcoral.versatia.core.api.modules.commands;


public interface VersatiaPlayerCommand extends VersatiaCommand {
	/**
	 * @param context Command context
	 * @return <code>true</code> if command exited successfully, <code>false</code> otherwise
	 */
    boolean invoked(VersatiaPlayerCommandContext context);
}