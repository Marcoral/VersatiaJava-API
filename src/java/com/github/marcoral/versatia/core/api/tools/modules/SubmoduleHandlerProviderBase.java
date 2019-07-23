package com.github.marcoral.versatia.core.api.tools.modules;

import org.bukkit.plugin.Plugin;

import com.github.marcoral.versatia.core.api.modules.VersatiaModule;
import com.github.marcoral.versatia.core.api.modules.VersatiaModuleInitializer;
import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaSubmodule;

public abstract class SubmoduleHandlerProviderBase implements VersatiaSubmoduleHandlerProvider, VersatiaSubmodule {
	protected final String name;
	protected final VersatiaModuleInitializer initializer;
	public SubmoduleHandlerProviderBase(String name, VersatiaModuleInitializer initializer) {
		this.name = name;
		this.initializer = initializer;
	}
	
	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final Plugin getRegisterer() {
		return initializer.getUnderlyingModule().getCorrespondingPlugin();
	}
	
	protected abstract void submoduleLoad(VersatiaModule module);
	
	protected void submoduleUnload(VersatiaModule module) {}	//Hook
	protected void submoduleShutdown(VersatiaModule module) {}	//Hook
	protected void submoduleReload(VersatiaModule module) {
		submoduleLoad(module);
	}

	@Override
	public final VersatiaSubmodule createHandler(VersatiaModule module) {
		return new VersatiaSubmodule() {
			@Override
			public void shutdown() {
				submoduleShutdown(module);
			}
			
			@Override
			public void unload() {
				submoduleUnload(module);
			}
			
			@Override
			public void reload() {
				submoduleReload(module);
			}
			
			@Override
			public void load() {
				submoduleLoad(module);
			}
			
			@Override
			public String getName() {
				return name;
			}
		};
	}
}