package com.github.marcoral.versatia.core.api.events;


import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.commands.CommandPriority;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class VersatiaCommandHandlerChangedEvent extends Event {
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


    private final String commandAccessor;
    private final VersatiaModule previousRegisterer, newRegisterer;
    private final CommandPriority previousPriority, newPriority;
    
    /**
     * Instances of this event are created by VersatiaCore internally. You should not call them on your own!
     * @param commandAccessor Command accessor
     * @param previousRegisterer Previous registerer of command with specified <code>commandAccessor</code>
     * @param previousPriority Previous priority of command named <code>commandAccessor</code>
     * @param newRegisterer New registerer of command with specified <code>commandAccessor</code>
     * @param newPriority New priority of command named <code>commandAccessor</code>
     */
    public VersatiaCommandHandlerChangedEvent(String commandAccessor, VersatiaModule previousRegisterer,
    		CommandPriority previousPriority, VersatiaModule newRegisterer, CommandPriority newPriority) {
        this.commandAccessor = commandAccessor;
        this.previousRegisterer = previousRegisterer;
        this.newRegisterer = newRegisterer;
        this.previousPriority = previousPriority;
        this.newPriority = newPriority;
    }

    /**
     * @return Command accessor
     */
	public String getCommandAccessor() {
		return commandAccessor;
	}

	/**
	 * @return Previous registerer of the command
	 */
	public VersatiaModule getPreviousRegisterer() {
		return previousRegisterer;
	}

	/**
	 * @return New registerer of the command
	 */
	public VersatiaModule getNewRegisterer() {
		return newRegisterer;
	}

	/**
	 * @return Previous priority of the command
	 */
	public CommandPriority getPreviousPriority() {
		return previousPriority;
	}

	/**
	 * @return New priority of the command
	 */
	public CommandPriority getNewPriority() {
		return newPriority;
	}
}
