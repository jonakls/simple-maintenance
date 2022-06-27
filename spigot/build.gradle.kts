plugins {
    java
    id("com.github.johnrengelman.shadow") version("7.1.2")
}
repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18-R0.1-SNAPSHOT")
    implementation("org.jetbrains:annotations:23.0.0")

    compileOnly(project(":core"))
}

tasks {
    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    processResources {
        filesMatching("**/*.yml") {
            filter<org.apache.tools.ant.filters.ReplaceTokens>(
                "tokens" to mapOf(
                    "version" to project.version
                )
            )
        }
    }
}