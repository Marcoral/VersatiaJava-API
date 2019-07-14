package com.github.marcoral.versatia.core.api.modules.commands;

import org.bukkit.entity.Player;


public interface VersatiaPlayerCommandContext extends VersatiaCommandContext {
	/**
	 * {@inheritDoc}
	 */
    @Override Player getExecutor();
}
