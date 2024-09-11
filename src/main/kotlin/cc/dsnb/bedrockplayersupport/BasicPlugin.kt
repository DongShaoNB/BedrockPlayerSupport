package cc.dsnb.bedrockplayersupport

/**
 * @author DongShaoNB
 */
enum class BasicPlugin(
    val pluginName: String,
    val tpaCommand: String,
    val tpaHereCommand: String,
    val tpaAcceptCommand: String,
    val tpaRejectCommand: String
) {

    CMI("CMI", "/tpa", "/tpahere", "/tpaccept", "/tpdeny"),
    ESSENTIALS("EssentialsX", "/tpa", "/tpahere", "/tpaccept", "/tpdeny"),
    HUSKHOMES("HuskHomes", "/tpa", "/tpahere", "/tpaccept", "/tpdecline"),
    ADVANCEDTELEPORT("AdvancedTeleport", "/tpa", "/tpahere", "/tpayes", "/tpano"),
    SUNLIGHT("SunLight", "/ptp request", "/ptp invite", "/ptp accept", "/ptp decline"),
    NONE("None", "None", "None", "None", "None");

}