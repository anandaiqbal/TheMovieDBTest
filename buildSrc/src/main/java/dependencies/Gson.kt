package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.gson() {
    implementation("com.google.code.gson:gson:2.9.1")
}