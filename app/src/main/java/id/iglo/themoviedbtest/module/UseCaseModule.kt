package id.iglo.themoviedbtest.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.iglo.api_service.service.*
import id.iglo.api_service.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideGenresUseCase(genreService: GenreService) = GenreUseCase(genreService)

    @Provides
    fun provideMoviesByGenresUseCase(
        movieByGenreService: MovieByGenreService
    ) =
        MovieByGenreUseCase(movieByGenreService)

    @Provides
    fun provideMovieDetailUseCase(
        movieDetailService: MovieDetailService
    ) =
        MovieDetailUseCase(movieDetailService)

    @Provides
    fun provideMovieVideoUseCase(
        movieVideoService: MovieVideoService
    ) = MovieVideoUseCase(movieVideoService)

    @Provides
    fun provideMovieReviewUseCase(
        movieReviewService: MovieReviewService
    ) = MovieReviewUseCase(movieReviewService)
}