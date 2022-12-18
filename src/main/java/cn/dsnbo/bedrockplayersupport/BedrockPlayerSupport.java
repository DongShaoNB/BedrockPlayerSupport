package cn.dsnbo.bedrockplayersupport;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class BedrockPlayerSupport extends JavaPlugin implements Listener {

    public static Plugin Plugin;
    public boolean isCMIEnabled;
    public boolean isEssXEnabled;
    public boolean isFloodgateEnabled;

    @Override
    public void onEnable() {
        getLogger().info("感谢选择使用本插件，作者: DongShaoNB，QQ群: 159323818");
        Plugin = BedrockPlayerSupport.getProvidingPlugin(BedrockPlayerSupport.class);
        isCMIEnabled = !(Bukkit.getPluginManager().getPlugin("CMI") == null);
        isEssXEnabled = !(Bukkit.getPluginManager().getPlugin("Essentials") == null);
        isFloodgateEnabled = !(Bukkit.getPluginManager().getPlugin("Floodgate") == null);
        if (isCMIEnabled) {
            Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
        } else if (isEssXEnabled) {
            Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
        } else {
            getLogger().warning("你的服务端中没有 CMI 或 Essentials 基础插件，本插件无法正常运行");
        }
        new Metrics(this, 17107);
    }

    @Override
    public void onDisable() {

    }
}
