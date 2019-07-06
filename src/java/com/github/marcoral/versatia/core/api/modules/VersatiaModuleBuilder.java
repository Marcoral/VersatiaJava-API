package com.github.marcoral.versatia.core.api.modules;

import java.util.function.BiConsumer;

import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaSubmodule;

public interface VersatiaModuleBuilder {
	/**
	 * Request to register new submodule.
	 * @param reloadKey Submodule unique name
	 * @param submodule Submodule to register
	 */
    void addSubmodule(String reloadKey, VersatiaSubmodule submodule);
    
    /**
     * Groups given submodules, so as whole group can be reloaded as one submodule
     * @param groupKey Name for group
     * @param submodules Participants of group
     */
    void groupSubmodules(String groupKey, VersatiaSubmodule... submodules);

    /**
     * Sets processor which may manually modify messages from message configs.
     * If passed <code>null</code> as a parameter, none will be used.
     * @param processor Processor to set
     */
    void setMessageTemplatesProcessor(BiConsumer<String, String> processor);

    /**
     * @return Returns module being builded
     */
    VersatiaModule getCorrespondingModule();
    
    /**
     * Builds requested features and registers module
     */
    void buildAndRun();
}