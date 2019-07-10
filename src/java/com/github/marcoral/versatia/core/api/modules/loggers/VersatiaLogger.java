package com.github.marcoral.versatia.core.api.modules.loggers;

import com.github.marcoral.versatia.core.api.modules.messages.VersatiaMessageDescriptor;

public interface VersatiaLogger {
	/**
	 * @param priorityThreshold Messages priority threshold. If passed <code>null</code> {@link LoggingPriority.FINEST} will be used.
	 */
	void setPriorityThreshold(LoggingPriority priorityThreshold);
	
	/**
	 * Sets logger prefix
	 * @param messageTemplate Prefix message template from current module
	 */
	void setPrefix(String messageTemplate);
	
	/**
	 * Sets logger prefix
	 * @param messageDescriptor Prefix message template descriptor
	 */
	void setPrefix(VersatiaMessageDescriptor messageDescriptor);
	
	/**
	 * Sets logger prefix for specify priority
	 * @param priority Logging priority for which prefix will be set
	 * @param messageTemplate Prefix message template from current module
	 */
	void setPrefix(LoggingPriority priority, String messageTemplate);
	
	/**
	 * Sets logger prefix for specify priority
	 * @param priority Logging priority for which prefix will be set
	 * @param messageDescriptor Prefix message template descriptor
	 */
	void setPrefix(LoggingPriority priority, VersatiaMessageDescriptor messageDescriptor);
	
	/** 
	 * @param priority Logging priority
	 * @param messageTemplate Message template from module which introduced underlying logger
	 * @param args Message arguments
	 */
	void log(LoggingPriority priority, String messageTemplate, Object... args);
	
	/**
	 * @param priority Logging priority
	 * @param messageDescriptor Message descriptor
	 * @param args Message arguments
	 */
	void log(LoggingPriority priority, VersatiaMessageDescriptor messageDescriptor, Object... args);
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, String, Object...) log(LoggingPriority.FINEST, messageTemplate, args)}.
	 * @param messageTemplate Message template from module which introduced underlying logger
	 * @param args Message arguments
	 */
	default void finest(String messageTemplate, Object... args) {
		log(LoggingPriority.FINEST, messageTemplate, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, VersatiaMessageDescriptor, Object...) log(LoggingPriority.FINEST, messageDescriptor, args)}.
	 * @param messageDescriptor Message descriptor
	 * @param args Message arguments
	 */
	default void finest(VersatiaMessageDescriptor messageDescriptor, Object... args) {
		log(LoggingPriority.FINEST, messageDescriptor, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, String, Object...) log(LoggingPriority.FINER, messageTemplate, args)}.
	 * @param messageTemplate Message template from module which introduced underlying logger
	 * @param args Message arguments
	 */
	default void finer(String messageTemplate, Object... args) {
		log(LoggingPriority.FINER, messageTemplate, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, VersatiaMessageDescriptor, Object...) log(LoggingPriority.FINER, messageDescriptor, args)}.
	 * @param messageDescriptor Message descriptor
	 * @param args Message arguments
	 */
	default void finer(VersatiaMessageDescriptor messageDescriptor, Object... args) {
		log(LoggingPriority.FINER, messageDescriptor, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, String, Object...) log(LoggingPriority.FINE, messageTemplate, args)}.
	 * @param messageTemplate Message template from module which introduced underlying logger
	 * @param args Message arguments
	 */
	default void fine(String messageTemplate, Object... args) {
		log(LoggingPriority.FINE, messageTemplate, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, VersatiaMessageDescriptor, Object...) log(LoggingPriority.FINE, messageDescriptor, args)}.
	 * @param messageDescriptor Message descriptor
	 * @param args Message arguments
	 */
	default void fine(VersatiaMessageDescriptor messageDescriptor, Object... args) {
		log(LoggingPriority.FINE, messageDescriptor, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, String, Object...) log(LoggingPriority.INFO, messageTemplate, args)}.
	 * @param messageTemplate Message template from module which introduced underlying logger
	 * @param args Message arguments
	 */
	default void info(String messageTemplate, Object... args) {
		log(LoggingPriority.INFO, messageTemplate, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, VersatiaMessageDescriptor, Object...) log(LoggingPriority.INFO, messageDescriptor, args)}.
	 * @param messageDescriptor Message descriptor
	 * @param args Message arguments
	 */
	default void info(VersatiaMessageDescriptor messageDescriptor, Object... args) {
		log(LoggingPriority.INFO, messageDescriptor, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, String, Object...) log(LoggingPriority.WARNING, messageTemplate, args)}.
	 * @param messageTemplate Message template from module which introduced underlying logger
	 * @param args Message arguments
	 */
	default void warning(String messageTemplate, Object... args) {
		log(LoggingPriority.WARNING, messageTemplate, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, VersatiaMessageDescriptor, Object...) log(LoggingPriority.WARNING, messageDescriptor, args)}.
	 * @param messageDescriptor Message descriptor
	 * @param args Message arguments
	 */
	default void warning(VersatiaMessageDescriptor messageDescriptor, Object... args) {
		log(LoggingPriority.WARNING, messageDescriptor, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, String, Object...) log(LoggingPriority.ERROR, messageTemplate, args)}.
	 * @param messageTemplate Message template from module which introduced underlying logger
	 * @param args Message arguments
	 */
	default void error(String messageTemplate, Object... args) {
		log(LoggingPriority.ERROR, messageTemplate, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, VersatiaMessageDescriptor, Object...) log(LoggingPriority.ERROR, messageDescriptor, args)}.
	 * @param messageDescriptor Message descriptor
	 * @param args Message arguments
	 */
	default void error(VersatiaMessageDescriptor messageDescriptor, Object... args) {
		log(LoggingPriority.ERROR, messageDescriptor, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, String, Object...) log(LoggingPriority.SEVERE, messageTemplate, args)}.
	 * @param messageTemplate Message template from module which introduced underlying logger
	 * @param args Message arguments
	 */
	default void severe(String messageTemplate, Object... args) {
		log(LoggingPriority.SEVERE, messageTemplate, args);
	}
	
	/**
	 * Convenience method. Calls to {@link #log(LoggingPriority, VersatiaMessageDescriptor, Object...) log(LoggingPriority.SEVERE, messageDescriptor, args)}.
	 * @param messageDescriptor Message descriptor
	 * @param args Message arguments
	 */
	default void severe(VersatiaMessageDescriptor messageDescriptor, Object... args) {
		log(LoggingPriority.SEVERE, messageDescriptor, args);
	}
}
