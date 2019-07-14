package com.github.marcoral.versatia.core.api.tools;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BukkitTools {
	/**
	 * Runs action in new Bukkit's async task (only if invoked from main thread).
	 * If invoked outside of main thread, just invokes {@link Runnable#run()} on <code>action</code>.
	 * @param plugin Task registerer
	 * @param action Action to take
	 */
	public static void runAsync(Plugin plugin, Runnable action) {
		if(Bukkit.isPrimaryThread())
			Bukkit.getScheduler().runTaskAsynchronously(plugin, action);
		else
			action.run();
	}
	
	/**
	 * Runs action in server's main thread
	 * @param plugin Task registerer
	 * @param action Action to take
	 */
	public static void runSync(Plugin plugin, Runnable action) {
		if(Bukkit.isPrimaryThread())
			action.run();
		else
			Bukkit.getScheduler().runTask(plugin, action);
	}
}