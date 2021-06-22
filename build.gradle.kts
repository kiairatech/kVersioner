plugins {
    application
    kotlin("jvm") version "1.4.20"
}

application {
    mainClassName = "com.tyluur.Launcher"
}

repositories {
    mavenCentral()
}

dependencies {
    // Jvm
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // Kotlin
    implementation(kotlin("reflect"))

    //Utilities
    implementation("commons-codec:commons-codec:1.9")
}

java {
    withSourcesJar()
    withJavadocJar()
}