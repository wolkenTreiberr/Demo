import org.gradle.kotlin.dsl.named

plugins {
    kotlin("jvm") version "1.9.22"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.named("classes") {
    dependsOn("ktlintCheck")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}
