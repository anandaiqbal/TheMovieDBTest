package id.iglo.themoviedbtest.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.iglo.api_service.usecase.MovieByGenreUseCase
import id.iglo.common.base.BaseViewModel
import id.iglo.common.entity.movie_by_genre.Result
import id.iglo.themoviedbtest.ui.movie_by_genre.MovieByGenreFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieByGenreViewModel @Inject constructor(
    application: Application, val movieByGenreUseCase: MovieByGenreUseCase
) : BaseViewModel(application) {
    val movieByGenreData = MutableLiveData<PagingData<Result>>()

    fun loadMovie(genre: List<Int>) {
        if (movieByGenreData.value == null) {
            viewModelScope.launch {
                movieByGenreUseCase.invoke(genre).cachedIn(viewModelScope).collect {
                    movieByGenreData.postValue(it)
                }
            }
        } else {
            movieByGenreData.postValue(movieByGenreData.value)
        }
    }

    fun getMovieForDetail(movie: Int) {
        navigate(
            MovieByGenreFragmentDirections.actionMovieByGenreFragmentToMovieDetailFragment(
                movie
            )
        )
    }
}