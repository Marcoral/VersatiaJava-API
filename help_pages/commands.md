## Commands system

VersatiaCore provides very convinient and handy commands API. It is especially useful if you have commands with many nested subcommands (like `/<pluginname> <command> <subcommand> <argument>`).
The standard approach would be registering command named `<pluginname>` and then manually checking whether arguments are specified and if they match known commands.
Also, you would have to remember about adding command to *plugin.yml*.
With VersatiaCore all the above problems are gone.

### Registering simple command
Here is the example of Versatia module with simple command:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleInitializer initializer) { 
			initializer.registerGenericCommand(new ExampleCommand());
		}
	}
	
	class ExampleCommand implements VersatiaGenericCommand {
		@Override
		public String getName() {
			return "scom";
		}

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

With VersatiaCore you can easily create hierarchical commands rather than Bukkit's flat ones. Take a look at the example (we assume that *FirstCommand* and *SecondCommand* are instances of `VersatiaGenericCommand` and `VersatiaPlayerCommand` respectively)

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleInitializer initializer) {
			//You can easly recognize command tree structure just by looking at the code.
			VersatiaCommandFamilyBuilder familyRoot = initializer.registerGenericCommandsFamily(new MyCommandFamilyDescriptor(), CommandPriority.HIGHEST);
				familyRoot.registerGenericCommand(new FirstCommand());
        	
				VersatiaCommandFamilyBuilder nestedFamily = familyRoot.registerGenericCommandsFamily(new NestedFamily());
					nestedFamily.registerPlayerOnlyCommand(new SecondCommand());
		}
	}
	
	class MyCommandFamilyDescriptor implements VersatiaCommand  {
		@Override
		public String getName() {
			return "family";
		}

		@Override
		public String[] getAliases() {
			return new String[]{"fam"};
		}
	
		@Override
		public String getPermission() {
			return "exampleplugin.familycommand.scom";
		}
	}
	
	class FirstCommand implements VersatiaGenericCommand {
		@Override
		pubic boolean invoked(VersatiaCommandContext context) {
			//Command logic command goes here
		}

		@Override
		public String getName() {
			return "scom";
		}

		@Override
		public String[] getAliases() {
			return new String[]{"sc"};
		}
	
		@Override
		public String getPermission() {
			return "exampleplugin.familycommand";
		}

		@Override
		public String getDescription() {
			return "ScomDescription";
		}

		@Override
		public String[] withUsageHints() {
			return new String[]{"ScomUsageHint"};
		}
	}

	class NestedFamily implements VersatiaCommand {
		@Override
		public String getName() {
			return "nested";
		}

		@Override
		public String[] getAliases() {
			return new String[]{"nest"};
		}
	
		@Override
		public String getPermission() {
			return "exampleplugin.familycommand.nested";
		}

		@Override
		public String getDescription() {
			return "NestedFamilyDescription";
		}

		@Override
		public String[] withUsageHints() {
			return new String[]{"NestedFamilyUsageHint1", "NestedFamilyUsageHint2"};
		}
	}

	class SecondCommand implements VersatiaPlayerCommand {
		@Override
		pubic boolean invoked(VersatiaPlayerCommandContext context) {
			//Command logic command goes here
		}

		@Override
		public String getName() {
			return "pcom";
		}

		@Override
		public String[] getAliases() {
			return new String[]{"pc"};
		}
	
		@Override
		public String getPermission() {
			return "exampleplugin.familycommand.nested.pcom";
		}

		@Override
		public String getDescription() {
			return "PcomDescription";
		}

		@Override
		public String[] withUsageHints() {
			return new String[]{"PcomUsageHint"};
		}
	}
	

**Result:**
Now you have two commands:
**1.** /family scom
**2.** /family nested pcom

where the second one can be executed only by player. Versatia will automatically provide detailed information when executor makes a mistake. See the table:

|Misuse example| What VersatiaCore will do |
|--|--|
| `/family` | Inform that you have to provide one of parameters: either *scom* or *nested*. Aliases such as *sc* or *nest* will be printed in addition to commands description messages, read from provided message templates. You can set whether the description to commands which sender doesn't have permissions to, should also be printed (you can set it in ***VersatiaCore/messages/general/commands.yml*** config: `CommandUseErrorNoArguments.PrintForbiddenCommands` flag. Defaults to `false`)
|`/family cfsada`| Inform that you provided unknown command and list available children as above.
|`/family nested`|As for `/family`, but only `pcom` command will be printed, as it is only command as this level of nest.|
|`/family nested pcom` *as console*|Inform that this is the player-only command. You can set such message template in ***VersatiaCore/messages/general/commands.yml*** config: `CommandUseErrorPlayersOnly`.|
|`/family nested pcom` *without permission*|Inform that sender do not have permission to this command. You can set such message template in ***VersatiaCore/messages/general/commands.yml*** config: `CommandUseErrorNoPermission`.|
|`/family nested pcom` *and its handler returns false*|Inform that command exited with error. You can set such message template in ***VersatiaCore/messages/general/commands.yml*** config: `CommandUseErrorIncorrectUse`. If command has specified correct usage hints, `CommandUseErrorIncorrectUseInfo` will be used instead. Also every of the usage hint message templates will be printed in separate lines.|

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