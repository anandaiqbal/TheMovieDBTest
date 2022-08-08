package id.iglo.api_service.usecase

import id.iglo.api_service.paging.MovieByGenrePager
import id.iglo.api_service.service.MovieByGenreService

class MovieByGenreUseCase(private val movieByGenreService: MovieByGenreService) {
    operator fun invoke(genre: List<Int>) =
        MovieByGenrePager.createPager(10, movieByGenreService, genre).flow
}