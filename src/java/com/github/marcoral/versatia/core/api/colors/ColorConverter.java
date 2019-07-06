package com.github.marcoral.versatia.core.api.colors;


public interface ColorConverter {
	/**
	 * Converts VersatiaColor into corresponding Bukkit color String.
	 * VersatiaCore uses it internally.
	 * @param color VersatiaColor to translate
	 * @return Corresponding Bukkit color String.
	 */
    String convert(VersatiaColor color);
}
