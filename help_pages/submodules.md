## Submodules system

Versatia enforces division of module (central unit) to many submodules. Such structure helps to maintain the plugin. Each of them can be reloaded independently and depend on the others.

### The construction
Every submodule is a subclass of `VersatiaSubmodule`. Look at the description of its methods:

|Method|Type| When it is invoked | What should be done here |
|--|--|--|--|
| getName() | Mandatory | When submodule name is requested. | Method should return unique submodule name (in module's scope).
| load() | Mandatory | When module is loaded for the first time. | Method should set submodule in correct state.
| unload() | Optional | Before reload() method is called. | It's a good place to clear all collections like Lists and Maps.
| reload() | Optional | When module is reloaded. Can be explicitly invoked. | By default this method calls `unload()` and `load()` afterwards.
| shutdown() | Optional |When submodule is about to shut down virtually. | It's a perfect place to close all IO connections etc.

**Note that none of above method should be explicitly invoked!**
Read more to learn how to act with submodules correctly.


### Adding submodules
 At the moment, Versatia attach two defaults submodules: ***messages*** and ***loggers*** to every module. Of course you can (and should) add custom ones. You can only do it using `VersatiaModuleInitializer.addSubmodule(VersatiaSubmodule)` method, before module is built up. Look at the example:

    public class YourPluginClass extends VersatiaPlugin {
	    @Override
	    protected void onVersatiaEnable(VersatiaModuleInitializer initializer) { 
		    initializer.addSubmodule(new ExampleSubmodule());
	    }
	}
	
	class ExampleSubmodule implements VersatiaSubmodule {	
		@Override
		public String getName() {
			return "YourSubmoduleName";
		}

		@Override
		public void load() {
			//Here you can decide what this submodule should do when it's loaded
		}
	}

**Note that order of adding submodules is relevant! Submodules that uses another ones, should be registered after them!**
You can reload any submodule of any Versatia module using this code:

    VersatiaModules.getModule("ModuleName").reloadSubmodules("FirstSubmodule", "SecondSubmodule");

Versatia will take care that submodules are reloaded in correct order.

### Grouping submodules
You can also form submodules into groups with unique name (within module scope). Then you can request whole group to reload, using its name as it was an independent module. Of course, you can still reload every of group member independently of others. Look at the code:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleInitializer initializer) { 
			//Lets suppose that FirstSubmodule and SecondSubmodule are instances of VersatiaSubmodule
			initializer.addSubmodule(new FirstSubmodule());
			initializer.addSubmodule(new SecondSubmodule());
			initializer.groupSubmodules("GroupKey", "SecondSubmoduleName", "FirstSubmoduleName");
		}

		@Override
		protected void afterBeingBuilded() { 
			/* Will reload both of the submodules.
			* "FirstSubmodule" is guaranteed to be reloaded first, as it was added to module before second one.
			* Order of names in group is meaningless. */
			getCorrespondingModule().reloadSubmodules("GroupKey");
		}
	}