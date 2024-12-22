val user: String by project
val repo: String by project
val g: String by project
val artifact: String by project
val v: String by project
val desc: String by project

val localMavenRepo = uri(layout.buildDirectory.dir("repo").get())

plugins {
    alias(libs.plugins.kotlin.jvm)
}

repositories {
    mavenCentral()
}

group = g
version = v

subprojects {
    if (name != "docs") {
        tasks.apply {
            register("formatAndLintKotlin") {
                group = "formatting"
                description = "Formats and lints Kotlin using Kotlinter"
                dependsOn(named("formatKotlin"))
                dependsOn(named("lintKotlin"))
            }
        }
    }
}

tasks.apply {
    register("formatAndLintKotlin") {
        group = "formatting"
        description = "Formats and lints Kotlin using Kotlinter"
        dependsOn(":core:formatAndLintKotlin")
        dependsOn(":compose:formatAndLintKotlin")
    }
    register("publishAllToCentralPortal") {
        group = "publishing"
        description = "Publish all of the subprojects to central portal"
        dependsOn(":core:publishToCentralPortal")
        dependsOn(":compose:publishToCentralPortal")
    }
    build {
        dependsOn(":docs:dokkaGenerate")
        dependsOn(":core:build")
        dependsOn(":compose:build")
        dependsOn(named("formatAndLintKotlin"))
    }
}
