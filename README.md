
# VersatiaCore
***VersatiaCore*** is a micro framework which makes both Bukkit plugins development and server management easier.

## Setup

### Adding the dependency

To register module, you have to add VersatiaCore as the dependency to your *plugin.yml*:

> depends: [VersatiaCore]

Also don't forget to add VersatiaCore to build path.

### Registering the module

Althought some of VersatiaCore features are just utils which can be used independently, you should register your plugin as a Versatia **module** to gain access for most of the features. You can do it two ways:

**a) Extend VersatiaPlugin class:**
It is preffered way to register Versatia module. All you have to do, is to extend *VersatiaPlugin* class. Every methods in VersatiaPlugin class are just the hooks, which means that you don't have to implement them, if you don't need to.

	public class YourPluginClass extends VersatiaPlugin {
		@Override  
		protected void onVersatiaEnable(VersatiaModuleBuilder versatiaModuleBuilder) {
			//Module building code goes here
		}

		@Override
		protected void afterBeingBuilded() {
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
		@Override  
		public void onEnable() {
			VersatiaModuleBuilder builder = VersatiaModules.createBuilderFor(this);
			//Module building code goes here.
			builder.buildAndRun();
			//Now it is save to access your module's assets.
		}

		@Override
		public void onDisable() {
			VersatiaModules.invalidate(this);
			//This code runs when your module gets invalidated.
		}
	}

## Automatical resources unpacking
If you register your plugin as Versatia module (see *Setup* above), Versatia will unpack all of files packed in ***resources*** directory onto corresponding directory in plugins folder (named the same as your plugin).

## Message templates system
It is often the case that many of the messages are hardcoded in plugins and become  impossible to change at the runtime and hard to change in general.
It is also tiring and tedious process to separate those Strings from code source into configs - often you would have to create some sort of storage, config processing system and reloading command.

Now you don't have to - Versatia will do that for you.

### Basics
Every messages configs put under ***resources/messages/*** directory (and every subdirectory) will be loaded into Versatia's message storage.

Suppose that your ***resources/messages/SomeMessagesFile.yml*** looks like this:

    MyFirstMessageKey: "My first message"
    MySecondMessageKey: "My second message"

You can reference those messages this way:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void afterBeingBuilded() {
			String message1 = getCorrespondingModule().getMessageTemplate("MyFirstMessageKey");
			String message2 = getCorrespondingModule().getMessageTemplate("MySecondMessageKey");
		}
	}

(`getCorrespondingModule` method is also present in `VersatiaModuleBuilder` object if you don't extend `VersatiaPlugin` class).

### Attaching metadata	

You can also attach some metadata to messages. Lets change our config a bit:

    MyFirstMessageKey:
      Value: "My first message"
      SomeBoolMeta: true
      SomeIntMeta: 4

and the code:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void afterBeingBuilded() { 
			VersatiaMessageEntry messageEntry = getCorrespondingModule().getMessageTemplateEntry("MyFirstMessageKey");
			String message = messageEntry.getTemplateString();
			boolean flag = messageEntry.getMetadataObjectOrThrow("SomeBoolMeta");	//Automatically casted to variable type
			int amount = messageEntry.getMetadataObjectOrThrow("SomeIntMeta");	//Automatically casted to variable type
		}
	}
It is important to remember that "Value" metakey is reserved for actual text of template (returned by `getMessageTemplate` method)

### Nesting messages
You can also nest some of the messages into others. You just have to cover desired message key with `$`. Consider below messages config:

    MyFirstMessageKey: "My first message"
    MySecondMessageKey "My second message, referencing $MyFirstMessageKey$

and the code:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
	    	protected void afterBeingBuilded() { 
			String message = getCorrespondingModule().getMessageTemplate("MySecondMessageKey");
			System.out.println(message);	//Prints "My second message referencing My first message"
	    	}
	}

There is no need to worry about performance. Versatia automatically compiles such messages only once, when the files are being scanned. It also detects errors such as circular or missing dependencies.

You can also access messages from another Versatia modules. The syntax for such reference is: `$ModuleName.MessageKey$`. Note that you will have to add plugin *ModuleName* as the dependency to your *plugin.yml*.

### Using arguments

You can attach reference to arguments, which will emanate from code. Look at the example.

**Config:**

    MyFirstMessage: "Hey {0}, I'm {1}, how are you?"

**Code:**

	public class YourPluginClass extends VersatiaPlugin {
		@Override
	    	protected void afterBeingBuilded() { 
			String messageTemplate = getCorrespondingModule().getMessageTemplate("MyMessage");
        		VersatiaMessages.sendVersatiaMessageToConsole(messageTemplate, "Console", "Bob");
	    	}
	}
It will print `Hey Console, I'm Bob, how are you?` to the console. 
You probably noticed  the use of `VersatiaMessages` utility class. We will discuss it later.
Notice that at any moment you can remove any of the argument from the config, which will result of omitting corresponding argument originating from the code. Lets say, that we don't want to catch first argument anymore. After changing config to:

	MyFirstMessage: "Hey, I'm {1}, how are you?"

Versatia will print `Hey I'm Bob, how are you?` as expected. `"Console"` argument was omitted.
### Using bukkit colors
Versatia automatically calls `ChatColor.translateAlternateColorCodes` method on messages above with `&` as a parameter, which means that

	MyFirstMessageKey: "&6&lMy first message"

will be displayed as golden, bold message.

### Using Versatia colors
Versatia introduces its own "colors", which will be translated into Bukkit ones. Every of such colors has its unique symbol, just like Bukkit colors do.

#### Primitives
Primitive colors identifiers always starts with `&&`, contains first letter of every "word" of its enum instance and ends with single dot (`.`).
You can use them both in the code (using `VersatiaColor` enum instances) and in configs (using colors identifiers). This approach makes keeping chat consistency easier, as every translation to Bukkit color can be easly changed in ***VersatiaCore/messages/general/prefixes.yml*** config by server owner.

**Valid colors are:**

|Enum instance| Identifier |
|--|--|
| PlayerCasual | `&&pc.` |
| PlayerCommandCasual | `&&pcc.` |
| BroadcastCasual | `&&bc.` |
| ConsoleCasual | `&&cc.` |
||
| PlayerCasualHighlighted | `&&pch.` |
| PlayerCommandCasualHighlighted | `&&pcch.` |
| BroadcastCasualHighlighted | `&&bch.` |
| ConsoleCasualHighlighted | `&&cch.` |
||
| PlayerError | `&&pe.` |
| PlayerCommandError | `&&pce.` |
| BroadcastError | `&&be.` |
| ConsoleError | `&&ce.` |
||
| PlayerErrorHighlighted | `&&peh.` |
| PlayerCommandErrorHighlighted | `&&pceh.` |
| BroadcastErrorHighlighted | `&&beh.` |
| ConsoleErrorHighlighted | `&&ceh.` |
||
| PlayerSuccess | `&&ps.` |
| PlayerCommandSuccess | `&&pcs.` |
| BroadcastSuccess | `&&bs.` |
| ConsoleSuccess | `&&cs.` |
||
| PlayerSuccessHighlighted | `&&psh.` |
| PlayerCommandSuccessHighlighted | `&&pcsh.` |
| BroadcastSuccessHighlighted | `&&bsh.` |
| ConsoleSuccessHighlighted | `&&csh.` |

#### Compound
Versatia also comes up with some compound colors. The principle of use is simmilar as for primitive ones. The only difference is about how these colors are processed. Unlike as for primitives, compound colors can not be compiled by Versatia, as them do not provide unequivocal translation to Bukkit codes. Translation depends on who is going to receive such messages. Without that information, Versatia will be helpless with translating - this is why you should always invoke `toString` method explicitly on instances of these colors and pass message receiver as the argument.

**Look at table below:**

**Enum class:** `CommandSenderColor`

|Enum instance|Identifier|Player receiver translation|Console receiver translation|
|--|--|--|--|
| Casual | `^csc.` | PlayerCommandCasual | ConsoleCommandCasual |
| CasualHighlighted | `^csch.` | PlayerCommandCasualHighlighted | ConsoleCommandCasualHighlighted |
| Error | `^cse.` | PlayerCommandError | ConsoleCommandError |
| ErrorHighlighted | `^cseh.` | PlayerCommandErrorHighlighted | ConsoleCommandErrorHighlighted |
| Success | `^csc.` | PlayerCommandSuccess | ConsoleCommandSuccess |
| SuccessHighlighted | `^csch.` | PlayerCommandSuccessHighlighted| ConsoleCommandSuccessHighlighted |

#### Messenging methods:
Often you should append specific prefix to specific message. VersatiaCore defines for of them: ***PrefixPlayer***, ***PrefixPlayerCommand***, ***PrefixBroadcast***, ***PrefixConsole*** in ***messages/general/prefixes.yml*** config.
Anyway you shouldn't use `$VersatiaCore.PrefixKey$` to access them. Instead use methods from `VersatiaMessages` utility class, which makes messenging in Versatia style really easy. Look at the table:

|Example|Proper realization|
|--|--|
| Send message template `template` without arguments to player, leaded by `$VersatiaCore.PrefixPlayer$` | `VersatiaMessages.sendVersatiaMessageToPlayer(player, template);` |
| Send message template `template` with arguments `"foo"` and `"bar"` to player, leaded by `$VersatiaCore.PrefixPlayer$` | `VersatiaMessages.sendVersatiaMessageToPlayer(player, template, "foo", "bar");` |
| Broadcast message template `template` with arguments `"foo"` and `"bar"` to every player, leaded by `$VersatiaCore.PrefixBroadcast$` | `VersatiaMessages.broadcastMessage(template, "foo", "bar");` |
| Broadcast message template `template` with arguments `"foo"` and `"bar"` to console, leaded by `$VersatiaCore.PrefixConsole$` | `VersatiaMessages.sendVersatiaMessageToConsole(template, "foo", "bar");` |
| Broadcast message template `template` with arguments `"foo"` and `"bar"` to either console or player, leaded by proper prefix, as the received message was the result of command invoking | `VersatiaMessages.sendVersatiaMessageToCommandSender(sender, template, "foo", "bar");` |

## Submodules system

Every Versatia module consists of submodules. Every module has unique name (within module scope) and reloader (`VersatiaSubmodule` class extension), which implements `reload()` method (and optionally `shutdown()`).
Above methods are invoked when whole module is reloaded and shutted down respectively, but submodules may also be reloaded independently.

### Adding submodules
 At the moment Versatia registers ***messages*** submodule (which reloads messages in ***messages*** directory) to every module. Of course you can (and should) add your own. You can only do it using `VersatiaModuleBuilder` method, before module is built up. Look at the example:

    public class YourPluginClass extends VersatiaPlugin {
	    @Override
	    protected void onVersatiaEnable(VersatiaModuleBuilder builder) { 
		    builder.addSubmodule("YourSubmoduleName", new ExampleSubmodule());
	    }
	}
	
	class ExampleSubmodule implements VersatiaSubmodule {	
		@Override
		public void reload() {
			//Here you can decide what this submodule should do on reload
		}

		@Override
		public void shutdown() {
			//Hook method. It is a good place to do some sort of cleanup if neccessary.
		}
	}

You can reload any submodule of any Versatia module using this code:

    VersatiaModules.getModule("ModuleName").reloadSubmodules("FirstSubmodule", "SecondSubmodule");

### Grouping submodules
You can also form submodules into groups with unique name (within module scope). Then you can request whole group to reload, using its name as it was an independent module. Of course, you can still reload every of group member independently of others. Look at the code:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleBuilder builder) { 
			//Lets suppose that FirstSubmodule and SecondSubmodule are instances of VersatiaSubmodule
			VersatiaSubmodule firstSubmodule = new FirstSubmodule();
			VersatiaSubmodule secondSubmodule = new SecondSubmodule();
			builder.addSubmodule("FirstSubmodule", firstSubmodule);
			builder.addSubmodule("SecondSubmodule", secondSubmodule);
			builder.groupSubmodules("GroupKey", firstSubmodule, groupSubmodule);
		}

		@Override
		protected void afterBeingBuilded() { 
			getCorrespondingModule().reloadSubmodules("GroupKey");	//Will reload FirstSubmodule and SecondSubmodule
		}
	}

## Commands system

VersatiaCore provides very convinient and handy commands API. It is especially useful if you have commands with many nested subcommands (like `/<pluginname> <command> <subcommand> <argument>`).
The standard approach would be registering command named `<pluginname>` and then manually checking whether arguments are specified and if they match known commands.
Also, you would have to remember about adding command to *plugin.yml*.
With VersatiaCore all the above problems are gone.

### Registering simple command
Here is the example of Versatia module with simple command:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleBuilder builder) { 
			VersatiaModule module = builder.getCorrespondingModule();
			module.registerGenericCommand("scom", new ExampleCommand());
		}
	}
	
	class ExampleCommand implements VersatiaCommandHandler {
		@Override
		public boolean invoked(VersatiaCommandContext context) {
			int argsCount = context.getArgsCount();
			context.replyToSender("ExampleCommandMessageTemplate", argsCount");
			return true;
		}
	}

and below line in some of messages file:
`ExampleCommandMessageTemplate: "^csc.You passed ^csch.{0}^csc. arguments."`

**Result:**
When you type /scom test test2, you'll get fancy formatted message `You passed 2 arguments.` Formatting will differ depending on whether the command was invoked by the player or console. 

`context.replyToSender(template, args)` is a nice alias for `VersatiaMessages.sendVersatiaMessageToCommandSender(context.getExecutor(), module.getMessageTemplate(template), args);`

Likewise, as for standard `CommandExecutor`, returned value informs whether command returned successfully.
And, as mentioned earlier, VersatiaCore takes care of registering commands on its own, therefore you don't have to register your commands in config by hand.

### Registering command family

With VersatiaCore you can easily create hierarchical commands rather than Bukkit's flat ones. Take a look at the example (we assume that *FirstCommand* and *SecondCommand* are instances of `VersatiaCommandHandler` and `VersatiaPlayerCommandHandler` respectively)

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleBuilder builder) { 
			VersatiaModule module = builder.getCorrespondingModule();
        	
			VersatiaCommandFamilyBuilder familyRoot = module.registerGenericCommandsFamily("family", CommandPriority.HIGHEST)
			.withAliases("fam")
			.withPermission("exampleplugin.familycommand")

			familyRoot.registerGenericCommand("scom", new FirstCommand())
			.withAliases("sc")
			.withDescription("ScomDescription")
			.withPermission("exampleplugin.familycommand.scom")
			.withUsageHint("ScomUsageHint");
        	
			VersatiaCommandFamilyBuilder nestedFamily = familyRoot.registerGenericCommandsFamily("nested")
			.withAliases("nest")
			.withDescription("NestedFamilyDescription")
			.withPermission("exampleplugin.familycommand.nested)
			.withUsageHint("NestedFamilyUsageHint");
				nestedFamily.registerPlayerOnlyCommand("pcom", new SecondCommand())
				.withAliases("pc")
				.withDescription("PcomDescription")
				.withPermission("exampleplugin.familycommand.nested.pcom)
				.withUsageHint("PcomUsageHint");
		}
	}

**Result:**
Now you have two commands:
**1.** /family scom
**2.** /family nested pcom

where the second one can be executed only by player. Versatia will automatically provide detailed information when executor makes a mistake. See the table:

|Misuse example| What VersatiaCore will do |
|--|--|
| `/family` | Inform that you have to provide one of parameters: either *scom* or *nested*. Aliases such as *sc* or *nest* will be printed in addition to commands description messages, read from provided message templates. You can set whether the description to commands which sender doesn't have permissions to, should also be printed (you can set it in ***VersatiaCore/messages/general/commands.yml*** config: `GenericErrorNoArguments.PrintForbiddenCommands` flag. Defaults to `false`)
|`/family cfsada`| Inform that you provided unknown command and list available children as above.
|`/family nested`|As for `/family`, but only `pcom` command will be printed, as it is only command as this level of nest.|
|`/family nested pcom` *as console*|Inform that this is the player-only command. You can set such message template in ***VersatiaCore/messages/general/commands.yml*** config: `GenericErrorPlayersOnly`.|
|`/family nested pcom` *without permission*|Inform that sender do not have permission to this command. You can set such message template in ***VersatiaCore/messages/general/commands.yml*** config: `GenericErrorNoPermission`.|
|`/family nested pcom` *and its handler returns false*|Inform that command exited with error. You can set such message template in ***VersatiaCore/messages/general/commands.yml*** config: `GenericErrorIncorrectUse`. If command has specified correct usage hints, `GenericErrorIncorrectUseInfo` will be used instead. Also every of the usage hint message templates will be printed in separate lines.|

### About CommandPriority
When registering root-level command, you can specify its priority (`CommandPriority.NORMAL` by default). VersatiaCore will handle command names colisions from various modules in such way, that at any moment the command with higher priority is registered. When module which introduced command with higher priority gets shutted down, the next command with the highest priority from the queue will be registered for its place. If two commands have the same priority, collisions will be handled in FIFO manner.

### Built-in commands
VersatiaCore serves some of build in commands.

    /versatia (vers) reload (rel) <module name> [submodule names...]

Specify module to reload and (optionally) submodules to reload. If none passed, then every will be reloaded.

    /versatia (vers) reloadcore (relcore, relc) [submodule names...]

Like command above, but with *VersatiaCore* passed as `<module name>`.

    /versatia (vers) regenerate (regen) <module name> [-f | -fr]

Specify module, which configuration files should be regenerated. Optionally you can use `-f` or `-fr`.

|Flag| Description |
|--|--|
| `-f` | Overwrite every configuration file (be cautious!) |
| `-fr` | Like above. Reload whole module afterwards.


    /versatia (vers) regeneratecore (regencore, regenc) [-f | -fr]

Like command above, but with *VersatiaCore* passed as `<module name>`.

## Events

**VersatiaCore introduces three new events:**

`VersatiaCommandHandlerChangedEvent` - Called when command overrides another, with lower priority.

`VersatiaModuleLoadedEvent` - Called when module gets loaded.

`VersatiaModuleReloadedEvent` - Called when module (or one of its submodules) gets reloaded.

## Configuration tools

Versatia provides also some nice tools, which my be useful when it comes to read data from config.
It allows you to throw exception with informative message rather than NullPointerException in case that node in requested config is missing. You can also easily move through sections, with custom exception message if they are missing. Look at the example:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleBuilder builder) { 
			VersatiaModule module = builder.getCorrespondingModule();
			VersatiaConfigurationProcessor processor = module.getConfig("myconfigs/example.yml").getProcessor();
			boolean flag = processor.getBooleanOrThrow("Flag", "Oops, someone captured the flag!");
			processor = processor.moveToSectionOrThrow("NextSection", "Where is the NextSection...?");
			String secretMessage = processor.getColoredStringOrThrow("SecretMessage", "You don't keep secrets in config?");
			processor = processor.moveToSectionOrThrow("EvenDeeperSection", "Where is the NextSection.EvenDeeperSection?!");
			String bottomHierarchyMessage = processor.getStringOrThrow("BottomString", "Whoa, bottom-less config! Ha ha.");
		}
	}

For file ***pluginname/myconfigs/example.yml***:

    Flag: true
    NextSection:
      SecretMessage: "&6&lWhen you love someone, then that someone will never disappear. - Winnie The Pooh"
      EvenDeeperSection:
        BottomString: "China"

The variables will have values:

`flag` = true;

`secretMessage` = When you love someone, then that someone will never disappear. - 
Winnie The Pooh *(in golden bold font)*

`bottomHierarchyMessage` = China
