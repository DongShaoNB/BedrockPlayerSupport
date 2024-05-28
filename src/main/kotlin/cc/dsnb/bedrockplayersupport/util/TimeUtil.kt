package cc.dsnb.bedrockplayersupport.util

object TimeUtil {

    fun measureTimeMillis(code: () -> Unit): Long {
        val startTime = System.nanoTime()
        code()
        val endTime = System.nanoTime()
        return (endTime - startTime) / 1_000_000
    }

}