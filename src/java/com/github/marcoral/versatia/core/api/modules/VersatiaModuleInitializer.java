package com.github.marcoral.versatia.core.api.modules;

public interface VersatiaModuleInitializer extends UnloadedModuleAccessSave {
    /**
     * Overwrites every configuration files in plugin's directory. Use with caution.
     * NOTE: Do not use it to generate initial configuration. It will be done automatically.
     */
	@Override
    void overwriteConfiguration();
	
	/**
	 * Returns module which is being initialized.
	 * Generally, you won't need to use this method, unless you call {@link com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModules#build(JavaPlugin, Consumer) VersatiaModules.build(JavaPlugin, Consumer&lt;VersatiaModuleInitializer&gt;)} manually
	 * @return Module which is being initialized
	 */
	VersatiaModule getUnderlyingModule();
}