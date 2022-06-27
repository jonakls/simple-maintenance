plugins {
    java
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")

}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
}

tasks {
    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}