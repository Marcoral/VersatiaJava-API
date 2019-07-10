package com.github.marcoral.versatia.core.api.modules.commands;

public interface VersatiaCommand {
	/**
	 * @return Main name of the command. Can not be null.
	 */
	String getName();
	
	/**
	 * @return Command description, possibly null. It may be displayed in commands preview.
	 */
	default String getDescription() {
		return null;
	}
	
	/**
	 * @return Permission required to use command, possibly null
	 */
	default String getPermission() {
		return null;
	}
	
	/**
	 * @return Command aliases, possibly null
	 */
	default String[] getAliases() {
		return null;
	}
	
	/**
	 * @return Command usage hints, possibly null. They may be displayed when player invokes the command in an improper way.
	 */
	default String[] getUsageHints() {
		return null;
	}
	
	/**
	 * @return Command usage flags, possibly null. They may be displayed when player invokes the command in an improper way.
	 */
	default String[] getUsageFlags() {
		return null;
	}
}