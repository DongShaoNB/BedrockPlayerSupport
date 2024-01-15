package cn.dsnbo.bedrockplayersupport.config;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import space.arim.dazzleconf.ConfigurationFactory;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.ConfigFormatSyntaxException;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.CommentMode;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

/**
 * @author DongShaoNB
 */
public final class ConfigManager<C> {

	private final ConfigurationHelper<C> configHelper;
	private volatile C configData;

	private ConfigManager(ConfigurationHelper<C> configHelper) {
		this.configHelper = configHelper;
	}

	public static <C> ConfigManager<C> create(Path configFolder, String fileName, Class<C> configClass) {
		// SnakeYaml example
		SnakeYamlOptions yamlOptions = new SnakeYamlOptions.Builder()
				.commentMode(CommentMode.alternativeWriter()) // Enables writing YAML comments
				.build();
		ConfigurationFactory<C> configFactory = SnakeYamlConfigurationFactory.create(
				configClass,
				new ConfigurationOptions.Builder().sorter(new AnnotationBasedSorter()).build(), // change this if desired
				yamlOptions);
		return new ConfigManager<>(new ConfigurationHelper<>(configFolder, fileName, configFactory));
	}

	public void reloadConfig() {
		try {
			configData = configHelper.reloadConfigData();
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);

		} catch (ConfigFormatSyntaxException ex) {
			configData = configHelper.getFactory().loadDefaults();
			BedrockPlayerSupport.getInstance().getLogger().severe("配置文件格式错误, 请检查你的配置文件");
			ex.printStackTrace();

		} catch (InvalidConfigException ex) {
			configData = configHelper.getFactory().loadDefaults();
			BedrockPlayerSupport.getInstance().getLogger().severe("配置文件某个键值无效, 请检查你的配置文件");
			ex.printStackTrace();
		}
	}

	public C getConfigData() {
		C configData = this.configData;
		if (configData == null) {
			throw new IllegalStateException("配置文件还没有加载");
		}
		return configData;
	}

}
