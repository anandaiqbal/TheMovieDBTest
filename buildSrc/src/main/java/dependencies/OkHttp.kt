package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.okHttp() {
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.10")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
}