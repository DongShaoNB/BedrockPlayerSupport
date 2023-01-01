package cn.dsnbo.bedrockplayersupport.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Update {
    public static String getLatestVersion() {
        String latestVersion;
        try {
            URL url = new URL("https://update.dsnbo.cn/BedrockPlayerSupport/version");
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            latestVersion = bufferedReader.readLine();
        } catch (Exception e) {
            latestVersion = null;
        }
        return latestVersion;
    }
}
