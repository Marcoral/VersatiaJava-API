package com.github.marcoral.versatia.core.api.modules.commands;


public interface VersatiaCommandFamilyBuilder extends VersatiaPlayerCommandFamilyBuilder {    
    /**
     * @param commandDescriptor Descriptor providing base informations about family
     * @return Player-only commands family builder of given name
     */
    VersatiaCommandFamilyBuilder registerCommandsFamily(VersatiaCommand commandDescriptor);
    
    /**
     * @param command Command to register
     */
    void registerCommand(VersatiaGenericCommand command);
}