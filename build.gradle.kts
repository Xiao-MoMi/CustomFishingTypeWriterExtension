plugins {
    id("java")
    kotlin("jvm") version "2.0.21"
    id("com.typewritermc.module-plugin") version "1.0.0"
}

group = "net.momirealms"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.3-R0.1-SNAPSHOT")
    compileOnly("com.github.Xiao-MoMi:Custom-Fishing:2.2.32")
}

typewriter {
    engine {
        version = "0.6.1"
    }
    namespace = "customfishing"
    extension {
        name = "CustomFishing"
        shortDescription = "Integrate CustomFishing with Typewriter."
        description = "The CustomFishing extension provides seamless integration with the CustomFishing system, enabling you to utilize and customize fishing-related events with greater flexibility."
        paper {
            dependency("CustomFishing")
        }
    }
}

kotlin {
    jvmToolchain(21)
}