
# VersatiaCore
***VersatiaCore*** is a micro framework which makes both Bukkit plugins development and server management easier.

## Setup

### Adding the dependency

To register module, you have to add VersatiaCore as the dependency to your *plugin.yml*:

> depends: [VersatiaCore]

Also don't forget to add VersatiaCore to your project.
You can add it as **Maven dependency**:

    <dependency>
	  <groupId>com.github.marcoral.versatia</groupId>
	  <artifactId>VersatiaCore-API</artifactId>
	  <version>2.0.0-SNAPSHOT</version>
	  <scope>provided</scope>
	</dependency>

There is no save, release version at the moment.


### Registering the module

Althought some of VersatiaCore features are just utils which can be used independently, you should register your plugin as a Versatia **module** to gain access for most of the features. You can do it two ways:

**a) Extend VersatiaPlugin class:**
It is preffered way to register Versatia module. All you have to do, is to extend *VersatiaPlugin* class. Every method in VersatiaPlugin class is just the hook, which means that you don't have to implement it, if you don't need to.

	public class YourPluginClass extends VersatiaPlugin {
		@Override  
		protected void onVersatiaEnable(VersatiaModuleInitializer versatiaModuleInitializer) {
			//Module building code goes here
		}

		@Override
		protected void afterBeingBuilded() {
			VersatiaModule module = getCorrespondingModule();
			// Now it is save to access your module's assets.
		}
		
		@Override
		protected void onVersatiaDisable() {
			//This code runs when your module gets invalidated.
		}
	}

**b) Manually register class:**
Sometimes you can not afford to extend *VersatiaPlugin* class. It is often the case, when you already extend custom class. In such case, you can register your module manually:

	public class YourPluginClass extends JavaPlugin {
		VersatiaModule correspondingModule;

		@Override  
		public void onEnable() {
	        VersatiaModules.build(this, initializer -> {
	   			//Module building code goes here
	            correspondingModule = initializer.getUnderlyingModule();
	        });
	        VersatiaModule module = correspondingModule;
			//Now it is save to access your module's assets.
		}

		@Override
		public void onDisable() {
			VersatiaModules.invalidate(this);
			//This code runs when your module gets invalidated.
		}
	}

Notice the use of `initializer.getUnderlyingModule()` method in second case.
It's likely, that you'll need to do simmilar trick. Anyway, when working with Versatia modules the default way, there will no need to use it at all.

## Subsystems
Versatia serves set of powerful tools. Every of it has its own help page, whichSubsystemutomats set of powerful tools. Every of it has its own help page, which assumes that you use standard approach in module registering (i.e by extending `VersatiaPlugin` class). If you don't, just use the trick from bottom of the **b)** subsection from **Registering the module** section, to access atiaModule` where it is needed.


**Click at one of the headers below to learn more about system.**

 ### [Messages system](./help_pages/messages.md)

 
It is often the case that many of the messages are hardcoded in plugins and become  impossible to change at the runtime and hard to change in general.
It is also tiring and tedious process to separate those Strings from code source into configs - often you would have to create some sort of storage, config processing system and reloading command.
Usually every plugin introduces its own style of coloring messages, which may result in making the server chat hard to read.
Versatia solves all of above problems.

### [Loggers system](./help_pages/loggers.md)

There is a problem using default Bukkit loggers - you can not distinguish important messages from regular ones using colors. Therefore many developers decides to log their messages using `Bukkit.getConsoleSender().sendMessage()` method.
Unfortunelly, this solution also have its cons (look at [Messages system help page](./help_pages/messages.md) to learn more). So, here comember about ading command o pin.yls Versatia with its loggers system.

### [Submodules system](./help_pages/submodules.md)

Versatia enforces division of module (central unit) to many submodules. Such structure helps to maintain the plugin. Each of them can be reloaded independently and depend on the others.

### [Commands system](./help_pages/commands.md)

VersatiaCore provides very convinient and handy commands API. It is especially useful if you have commands with many nested subcommands (like `/<pluginname> <command> <subcommand> <argument>`).
The standard approach would be registering command named `<pluginname>` and then manually checking whether arguments are specified and if they match known commands.
Also, you would have to remember about adding command to *plugin.yml*.
With VersatiaCore all the above problems are gone.


### [Configuration tools](./help_pages/configuration_tools.md)

Versatia provides some nice tools, which may be useful when it comes to read data from config.

## Automatical resources unpacking
If you register your plugin as Versatia module (see *Setup* above), Versatia will unpack all of files packed in ***resources*** directory onto corresponding directory in plugins folder (named the same as your plugin).

## Events

**VersatiaCore introduces three new events:**


`VersatiaCommandHandlerChangedEvent` - Called when command overrides another, with lower priority.

`VersatiaModuleLoadedEvent` - Called when module gets loaded

`VersatiaModuleReloadedEvent` - Called when module (or one of its submodules) gets reloaded.
