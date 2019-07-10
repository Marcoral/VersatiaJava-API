package com.github.marcoral.versatia.core.api.colors;

import com.github.marcoral.versatia.core.api.tools.ExternalDependency;


public enum VersatiaColor {
    BroadcastCasual("&&bc."), BroadcastCasualHighlighted("&&bch."), BroadcastError("&&be."), BroadcastErrorHighlighted("&&beh."), BroadcastSuccess("&&bs."), BroadcastSuccessHighlighted("&&bsh."),
    PlayerCasual("&&pc."), PlayerCasualHighlighted("&&pch."), PlayerError("&&pe."), PlayerErrorHighlighted("&&peh."), PlayerSuccess("&&ps."), PlayerSuccessHighlighted("&&psh."),
    PlayerCommandCasual("&&pcc."), PlayerCommandCasualHighlighted("&&pcch."), PlayerCommandError("&&pce."), PlayerCommandErrorHighlighted("&&pceh."), PlayerCommandSuccess("&&pcs."), PlayerCommandSuccessHighlighted("&&pcsh."),
    ConsoleCasual("&&cc."), ConsoleCasualHighlighted("&&cch."), ConsoleError("&&ce."), ConsoleErrorHighlighted("&&ceh."), ConsoleSuccess("&&cs."), ConsoleSuccessHighlighted("&&csh.");

    @ExternalDependency("Converter")
    private static ColorConverter converter;

    private String referenceKey;
    private VersatiaColor(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    /**
     * Returns color reference key. VersatiaCore uses it internally, when it scans configuration files.
     * @return Color reference key.
     */
    public String getReferenceKey() {
        return referenceKey;
    }

    /**
	 * Converts VersatiaColor into corresponding Bukkit color String.
     * @return Message which is ready to send
     */
    @Override
    public String toString() {
        return converter.convert(this);
    }
}
