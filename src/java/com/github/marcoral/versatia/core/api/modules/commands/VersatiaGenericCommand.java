package com.github.marcoral.versatia.core.api.modules.commands;

import java.util.List;

public interface VersatiaGenericCommand extends VersatiaCommand {
	/**
	 * @param context Command context
	 * @return <code>true</code> if command exited successfully, <code>false</code> otherwise
	 */
    boolean invoked(VersatiaCommandContext context);
    
    /**
     * @param context Tab completion context
     * @return A list of tab-completions for the specified arguments. This will never be null. List may be immutable.
     */
    List<String> tabComplete(VersatiaTabCompletionContext context);
}