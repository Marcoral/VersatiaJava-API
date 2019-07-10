package com.github.marcoral.versatia.core.api.modules.commands;


public interface VersatiaPlayerCommandFamilyBuilder {
    /**
     * @param commandDescriptor Descriptor providing base informations about family
     * @return Player-only commands family builder of given name
     */
    VersatiaPlayerCommandFamilyBuilder registerPlayerOnlyCommandsFamily(VersatiaCommand commandDescriptor);
    
    /**
     * @param command Command to register
     */
    void registerPlayerOnlyCommand(VersatiaPlayerCommand command);
}