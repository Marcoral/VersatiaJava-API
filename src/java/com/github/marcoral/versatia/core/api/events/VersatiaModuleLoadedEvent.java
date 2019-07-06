package com.github.marcoral.versatia.core.api.events;

import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class VersatiaModuleLoadedEvent extends Event {
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
    
    /**
     * Instances of this event are created by VersatiaCore internally. You should not call them on your own!
     * @param module Module being loaded
     */
    public VersatiaModuleLoadedEvent(VersatiaModule module) {
        this.module = module;
    }

    /**
     * @return Module being loaded
     */
    public VersatiaModule getModule() {
        return module;
    }
}
