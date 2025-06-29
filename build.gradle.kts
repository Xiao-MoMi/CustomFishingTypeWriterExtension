plugins {
    id("java")
    kotlin("jvm") version "2.0.21"
    id("com.typewritermc.module-plugin") version "1.1.2"
}

group = "net.momirealms"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://repo.momirealms.net/releases/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.typewritermc.com/beta/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("net.momirealms:custom-fishing:2.3.7")
}

typewriter {
    namespace = "customfishing"
    extension {
        name = "CustomFishing"
        shortDescription = "Integrate CustomFishing with Typewriter."
        description = "The CustomFishing extension provides seamless integration with the CustomFishing system, enabling you to utilize and customize fishing-related events with greater flexibility."
        engineVersion = "0.8.0"
        paper {
            dependency("CustomFishing")
        }
    }
}

kotlin {
    jvmToolchain(21)
}