package com.github.marcoral.versatia.core.api.events;

import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModuleReloadResult;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class VersatiaModuleReloadedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    /**
     * Bukkit boilerplate.
     * @return Handlers list
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Bukkit boilerplate.
     * @return Handlers list
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


    private final VersatiaModule module;
    private final VersatiaModuleReloadResult result;
    
    /**
     * Instances of this event are created by VersatiaCore internally. You should not call them on your own!
     * @param module Module being reloaded
     * @param result Reloading result
     */
    public VersatiaModuleReloadedEvent(VersatiaModule module, VersatiaModuleReloadResult result) {
        this.module = module;
        this.result = result;
    }

    /**
     * @return Module being reloaded
     */
    public VersatiaModule getModule() {
        return module;
    }

    
    /**
     * @return Reloading result
     */
    public VersatiaModuleReloadResult getResult() {
        return result;
    }
}
