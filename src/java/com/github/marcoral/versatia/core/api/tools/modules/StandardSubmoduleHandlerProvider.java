package com.github.marcoral.versatia.core.api.tools.modules;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.VersatiaModuleInitializer;

public abstract class StandardSubmoduleHandlerProvider extends SubmoduleHandlerProviderBase {
	protected final Map<String, Map<String, Object>> modulesData = new HashMap<>();
	public StandardSubmoduleHandlerProvider(String name, VersatiaModuleInitializer initializer) {
		super(name, initializer);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getDataObject(String moduleName, String dataObjectAccessor) {
		Map<String, Object> dataObjects = modulesData.get(moduleName);
		if(dataObjects == null)
			return null;
		return (T) dataObjects.get(dataObjectAccessor);
	}
	
	protected abstract void scanForDataObjects(Plugin plugin);
	protected void shutdownDataObjects(VersatiaModule module, Map<String, Object> dataObjects) {}	//Hook
	
	@Override
	public void load() {
		submoduleLoad(initializer.getUnderlyingModule());
	}
	
	@Override
	public void reload() {
		modulesData.keySet().forEach(key -> scanForDataObjects(Bukkit.getPluginManager().getPlugin(key)));
	}
	
	@Override
	protected void submoduleLoad(VersatiaModule module) {
		String moduleName = module.getCorrespondingPlugin().getName();
		modulesData.put(moduleName, new HashMap<>());
		submoduleReload(module);
	}

	@Override
	protected void submoduleReload(VersatiaModule module) {
		scanForDataObjects(module.getCorrespondingPlugin());
	}

	@Override
	protected void submoduleUnload(VersatiaModule module) {
		modulesData.get(module.getCorrespondingPlugin().getName()).clear();
	}

	@Override
	protected void submoduleShutdown(VersatiaModule module) {
		String pluginName = module.getCorrespondingPlugin().getName();
		Map<String, Object> objectsMap = (Map<String, Object>) modulesData.get(pluginName);
		shutdownDataObjects(module, objectsMap);
		modulesData.remove(pluginName);
	}
}