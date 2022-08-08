package id.iglo.themoviedbtest.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.iglo.api_service.retrofit.RetrofitClient
import id.iglo.api_service.service.*
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenre(retrofit: Retrofit) = retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideMovieByGenre(retrofit: Retrofit) =
        retrofit.create(MovieByGenreService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetail(retrofit: Retrofit) = retrofit.create(MovieDetailService::class.java)

    @Provides
    @Singleton
    fun provideMovieReview(retrofit: Retrofit) = retrofit.create(MovieReviewService::class.java)

    @Provides
    @Singleton
    fun provideMovieVideo(retrofit: Retrofit) = retrofit.create(MovieVideoService::class.java)
}