plugins {
    kotlin("jvm") apply false
    `dokka-convention`
}

dependencies {
    dokka(project(":core"))
    dokka(project(":compose"))
}

repositories {
    mavenCentral()
}

dokka {
    moduleName.set("MaleficExtensions")

    dokkaPublications.html {
        includes.from("DocsModule.md")
    }
}
