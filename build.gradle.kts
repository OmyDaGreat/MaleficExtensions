import cn.lalaki.pub.BaseCentralPortalPlusExtension.PublishingType

val v = "1.0.0"
val localMavenRepo = uri(layout.buildDirectory.dir("repo").get())

plugins {
  kotlin("jvm")
  id("org.jetbrains.compose")
  id("org.jetbrains.kotlin.plugin.compose")
  id("maven-publish")
  alias(libs.plugins.spotless)
  alias(libs.plugins.central)
  signing
}

group = "xyz.malefic"
version = v

repositories {
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  google()
}

dependencies {
  implementation(compose.desktop.common)
  implementation(compose.animation)
  implementation(compose.foundation)
  implementation(libs.precompose)
}

spotless {
  kotlin {
    ktfmt().googleStyle()
  }
}

java {
  withJavadocJar()
  withSourcesJar()
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      groupId = "xyz.malefic"
      artifactId = "maleficextensions"
      version = v

      from(components["java"])

      pom {
        name.set("MaleficExtensions")
        description.set("A Compose Desktop library containing common extension methods")
        url.set("https://github.com/OmyDaGreat/MaleficExtensions")
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
          connection.set("scm:git:git://github.com/OmyDaGreat/MaleficExtensions.git")
          developerConnection.set("scm:git:ssh://github.com/OmyDaGreat/MaleficExtensions.git")
          url.set("https://github.com/OmyDaGreat/MaleficExtensions")
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
  username = project.findProperty("centralPortalUsername") as String? ?: ""
  password = project.findProperty("centralPortalPassword") as String? ?: ""
  publishingType = PublishingType.AUTOMATIC
}
