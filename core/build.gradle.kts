import cn.lalaki.pub.BaseCentralPortalPlusExtension.PublishingType

val user: String by project
val repo: String by project
val g: String by project
val artifact: String by project
val a = "$artifact-core"
val v: String by project
val desc: String by project

val localMavenRepo = uri(layout.buildDirectory.dir("repo").get())

plugins {
    alias(libs.plugins.central)
    `maven-publish`
    signing
    kotlin("jvm")
}

group = g
version = v

repositories {
    mavenCentral()
}

java {
    withJavadocJar()
    withSourcesJar()
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
    jvmToolchain(17)
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation(kotlin("stdlib-jdk8"))
}

publishing {
    publications {
        create<MavenPublication>("core") {
            groupId = g
            artifactId = a
            version = v

            from(components["java"])

            pom {
                name.set(repo)
                description.set(desc)
                url.set("https://github.com/$user/$repo")
                developers {
                    developer {
                        name.set("Om Gupta")
                        email.set("ogupta4242@gmail.com")
                    }
                }
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/$user/$repo.git")
                    developerConnection.set("scm:git:ssh://github.com/$user/$repo.git")
                    url.set("https://github.com/$user/$repo")
                }
            }
        }
        repositories {
            maven {
                url = localMavenRepo
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

centralPortalPlus {
    url = localMavenRepo
    tokenXml = uri(rootProject.layout.projectDirectory.file("user_token.xml"))
    publishingType = PublishingType.AUTOMATIC
}

tasks.test {
    useJUnitPlatform()
}
