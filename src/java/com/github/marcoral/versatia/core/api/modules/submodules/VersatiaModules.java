package com.github.marcoral.versatia.core.api.modules.submodules;

import java.util.function.Consumer;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.marcoral.versatia.core.api.VersatiaConstants;
import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.VersatiaModuleInitializer;
import com.github.marcoral.versatia.core.api.tools.ExternalDependency;
import com.github.marcoral.versatia.core.api.tools.VersatiaTools;


public abstract class VersatiaModules {
	/**
	 * Registers specified plugin as Versatia module, registers default submodules and regenerates its configuration files.
	 * @param plugin Plugin that you want to register as Versatia's module
	 * @param initialization Actions that should be taken in addition to default ones
	 */
    public static void build(JavaPlugin plugin, Consumer<VersatiaModuleInitializer> initialization) {
    	VersatiaTools.unpackFiles(plugin, false);
        getInstance().buildImpl(plugin, initialization);
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



    /* -----------
     * BOILERPLATE
     * -------- */
    @ExternalDependency("INSTANCE")
    private static VersatiaModules INSTANCE = null;
    private static VersatiaModules getInstance() {
        if(INSTANCE == null)
            throw new RuntimeException(VersatiaConstants.NOT_LOADED_YET);
        return INSTANCE;
    }

    protected abstract void buildImpl(JavaPlugin plugin, Consumer<VersatiaModuleInitializer> initialization);
    protected abstract void invalidateImpl(JavaPlugin plugin);
    protected abstract VersatiaModule getModuleImpl(String pluginName);
}