package cn.dsnbo.bedrockplayersupport.util;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import com.tcoded.folialib.FoliaLib;
import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author DongShaoNB
 */
public class Update {
    public static void checkUpdate() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            FoliaLib foliaLib = new FoliaLib(BedrockPlayerSupport.getInstance());
            foliaLib.getImpl().runAsync((task) -> checkUpdateV());
        } catch (ClassNotFoundException e) {
            Bukkit.getScheduler().runTaskAsynchronously(BedrockPlayerSupport.getInstance(), Update::checkUpdateV);
        }
    }

    public static void checkUpdateV() {
        String latestVersion;
        try {
            URL url = new URL("https://update.dsnbo.cn/BedrockPlayerSupport/version");
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            latestVersion = bufferedReader.readLine();
        } catch (Exception e) {
            latestVersion = null;
        }

        if (latestVersion != null) {
            String nowVersion = BedrockPlayerSupport.getInstance().getDescription().getVersion();
            if (!nowVersion.equals(latestVersion)) {
                BedrockPlayerSupport.getInstance().getLogger().info("有新版本可以更新!");
                BedrockPlayerSupport.getInstance().getLogger().info("当前版本: " + nowVersion + " | 最新版本: " + latestVersion);
            } else {
                BedrockPlayerSupport.getInstance().getLogger().info("插件是最新版本，继续保持哦~");
            }
        } else {
            BedrockPlayerSupport.getInstance().getLogger().warning("无法检测更新，请检查网络情况，尝试访问 https://update.dsnbo.cn/BedrockPlayerSupport/version 是否正常");
        }
    }

    public static void updateConfig() {
        File configFile = new File(BedrockPlayerSupport.getInstance().getDataFolder(), "/config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        if (!config.getString("config-version").equals(BedrockPlayerSupport.getInstance().getDescription().getVersion())) {
            BedrockPlayerSupport.getInstance().getLogger().info("正在更新配置文件到最新版本(当前版本: {nowVersion} 内置版本: {latestVersion})"
                    .replace("{nowVersion}", config.getString("config-version"))
                    .replace("{latestVersion}", BedrockPlayerSupport.getInstance().getDescription().getVersion()));
            File oldConfigFile = new File(BedrockPlayerSupport.getInstance().getDataFolder(), "/old_config.yml");

            if (oldConfigFile.exists()) {
                oldConfigFile.delete();
            }

            if (configFile.renameTo(oldConfigFile)) {
                BedrockPlayerSupport.getInstance().saveResource("config.yml", false);
                config = YamlConfiguration.loadConfiguration(configFile);
                YamlConfiguration oldConfig = YamlConfiguration.loadConfiguration(oldConfigFile);
                for (String key : oldConfig.getKeys(true)) {
                    if (!"config-version".equalsIgnoreCase(key) && oldConfig.get(key).getClass() != MemorySection.class) {
                        config.set(key, oldConfig.get(key));
                    }
                }

                try {
                    config.save(configFile);
                    config = YamlConfiguration.loadConfiguration(configFile);
                    if (config.getString("config-version").equals(BedrockPlayerSupport.getInstance().getDescription().getVersion())) {
                        BedrockPlayerSupport.getInstance().getLogger().info("成功更新配置文件");
                    }
                } catch (IOException e) {
                    BedrockPlayerSupport.getInstance().getLogger().warning("配置文件更新失败，请将问题反馈至作者");
                    e.printStackTrace();
                }
            }
        }
    }
}

