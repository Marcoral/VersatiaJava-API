package com.github.marcoral.versatia.core.api.modules.commands;


public interface VersatiaPlayerCommandFamilyBuilder extends VersatiaCommandBuilder {
    @Override VersatiaPlayerCommandFamilyBuilder withPermission(String permission);
    @Override VersatiaPlayerCommandFamilyBuilder withAliases(String... aliases);
    
    /**
     * @param name Name of commands family
     * @return Player-only commands family builder of given name
     */
    VersatiaPlayerCommandFamilyBuilder registerPlayerOnlyCommandsFamily(String name);
    
    /**
     * @param name Name of command
     * @param handler Command invocation handler
     * @return Player-only command builder of given name
     */
    VersatiaCommandBuilder registerPlayerOnlyCommand(String name, VersatiaPlayerCommandHandler handler);
}