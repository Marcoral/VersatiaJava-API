package com.github.marcoral.versatia.core.api.modules.commands;


public interface VersatiaCommandFamilyBuilder extends VersatiaPlayerCommandFamilyBuilder {
    @Override VersatiaCommandFamilyBuilder withPermission(String permission);
    @Override VersatiaCommandFamilyBuilder withAliases(String... aliases);
    @Override VersatiaPlayerCommandFamilyBuilder registerPlayerOnlyCommandsFamily(String name);
    @Override VersatiaCommandBuilder registerPlayerOnlyCommand(String name, VersatiaPlayerCommandHandler handler);
    
    /**
     * @param name Name of commands family
     * @return Player-only commands family builder of given name
     */
    VersatiaCommandFamilyBuilder registerCommandsFamily(String name);
    
    /**
     * @param name Name of command
     * @param handler Command invocation handler
     * @return Command builder of given name
     */
    VersatiaCommandBuilder registerCommand(String name, VersatiaCommandHandler handler);
}