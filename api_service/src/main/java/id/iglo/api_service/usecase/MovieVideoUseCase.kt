package id.iglo.api_service.usecase

import id.iglo.api_service.service.MovieVideoService
import id.iglo.common.base.AppResponse
import id.iglo.common.entity.movie_video.Result
import kotlinx.coroutines.flow.flow

class MovieVideoUseCase(private val movieVideoService: MovieVideoService) {
    operator fun invoke(id: Int) = flow<AppResponse<Result>> {
        emit(AppResponse.loading())
        val movieVideoData = movieVideoService.getMovieVideo(id)
        if (movieVideoData.isSuccessful) {
            movieVideoData.body()?.let { data ->
                val filter = data.results.filter {
                    it.type == "Trailer"
                }
                if (data.results.isEmpty()){
                    emit(AppResponse.failure(Exception("Invalid Data")))
                } else {
                    emit(AppResponse.success(filter[0]))
                }
            } ?: run {
                emit(AppResponse.failure(Exception("Invalid Data")))
            }
        } else {
            emit(AppResponse.failure(Exception("Invalid Data")))
        }
    }
}