package com.github.marcoral.versatia.core.api.modules.messages;

import java.util.Map;


public interface VersatiaMessageEntry {
	/**
	 * @return Message template associated with object
	 */
	String getTemplateString();
	
	/**
	 * @return Map with object's metadata.
	 */
	Map<String, Object> getMetadataUnmodifable();
	
	/**
	 * Tries to return metadata object of requested type.
	 * If no object is found, the exception will be thrown.
	 * @param <T> Expected type of returned object
	 * @param key Metadata object key
	 * @return Metadata object of requested type
	 */
	<T> T getMetadataObjectOrThrow(String key);	 //Convenience method
}
