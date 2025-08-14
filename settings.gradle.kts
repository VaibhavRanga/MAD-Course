pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MAD_S10"
include(":app")
include(":week1")
include(":week2")
include(":week3")
include(":week4")
include(":week5")
include(":week6")
include(":week7")
include(":week8")
include(":week9")
include(":week10")
include(":week10-library")
include(":week10-paid-module")
