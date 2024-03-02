package cn.dsnbo.bedrockplayersupport.util;

import java.util.Random;

/**
 * @author DongShaoNB
 */
public class StringUtil {

  public static String randomString(int length) {
    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(62);
      stringBuilder.append(str.charAt(number));
    }
    return stringBuilder.toString();
  }

}
