package id.iglo.api_service.usecase

import id.iglo.api_service.paging.MovieReviewPager
import id.iglo.api_service.service.MovieReviewService

class MovieReviewUseCase(private val movieReviewService: MovieReviewService) {
    operator fun invoke(id: Int) =
        MovieReviewPager.createPager(10, movieReviewService, id).flow
}