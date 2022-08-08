package id.iglo.api_service.usecase

import id.iglo.api_service.service.MovieDetailService
import id.iglo.common.base.AppResponse
import id.iglo.common.entity.movie_detail.MovieDetailResponse
import kotlinx.coroutines.flow.flow

class MovieDetailUseCase(private val movieDetailService: MovieDetailService) {
    operator fun invoke(id: Int) = flow<AppResponse<MovieDetailResponse>> {
        emit(AppResponse.loading())
        val movieDetailData = movieDetailService.getMovieDetail(id)
        if (movieDetailData.isSuccessful) {
            movieDetailData.body()?.let {
                emit(AppResponse.success(it))
            } ?: run {
                emit(AppResponse.failure(Exception("Invalid Data")))
            }
        } else {
            emit(AppResponse.failure(Exception("Invalid Data")))
        }
    }
}