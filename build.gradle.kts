plugins {
    kotlin("jvm") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "cc.dsnb"
version = "2.0.0"

repositories {
    mavenCentral()
    maven {
        // PaperAPI
        name = "papermc"
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    maven {
        // Floodgate
        name = "opencollab-snapshot"
        url = uri("https://repo.opencollab.dev/main/")
    }
    maven {
        // EssentialsX
        name = "essentialsx"
        url = uri("https://repo.essentialsx.net/releases/")
    }
    maven {
        // HuskHomes
        name = "william278.net"
        url = uri("https://repo.william278.net/releases")
    }
    maven {
        // AuthMe
        name = "codemc-repo"
        url = uri("https://repo.codemc.org/repository/maven-public/")
    }
    maven {
        // UniversalScheduler
        name = "jitpack"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    // PaperAPI
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    // Floodgate
    compileOnly("org.geysermc.floodgate:api:2.2.2-SNAPSHOT")
    // CMILib
    compileOnly(files("lib/CMILib1.4.7.15.jar"))
    // CMI-API
    compileOnly(files("lib/CMI-API9.7.0.1.jar"))
    // EssentialsX
    compileOnly("net.essentialsx:EssentialsX:2.20.1")
    // HuskHomes
    compileOnly("net.william278.huskhomes:huskhomes-bukkit:4.6.3")
    // AuthMe
    compileOnly("fr.xephi:authme:5.6.0-SNAPSHOT")
    // CatSeedLogin
    compileOnly(files("lib/CatSeedLogin-1.4.1-SNAPSHOT.jar"))
    // NexEngine (use NexAuth API to login or register player depend on NexEngine)
    compileOnly(files("lib/NexEngine.jar"))
    // NexAuth
    compileOnly(files("lib/NexAuth-2.0.5.jar"))
    // bStats
    implementation("org.bstats:bstats-bukkit:3.0.2")
    // DazzleConf
    implementation("space.arim.dazzleconf:dazzleconf-ext-snakeyaml:1.3.0-M2")
    // UniversalScheduler
    implementation("com.github.Anon8281:UniversalScheduler:0.1.6")
}

tasks.test {
    useJUnitPlatform()
}
tasks.processResources {
    doLast {
        val pluginFile = project.projectDir.resolve("build/resources/main/plugin.yml")
        val pluginContent = pluginFile.readText()
        val outputContent = pluginContent.replace("\${pluginVersion}", version.toString())
        pluginFile.writeText(outputContent)
    }
}
tasks.shadowJar {
    // bStats
    relocate("org.bstats", "cc.dsnb.bedrockplayersupport.bstats")
    // DazzleConf
    relocate("space.arim.dazzleconf", "cc.dsnb.bedrockplayersupport.dazzleconf")
    // UniversalScheduler
    relocate("com.github.Anon8281.universalScheduler", "cc.dsnb.bedrockplayersupport.universalScheduler")
}
kotlin {
    jvmToolchain(17)
}
