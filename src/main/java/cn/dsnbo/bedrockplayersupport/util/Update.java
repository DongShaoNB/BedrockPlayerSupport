package cn.dsnbo.bedrockplayersupport.util;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author DongShaoNB
 */
public class Update {

    private static final int SPIGOT_RESOURCE_ID = 1145141919;
	
    public static void checkUpdate(Consumer<String> consumer) {
        BedrockPlayerSupport.getScheduler().runTaskAsynchronously(() -> getVersion(consumer));
    }

  public static void getVersion(final Consumer<String> consumer) {
    try (InputStream is = new URL(
        "https://api.spigotmc.org/legacy/update.php?resource=" + SPIGOT_RESOURCE_ID
            + "/~").openStream(); Scanner scann = new Scanner(is)) {
      if (scann.hasNext()) {
        consumer.accept(scann.next());
      }
    } catch (IOException e) {
      BedrockPlayerSupport.getInstance().getLogger()
          .info("Unable to check for updates: " + e.getMessage());
    }
  }
}

