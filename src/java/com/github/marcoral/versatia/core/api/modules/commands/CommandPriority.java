package com.github.marcoral.versatia.core.api.modules.commands;

public enum CommandPriority {
    LOWEST(0),
    LOW(1),
    NORMAL(2),
    HIGH(3),
    HIGHEST(4);

    private int level;
    private CommandPriority(int level) {
        this.level = level;
    }

    /**
     * @return Numerical value of command priority. The higher it is, the higher priority.
     */
    public int getLevel() {
        return level;
    }
}