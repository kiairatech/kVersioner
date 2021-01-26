plugins {
    application
    kotlin("jvm") version "1.4.20"
}

val junitVersion = "5.6.2"

application {
    mainClassName = "org.redrune.Launcher"
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "idea")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "redrune667"
    version = "0.0.1"

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    repositories {
        mavenCentral()
        mavenLocal()
        jcenter()
        maven(url = "https://repo.maven.apache.org/maven2")
        maven(url = "https://jitpack.io")
    }
}

dependencies {
    // Jvm
    implementation(kotlin("stdlib"))

    // Kotlin
    implementation(kotlin("reflect"))
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "1.4.2")

    // Network
    implementation("io.netty:netty-all:4.1.44.Final")

    // Logging
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    //Utilities
    implementation("commons-codec:commons-codec:1.9")
    //* // https://mvnrepository.com/artifact/commons-codec/commons-codec
    //compile group: 'commons-codec', name: 'commons-codec', version: '1.9'*/

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
