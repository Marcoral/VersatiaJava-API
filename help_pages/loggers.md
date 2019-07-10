## Loggers system
There is a problem using default Bukkit loggers - you can not distinguish important messages from regular ones using colors. Therefore many developers decides to log their messages using `Bukkit.getConsoleSender().sendMessage()` method.
Unfortunelly, this solution also have its cons (look at [Messages system help page](./messages.md) to learn more). So, here comes Versatia with its loggers system.

### Creating loggers

Logs works in simmilar manner to Versatia's messages sent to console but they also have its priority.
Log will be printed only if the priority is not lower than corresponding logger's threshold.
Although VersatiaCore provides one, default logger, any module can add any number of custom ones.
All you have to do is to put loggers configuration files under ***resources/loggers/*** directory (or any of its subdirectories).

Suppose that your ***resources/loggers/SomeLoggersFile.yml*** looks like this:

    MyPrimitiveLogger: INFO
    MyFancyLogger:
      PriorityThreshold: INFO
	  PrefixFinest: $VersatiaCore.LoggersPrefixFinest$
	  PrefixFiner: $VersatiaCore.LoggersPrefixFiner$
	  PrefixFine: $VersatiaCore.LoggersPrefixFine$
	  PrefixInfo: $VersatiaCore.LoggersPrefixInfo$
	  PrefixWarning: $VersatiaCore.LoggersPrefixWarning$
	  PrefixError: $VersatiaCore.LoggersPrefixError$
	  PrefixSevere: $VersatiaCore.LoggersPrefixSevere$
    YetAnotherLogger:
      PriorityThreshold: Finest
      GenericPrefix: $VersatiaCore.LoggersPrefixInfo$
      PrefixSevere: $VersatiaCore.LoggersPrefixSevere$

You can reference those loggers this way:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void afterBeingBuilded() {
			VersatiaLogger logger1 = getCorrespondingModule().getLogger("MyPrimitiveLogger");
			VersatiaLogger logger2 = getCorrespondingModule().getLogger("MyFancyLogger");
			VersatiaLogger logger3 = getCorrespondingModule().getLogger("YetAnotherLogger");
		}
	}

First two loggers work exactly the same way. Versatia's loggers engine scans for ***PriorityThreshold*** data (if you don't need to define whole configuration section, you can put the value just after the colon, like for *MyPrimitiveLogger*).
Then it looks for message templates corresponding to logs prefixes of various priority. If one is not found, the default will be used. Look at the table below:

| LoggingPriority enum | Description |Example use| Config key | Default value |
|--|--|--|--|--|
| FINEST | Should be used for logging least important messages, indicated by operations which can not be failed.|Information that module registered new logger. Cool, but... who cares? | PrefixFinest|`$VersatiaCore.LoggersPrefixFinest$`
| FINER | Should be used for logging messages indicated by common operations which are generally not expected to fail. | Information whether submodule was registered correctly. This can fail if the exception is thrown during its `reload()` method, but it is rather rare case for well-designed modules. |PrefixFiner|`$VersatiaCore.LoggersPrefixFiner$`
| FINE | Should be used for logging messages indicated by operations which are important to work of the server, but generally user knows well, when they are called and the result is the same as expected. | Information that submodule was successfully reloaded. |PrefixFine|`$VersatiaCore.LoggersPrefixFine$`
| INFO| Should be used for logging messages providing meaningful information about work of the server. | Information that newly registered command just overwritten another, existing one with the same name. It is kind of important information, isn't it?|PrefixInfo|`$VersatiaCore.LoggersPrefixInfo$`
| WARNING| One should use this priority if something looks certainly not as expected, but doesn't necessarily have to be an error. |Information that command was not registered correctly, as its priority was not greater than priority of the command which was registered earlier and have the same name. Not error yet, but it's  at least confusing.|PrefixWarning|`$VersatiaCore.LoggersPrefixWarning$`
| ERROR| Should be used for all those nasty situations, where something fucks up. | Information that the exception was thrown during reloading/shutting down some submodule.|PrefixError|`$VersatiaCore.LoggersPrefixError$`
| SEVERE| Should be used for logging deadly sins, which should force the server owner to shut down the server and resolve the error. | Information that error occurred during first submodule loading. If it can not load even for the first time, something is definitely wrong with the configuration.|PrefixSevere|`$VersatiaCore.LoggersPrefixSevere$`

If you don't want to redefine all prefix manually, you can use `GenericPrefix` flag, which will set all unspecified prefixes to the given value. That means that  `YetAnotherLogger` from the example above, will use `$VersatiaCore.LoggersPrefixInfo$` prefix for any logs, except the ones with priority `SEVERE`.

### Using loggers
Using loggers is simple. You just invoke the method named after the log's priority, specifying the message template (or the descriptor) and template arguments.

There are also methods like `setPriorityThreshold` and `setPrefix` for changing logger's settings.