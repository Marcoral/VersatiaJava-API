package com.github.marcoral.versatia.core.api.modules;

import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModules;
import org.bukkit.plugin.java.JavaPlugin;


public abstract class VersatiaPlugin extends JavaPlugin {
	private VersatiaModule correspondingModule;
	
    @Override
    public final void onEnable() {
        VersatiaModuleBuilder builder = VersatiaModules.createBuilderFor(this);
        this.correspondingModule = builder.getCorrespondingModule();
        onVersatiaEnable(builder);
        builder.buildAndRun();
        afterBeingBuilded();
    }

    @Override
    public final void onDisable() {
        VersatiaModules.invalidate(this);
        onVersatiaDisable();
    }

    /**
     * Causes actions which should be taken when module is being builded.
     * @param builder Object, which can allow to register module internals
     */
    protected void onVersatiaEnable(VersatiaModuleBuilder builder) {}	//Hook
    
    /**
     * Causes actions which should be taken when module gets build up.
     */
    protected void afterBeingBuilded() {} //Hook
    
    /**
     * Causes actions which should be taken when module is shut down.
     */
    protected void onVersatiaDisable() {} //Hook
    
    /**
     * @return <code>VersatiaModule</code> object from assigned <code>VersatiaModuleBuilder</code>
     */
    protected final VersatiaModule getCorrespondingModule() {
    	return correspondingModule;
    }
}