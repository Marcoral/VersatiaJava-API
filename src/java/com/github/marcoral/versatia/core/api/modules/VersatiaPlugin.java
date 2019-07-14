package com.github.marcoral.versatia.core.api.modules;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModules;


public abstract class VersatiaPlugin extends JavaPlugin {
	private VersatiaModule correspondingModule;
	
	/**
	 * {@inheritDoc}
	 */
    @Override
    public final void onEnable() {
        VersatiaModules.build(this, initializer -> {
        	onVersatiaEnable(initializer);	
            correspondingModule = initializer.getUnderlyingModule();
        });
        afterBeingBuilded();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onDisable() {
        VersatiaModules.invalidate(this);
        onVersatiaDisable();
    }

    /**
     * Causes actions which should be taken when module is being builded.
     * @param builder Object, which can allow to register module internals
     */
    protected void onVersatiaEnable(VersatiaModuleInitializer builder) {}	//Hook
    
    /**
     * Causes actions which should be taken when module gets build up.
     */
    protected void afterBeingBuilded() {} //Hook
    
    /**
     * Causes actions which should be taken when module is shut down.
     */
    protected void onVersatiaDisable() {} //Hook
    
    /**
     * @return {@link VersatiaModule} object from assigned {@link VersatiaModuleInitializer}
     */
    protected final VersatiaModule getCorrespondingModule() {
    	if(correspondingModule == null)
    		throw new IllegalStateException("You should not use this method yet!");
    	return correspondingModule;
    }
}