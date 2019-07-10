## Message templates system
It is often the case that many of the messages are hardcoded in plugins and become  impossible to change at the runtime and hard to change in general.
It is also tiring and tedious process to separate those Strings from code source into configs - often you would have to create some sort of storage, config processing system and reloading command.
Usually every plugin introduces its own style of coloring messages, which may result in making the server chat hard to read.

Versatia solves all of above problems.

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

### Attaching metadata	

You can also attach some metadata to messages. Lets change our config a bit:

    MyFirstMessageKey:
      Value: "My first message"
      SomeBoolMeta: true
      SomeIntMeta: 4
    MySecondMessageKey: "My second message"

and the code:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void afterBeingBuilded() { 
			VersatiaMessageEntry messageEntry1 = getCorrespondingModule().getMessageTemplateEntry("MyFirstMessageKey");
			//Contains "MyFirstMessage"
			String message = messageEntry1.getTemplateString();
			boolean flag = messageEntry1.getMetadataObjectOrThrow("SomeBoolMeta");	//Automatically casted to variable type
			int amount = messageEntry1.getMetadataObjectOrThrow("SomeIntMeta");	//Automatically casted to variable type
			
			//Also contains "My first message"
			String message1anotherWay = getCorrespondingModule().getMessageTemplate("MyFirstMessageKey");
			
			//Contains "My second message"
			VersatiaMessageEntry messageEntry2 = getCorrespondingModule().getMessageTemplateEntry("MySecondMessageKey");

			//Also contains "My second message"
			String message2anotherWay = getCorrespondingModule().getMessageTemplate("MySecondMessageKey");
		}
	}
It is important to remember that "Value" metakey is reserved for actual text of template (returned by `getMessageTemplate()` method). Anyway, most of messages (which doesn't have metadata) are rather like flat structures (i.e. key-value like `MySecondMessageKey`). You don't need to write `Value` keyword here. The value after the colon will be used.
You can also use `getMessageTemplate()` instead of `getMessageTemplateEntry()` if you care only about message template text (look how we derieved `message1anotherWay` and `message2anotherWay`).

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
Versatia also comes up with some compound colors. The principle of use is simmilar as for primitive ones. The only difference is about how these colors are processed. Unlike as for primitives, compound colors can not be compiled by Versatia, as them do not provide unequivocal translation to Bukkit codes. Translation depends on who is going to receive such messages. Without that information, Versatia will be helpless with translating - this is why you should always invoke `toString(CommandSender)` method on instances of these colors and pass message receiver as the argument (rather than classical, no parameter `toString()` method).

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

### Messenging methods
Often you should append specific prefix to specific message. VersatiaCore defines for of them: ***PrefixPlayer***, ***PrefixPlayerCommand***, ***PrefixBroadcast***, ***PrefixConsole*** in ***messages/general/prefixes.yml*** config.
Anyway you shouldn't use `$VersatiaCore.PrefixKey$` to access them. Instead use methods from `VersatiaMessages` utility class, which makes messenging in Versatia style really easy. Look at the table:

|Example|Proper realization|
|--|--|
| Send message template `template` without arguments to player, leaded by `$VersatiaCore.PrefixPlayer$` | `VersatiaMessages.sendVersatiaMessageToPlayer(player, template);` |
| Send message template `template` with arguments `"foo"` and `"bar"` to player, leaded by `$VersatiaCore.PrefixPlayer$` | `VersatiaMessages.sendVersatiaMessageToPlayer(player, template, "foo", "bar");` |
| Broadcast message template `template` with arguments `"foo"` and `"bar"` to every player, leaded by `$VersatiaCore.PrefixBroadcast$` | `VersatiaMessages.broadcastMessage(template, "foo", "bar");` |
| Broadcast message template `template` with arguments `"foo"` and `"bar"` to console, leaded by `$VersatiaCore.PrefixConsole$` | `VersatiaMessages.sendVersatiaMessageToConsole(template, "foo", "bar");` |
| Broadcast message template `template` with arguments `"foo"` and `"bar"` to either console or player, leaded by proper prefix, as the received message was the result of command invoking | `VersatiaMessages.sendVersatiaMessageToCommandSender(sender, template, "foo", "bar");` |

### Template descriptors

Some of the methods need to pass `VersatiaMessageDescriptor` as the argument. It's the object that uniquely identifies the template from the whole Versatia message's storage (as the same name of the template doesn't have to be unique across modules). You can obtain descriptor for your template, using `createTemplateDescriptor(module, messageTemplateName)` method, where `module` is your instance of `VersatiaModule` and `messageTemplateName` is the name of your message template.