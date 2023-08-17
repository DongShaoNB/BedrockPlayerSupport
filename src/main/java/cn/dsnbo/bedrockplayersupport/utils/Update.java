package cn.dsnbo.bedrockplayersupport.utils;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.FileUtil;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author DongShaoNB
 */
public class Update {
    public static void checkUpdate() {
        Bukkit.getScheduler().runTaskAsynchronously(BedrockPlayerSupport.getInstance(), () -> {
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
                    BedrockPlayerSupport.getInstance().getLogger().info("§a有新版本可以更新!");
                    BedrockPlayerSupport.getInstance().getLogger().info("§a当前版本: " + nowVersion + " | 最新版本: " + latestVersion);
                } else {
                    BedrockPlayerSupport.getInstance().getLogger().info("§a插件是最新版本，继续保持哦~");
                }
            } else {
                BedrockPlayerSupport.getInstance().getLogger().warning("§4无法检测更新，请检查网络情况，尝试访问 https://update.dsnbo.cn/BedrockPlayerSupport/version 是否正常");
            }
        });

    }

    public static void updateConfig() {
        File configFile = new File(BedrockPlayerSupport.getInstance().getDataFolder(), "/config.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
        if (!configuration.getString("config-version").equals(BedrockPlayerSupport.getInstance().getDescription().getVersion())) {
            File oldConfigFile = new File(BedrockPlayerSupport.getInstance().getDataFolder(), "/old_config.yml");
            if (oldConfigFile.exists()) {
                oldConfigFile.delete();
            }
            while (configFile.renameTo(oldConfigFile)) {
                BedrockPlayerSupport.getInstance().saveResource("config.yml", true);
                YamlConfiguration newConfig = YamlConfiguration.loadConfiguration(configFile);
                YamlConfiguration oldConfig = YamlConfiguration.loadConfiguration(oldConfigFile);
                for (String key : oldConfig.getKeys(true)) {
                    if (!"config-version".equalsIgnoreCase(key)) {
                        newConfig.set(key, oldConfig.get(key));
                    }
                }
                try {
                    newConfig.save(configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    BedrockPlayerSupport.getInstance().getLogger().warning("§e无法更新配置文件，请前往GitHub或QQ群提交该报错!");
                }
                break;
            }
        }
    }

}
