package id.iglo.themoviedbtest

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMovieDBTest : Application() {

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, e ->
            Log.e("Application Crash", e.message, e)

        }
    }
}