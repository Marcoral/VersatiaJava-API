package com.github.marcoral.versatia.core.api.modules.commands;

import org.bukkit.entity.Player;


public interface VersatiaPlayerTabCompletionContext extends VersatiaTabCompletionContext {
	/**
	 * {@inheritDoc}
	 */
    @Override Player getExecutor();
}