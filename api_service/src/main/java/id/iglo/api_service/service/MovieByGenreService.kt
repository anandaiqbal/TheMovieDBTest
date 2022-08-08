package id.iglo.api_service.service

import id.iglo.common.base.Constants
import id.iglo.common.entity.movie_by_genre.MoviesByGenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieByGenreService {
    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("with_genres") with_genres: String,
        @Query("page") page: Int = 1,
        @Query("api_key") api_key: String = Constants.API_KEY
    ): Response<MoviesByGenreResponse>
}