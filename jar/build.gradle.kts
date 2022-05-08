plugins {
    java
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

dependencies {
    implementation(rootProject.project(":core"))
    implementation(rootProject.project(":spigot"))
    implementation(rootProject.project(":bungeecord"))
    implementation(rootProject.project(":velocity"))
}

tasks {
    shadowJar {
        archiveBaseName.set("CommandBridge")
        minimize()
    }
}