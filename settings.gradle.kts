//enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    //enableFeaturePreview("VERSION_CATALOGS")
    repositories {
        google()
        mavenCentral()
        //maven {url 'https://jitpack.io'}
    }
    //versionCatalogs {
        //create("libs"){

        //}
    //}
}

rootProject.name = "Narodi"
include(":app")
 