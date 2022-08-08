package id.iglo.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.iglo.api_service.service.MovieByGenreService
import id.iglo.common.entity.movie_by_genre.Result

class MovieByGenrePagingDataSource(
    private val movieByGenreService: MovieByGenreService,
    val genre: List<Int>
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val result = movieByGenreService.getMovieByGenre(
                genre.joinToString(","), params.key ?: 1
            )
            result.body()?.let {
                LoadResult.Page(
                    data = it.results,
                    prevKey = if (it.page == 1) null else it.page.minus(1),
                    nextKey = if (it.results.isEmpty()) null else it.page.plus(1)
                )
            } ?: LoadResult.Error(Exception("Invalid Data"))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

object MovieByGenrePager{
    fun createPager(
        pageSize: Int,
        movieByGenreService: MovieByGenreService,
        genre: List<Int>
    ) : Pager<Int,Result> {
        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                MovieByGenrePagingDataSource(movieByGenreService, genre)
            }
        )
    }
}