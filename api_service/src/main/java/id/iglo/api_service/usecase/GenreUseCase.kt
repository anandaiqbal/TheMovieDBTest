package id.iglo.api_service.usecase

import id.iglo.api_service.service.GenreService
import id.iglo.common.base.AppResponse
import id.iglo.common.entity.genre.Genre
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class GenreUseCase(private val genreService: GenreService) {
    operator fun invoke() = flow<AppResponse<List<Genre>>> {
        emit(AppResponse.loading())
        val genreData = genreService.getGenre()
        if (genreData.isSuccessful)
            emit(genreData.body()?.let {
                AppResponse.success(it.genres)
            } ?: run {
                AppResponse.failure(
                    Exception("Invalid Data")
                )
            })
        else {
            emit(AppResponse.failure(Exception("Invalid Data")))
        }
    }
}