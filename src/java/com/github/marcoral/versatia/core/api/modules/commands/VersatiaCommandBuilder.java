package com.github.marcoral.versatia.core.api.modules.commands;


public interface VersatiaCommandBuilder {
	/**
	 * Sets command permission and returns current instance of builder
	 * @param permission Command permission
	 * @return Current instance of builder
	 */
	VersatiaCommandBuilder withPermission(String permission);
	
	/**
	 * Sets command aliases and returns current instance of builder
	 * @param aliases Command aliases
	 * @return Current instance of builder
	 */
	VersatiaCommandBuilder withAliases(String... aliases);
	
	/**
	 * Sets command description and returns current instance of builder
	 * @param description Command description
	 * @return Current instance of builder
	 */
	VersatiaCommandBuilder withDescription(String description);
	
	/**
	 * Sets command usage hits and returns current instance of builder
	 * @param hintMessageTemplates Command usage hints
	 * @return Current instance of builder
	 */
	VersatiaCommandBuilder withUsageHint(String... hintMessageTemplates);
	
	/**
	 * Sets command usage flags and returns current instance of builder
	 * @param flagsMessageTemplates Command usage flags
	 * @return Current instance of builder
	 */
	VersatiaCommandBuilder withUsageFlags(String... flagsMessageTemplates);
}