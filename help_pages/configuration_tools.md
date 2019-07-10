## Configuration tools

Versatia provides some nice tools, which may be useful when it comes to read data from config.

### Get or throw

It allows you to throw exception with informative message rather than plain `NullPointerException` in case that node in requested config is missing. You can also easily move through sections, with custom exception message if they are missing. Look at the example:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleInitializer initializer) { 
			VersatiaConfigurationProcessor config = initializer.getConfigProcessor("myconfigs/example.yml");
			boolean flag = config.getBooleanOrThrow("Flag", "Oops, someone captured the flag!");
			config = config.moveToSectionOrThrow("NextSection", "Where is the NextSection...?");
			String secretMessage = config.getColoredStringOrThrow("SecretMessage", "You don't keep secrets in config?");
			config.moveToSectionOrThrow("EvenDeeperSection", "Where is the NextSection.EvenDeeperSection?!");
			String bottomHierarchyMessage = config.getStringOrThrow("BottomString", "Whoa, bottom-less config! Ha ha.");

			//You can even load MessageDescriptors!
			VersatiaMessageDescriptor messageTemplate = config.getMessageDescriptorOrThrow("WhoaDescriptor", "No message template?!");
		}
	}

For file ***pluginname/myconfigs/example.yml***:

    Flag: true
    NextSection:
      SecretMessage: "&6&lWhen you love someone, then that someone will never disappear. - Winnie The Pooh"
      EvenDeeperSection:
        BottomString: "China"
        WhoaDescriptor: $VersatiaCore.PrefixPlayer$

The variables will have values:

`flag` = true;

`secretMessage` = When you love someone, then that someone will never disappear. - 
Winnie The Pooh *(in golden bold font)*

`bottomHierarchyMessage` = China

`messageTemplate` = *Descriptor pointing to message template `PrefixPlayer` from `VersatiaCore`**

If above were missing, module would throw `NullPointerException` with specified message.

### Do if present

Some parts of configuration should be optional. This forces developer to make many get-null-checks.
Versatia comes with more elegant solution here. Look at the example:

	public class YourPluginClass extends VersatiaPlugin {
		@Override
		protected void onVersatiaEnable(VersatiaModuleInitializer initializer) { 
			VersatiaConfigurationProcessor config = initializer.getConfigProcessor("myconfigs/example.yml");
			String databaseURL = config.getStringOrThrow("DatabaseURL, "URL not found!");
			config.ifSectionPresent("CustomProperties", subsection -> {
				config.ifIntPresent("MaximumPoolSize", this::setMaximumPoolSize);
				config.ifBooleanPresent("CachePreparedStatements", this::setPreparedStatementsCatching);
			});
		}
		
		private void setMaximumPoolSize(int size) {
			//Setting database's maximum pool size
		}

		private void setPreparedStatementsCatching(boolean toCatch) {
			//Setting database's maximum pool size
		}
	}

Methods `setMaximumPoolSize` and `setPreparedStatementsCatching` will get invoked for such config:

    DatabaseURL: "jdbc:mysql://localhost/database"
    CustomProperties:
      MaximumPoolSize: 10
      CachePreparedStatements: true
