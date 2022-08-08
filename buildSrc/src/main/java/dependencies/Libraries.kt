package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.libraries() {
    androidX()
    coroutine()
    daggerHilt()
    glide()
    gson()
    lottie()
    materialDesign()
    okHttp()
    retrofit()
    testUnit()
    youtubePlayer()
}