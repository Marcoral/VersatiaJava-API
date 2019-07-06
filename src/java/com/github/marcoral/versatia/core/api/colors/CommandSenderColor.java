package com.github.marcoral.versatia.core.api.colors;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum CommandSenderColor {
    Casual("^csc.", VersatiaColor.PlayerCommandCasual, VersatiaColor.ConsoleCasual),
    CasualHighlighted("^csch.", VersatiaColor.PlayerCommandCasualHighlighted, VersatiaColor.ConsoleCasualHighlighted),
    Error("^cse.", VersatiaColor.PlayerCommandError, VersatiaColor.ConsoleError),
    ErrorHighlighted("^cseh.", VersatiaColor.PlayerCommandErrorHighlighted, VersatiaColor.ConsoleErrorHighlighted),
    Success("^css.", VersatiaColor.PlayerCommandSuccess, VersatiaColor.ConsoleSuccess),
    SuccessHighlighted("^cssh.", VersatiaColor.PlayerCommandSuccessHighlighted, VersatiaColor.ConsoleSuccessHighlighted);
	
	private final String referenceKey;
	private final VersatiaColor playerColor;
	private final VersatiaColor consoleColor;
    private CommandSenderColor(String referenceKey, VersatiaColor playerColor, VersatiaColor consoleColor) {
        this.referenceKey = referenceKey;
        this.playerColor = playerColor;
        this.consoleColor = consoleColor;
    }

    /**
     * Returns color reference key. VersatiaCore uses it internally, when it scans configuration files.
     * @return Color reference key.
     */
    public String getReferenceKey() {
        return referenceKey;
    }
    
    /**
     * @return Returns reference key bound with this enum instance.
     * This is equivalent to call {@link #getReferenceKey()} method.
     */
    @Override
    public String toString() {
    	return getReferenceKey();
    }

    /**
     * Overloaded version of {@link java.lang.Object#toString()}. It takes into account who is the message receiver and translates message.
     * This is probably what you would be looking for - do not use no parameter {@link java.lang.Object#toString()} method, unless you know what are you doing.
     * @param executor Desired message receiver
     * @return Message which is ready to send to <code>executor</code>
     */
    public String toString(CommandSender executor) {
    	VersatiaColor color = executor instanceof Player? playerColor : consoleColor;
    	return color.toString();
    }
}
