import org.jmailen.gradle.kotlinter.tasks.FormatTask
import org.jmailen.gradle.kotlinter.tasks.LintTask

val user: String by project
val repo: String by project
val g: String by project
val artifact: String by project
val v: String by project
val desc: String by project

val localMavenRepo = uri(layout.buildDirectory.dir("repo").get())

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.dokka)
}

repositories {
    mavenCentral()
}

group = g
version = v

tasks.apply {
    register<LintTask>("ktLint") {
        group = "kotlint"
        source(files("core/src", "compose/src"))
        reports.set(
            mapOf(
                "plain" to file("build/lint-report.txt"),
                "json" to file("build/lint-report.json"),
            ),
        )
    }
    register<FormatTask>("ktFormat") {
        group = "kotlint"
        source(files("core/src", "compose/src"))
        report.set(file("build/format-report.txt"))
    }
    register("ktFormatAndLint") {
        group = "kotlint"
        dependsOn(named("ktFormat"))
        dependsOn(named("ktLint"))
    }
    register("publishAllToCentralPortal") {
        group = "publishing"
        description = "Publish all of the subprojects to central portal"
        dependsOn(":core:publishToCentralPortal")
        dependsOn(":compose:publishToCentralPortal")
        dependsOn(named("ktFormatAndLint"))
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.dokka")
    dependencies {
        testImplementation(kotlin("test"))
    }
    tasks.test {
        useJUnitPlatform()
    }
}
