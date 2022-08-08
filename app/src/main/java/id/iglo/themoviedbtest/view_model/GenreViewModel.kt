package id.iglo.themoviedbtest.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.SelectionTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import id.iglo.api_service.usecase.GenreUseCase
import id.iglo.common.base.AppResponse
import id.iglo.common.base.BaseViewModel
import id.iglo.common.entity.genre.Genre
import id.iglo.themoviedbtest.ui.genre.GenreFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(application: Application, val genreUseCase: GenreUseCase) :
    BaseViewModel(application) {
    var selectionTracker: SelectionTracker<Long>? = null
    val genreData = MutableLiveData<AppResponse<List<Genre>>>()

    init {
        loadGenre()
    }

    fun loadGenre() {
        viewModelScope.launch {
            genreUseCase().collect {
                genreData.value = it
            }
        }
    }

    fun getGenreForMovie() {
        navigate(
            GenreFragmentDirections.actionGenreFragmentToMovieByGenreFragment(
                selectionTracker?.selection?.toList().orEmpty().map {
                    it.toInt()
                }.toIntArray()
            )
        )
    }
}