package cc.dsnb.bedrockplayersupport

/**
 * @author DongShaoNB
 */
enum class BasicPlugin(
    val pluginName: String = "",
    val tpaCommand: String = "/tpa",
    val tpaHereCommand: String = "/tpahere",
    val tpaAcceptCommand: String = "/tpaccept",
    val tpaRejectCommand: String = "/tpdeny"
) {

    CMI("CMI"),
    ESSENTIALS("EssentialsX"),
    HUSKHOMES("HuskHomes", tpaRejectCommand = "/tpdecline"),
    ADVANCEDTELEPORT("AdvancedTeleport", tpaAcceptCommand = "/tpayes", tpaRejectCommand = "/tpano"),
    SUNLIGHT("SunLight", "/ptp request", "/ptp invite", "/ptp accept", "/ptp decline"),
    NONE;

}