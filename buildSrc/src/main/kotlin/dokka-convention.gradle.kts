plugins {
    id("org.jetbrains.dokka")
}

dokka {
    dokkaPublications.html {
        outputDirectory.set(layout.buildDirectory.dir("dokka"))
    }
    dokkaSourceSets.configureEach {
        sourceLink {
            remoteUrl("https://github.com/OmyDaGreat/MaleficExtensions/tree/main")
            localDirectory.set(rootDir)
        }
    }
}
