package com.github.marcoral.versatia.core.api.modules.loggers;

import java.util.Locale;

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
        
        //Use Locale.ENGLISH to avoid possible bugs with turkish locale
        this.configName = name().substring(0, 1).toUpperCase(Locale.ENGLISH) + name().toLowerCase(Locale.ENGLISH).substring(1);
    }

    /**
     * @return Numerical value of logger priority. The higher it is, the higher priority.
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * @return Corresponding config name
     */
    public String getConfigName() {
    	return configName;
    }
}