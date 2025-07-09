plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.8.0"
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.kofastack.authapi.ApplicationKt")
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-core-jvm:2.3.4")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.4")

    // Serialização
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.4")

    // MongoDB
    implementation("org.litote.kmongo:kmongo:4.9.0")
    implementation("org.litote.kmongo:kmongo-coroutine:4.9.0")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.11")

    // Testes
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("at.favre.lib:bcrypt:0.9.0")
}
