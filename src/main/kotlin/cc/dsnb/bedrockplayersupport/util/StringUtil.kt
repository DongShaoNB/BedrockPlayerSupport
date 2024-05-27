package cc.dsnb.bedrockplayersupport.util

import java.util.Random

/**
 * @author DongShaoNB
 */
object StringUtil {

    fun randomString(length: Int): String {
        val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = Random()
        val stringBuilder = StringBuilder()
        for (i in 0 until length) {
            val number = random.nextInt(62)
            stringBuilder.append(str[number])
        }
        return stringBuilder.toString()
    }

}