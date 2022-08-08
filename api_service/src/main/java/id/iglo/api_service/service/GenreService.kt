package id.iglo.api_service.service

import id.iglo.common.base.Constants
import id.iglo.common.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") api_key: String = Constants.API_KEY
    ): Response<GenreResponse>
}