package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidX() {
    implementation("androidx.core:core-ktx:1.8.0")

    implementation("androidx.annotation:annotation:1.4.0")

    implementation("androidx.appcompat:appcompat:1.4.2")

    implementation("androidx.activity:activity-ktx:1.5.1")

    implementation("androidx.fragment:fragment-ktx:1.5.1")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")

    implementation("androidx.paging:paging-runtime:3.1.1")

    implementation("androidx.navigation:navigation-fragment-ktx:2.5.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.1")


    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
}