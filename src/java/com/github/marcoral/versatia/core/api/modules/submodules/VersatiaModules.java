package com.github.marcoral.versatia.core.api.modules.submodules;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.marcoral.versatia.core.api.VersatiaConstants;
import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.VersatiaModuleBuilder;
import com.github.marcoral.versatia.core.api.tools.ExternalDependency;


public abstract class VersatiaModules {
	/**
	 * Creates instance of <code>VersatiaModuleBuilder</code> for specified plugin.
	 * @param plugin Plugin that Versatia should create module builder for
	 * @return Module builder associated with the specified plugin
	 */
    public static VersatiaModuleBuilder createBuilderFor(JavaPlugin plugin) {
        return getInstance().createBuilderForImpl(plugin);
    }

    /**
     * Invalidates the module. Always when mode is turned off this method should be invoked.
     * @param plugin Plugin to invalidate
     */
    public static void invalidate(JavaPlugin plugin) {
        getInstance().invalidateImpl(plugin);
    }

    /**
     * @param pluginName Name of the plugin
     * @return Module associated with the plugin, possibly <code>null</code> if no such exists
     */
    public static VersatiaModule getModule(String pluginName) {
        return getInstance().getModuleImpl(pluginName);
    }



    /* -------
     * BOILERPLATE
     * -------- */
    @ExternalDependency("INSTANCE")
    private static VersatiaModules INSTANCE = null;
    private static VersatiaModules getInstance() {
        if(INSTANCE == null)
            throw new RuntimeException(VersatiaConstants.NOT_LOADED_YET);
        return INSTANCE;
    }

    protected abstract VersatiaModuleBuilder createBuilderForImpl(JavaPlugin plugin);
    protected abstract void invalidateImpl(JavaPlugin plugin);
    protected abstract VersatiaModule getModuleImpl(String pluginName);
}