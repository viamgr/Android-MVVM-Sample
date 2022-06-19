dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Near By"
include(":app")
include(":common-ui")
include(":data")
include(":model")
include(":domain")
include(":data-model")
include(":ui:nearme")