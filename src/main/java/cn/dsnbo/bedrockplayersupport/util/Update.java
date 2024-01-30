package cn.dsnbo.bedrockplayersupport.util;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import com.tcoded.folialib.FoliaLib;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author DongShaoNB
 */
public class Update {
    private static final String currentVersion = BedrockPlayerSupport.getInstance().getDescription().getVersion();
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
        String owner = "DongShaoNB";
        String repo = "BedrockPlayerSupport";
        try {
            URL url = new URL("https://api.github.com/repos/" + owner + "/" + repo + "/releases/latest");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000); // 设置连接超时为10秒
            conn.setReadTimeout(10000); // 设置读取超时为10秒
            Scanner scanner = new Scanner(conn.getInputStream());
            String response = scanner.useDelimiter("\\Z").next();
            scanner.close();
            String latestVersion = response.substring(response.indexOf("tag_name") + 11);
            latestVersion = latestVersion.substring(0, latestVersion.indexOf("\""));
            if (isUpdateAvailable(latestVersion)) {
                BedrockPlayerSupport.getInstance().getLogger().info("有新版本可以更新!");
                BedrockPlayerSupport.getInstance().getLogger().info("当前版本: " + currentVersion + " | 最新版本: " + latestVersion);
            } else {
                BedrockPlayerSupport.getInstance().getLogger().info("插件是最新版本，继续保持哦~");
            }
        } catch (IOException e) {
            BedrockPlayerSupport.getInstance().getLogger().warning("无法检测更新，请检查网络情况");
        }
    }
    private static boolean isUpdateAvailable(String latestVersion) {
        String[] latestVersionArray = latestVersion.substring(1).split("\\.");
        String[] currentVersionArray = currentVersion.substring(1).split("\\.");
        for (int i = 0; i < Math.min(currentVersionArray.length, latestVersionArray.length); i++) {
            int currentPart = Integer.parseInt(currentVersionArray[i]);
            int latestPart = Integer.parseInt(latestVersionArray[i]);
            if (latestPart > currentPart) return true;
        }
        return false;
    }
}

