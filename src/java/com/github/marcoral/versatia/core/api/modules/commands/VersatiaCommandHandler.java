package com.github.marcoral.versatia.core.api.modules.commands;


public interface VersatiaCommandHandler {
	/**
	 * @param context Command context
	 * @return <code>true</code> if command exited successfully, <code>false</code> otherwise
	 */
    boolean invoked(VersatiaCommandContext context);
}