import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest {
        attributes["Main-Class"] = "ServerKt"
    }
}