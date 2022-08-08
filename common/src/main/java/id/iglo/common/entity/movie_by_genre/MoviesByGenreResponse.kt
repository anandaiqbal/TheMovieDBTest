package id.iglo.common.entity.movie_by_genre

import com.google.gson.annotations.SerializedName

data class MoviesByGenreResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)