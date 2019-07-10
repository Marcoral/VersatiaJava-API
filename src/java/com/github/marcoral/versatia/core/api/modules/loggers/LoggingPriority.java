package com.github.marcoral.versatia.core.api.modules.loggers;

public enum LoggingPriority {
    FINEST(0),
    FINER(1),
    FINE(2),
    INFO(3),
    WARNING(4),
    ERROR(5),
    SEVERE(6);

    private int level;
    private String configName;
    private LoggingPriority(int level) {
        this.level = level;
        this.configName = name().substring(0, 1).toUpperCase() + name().toLowerCase().substring(1);
    }

    /**
     * @return Numerical value of logger priority. The higher it is, the higher priority.
     */
    public int getLevel() {
        return level;
    }
    
    public String getConfigName() {
    	return configName;
    }
}