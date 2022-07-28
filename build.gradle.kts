import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    `java-gradle-plugin`
    `maven-publish`
}

project.group = property("projectGroup")!!
project.version = property("version")!!

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())
    compileOnly(kotlin("gradle-plugin", "1.5.21"))
    compileOnly(kotlin("compiler", "1.5.21"))
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    withJavadocJar()
    withSourcesJar()
}

gradlePlugin {
    (plugins) {
        register("test-project") {
            id = "my.project.group.plugin"
            displayName = "Plugin"
            implementationClass = "my.project.group.plugin.MyPlugin"
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}