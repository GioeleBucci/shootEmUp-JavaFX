plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

javafx {
    version = "20"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("App")
}
