package id.iglo.themoviedbtest.ui.movie_by_genre

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import id.iglo.common.base.BaseFragment
import id.iglo.themoviedbtest.R
import id.iglo.themoviedbtest.databinding.MovieByGenreFragmentBinding
import id.iglo.themoviedbtest.ui.base.BaseLoadAdapter
import id.iglo.themoviedbtest.view_model.MovieByGenreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieByGenreFragment : BaseFragment<MovieByGenreViewModel, MovieByGenreFragmentBinding>() {
    override val layoutId: Int = R.layout.movie_by_genre_fragment
    override val vm: MovieByGenreViewModel by viewModels()
    private val adapter = MovieByGenreAdapter {
        vm.getMovieForDetail(it.id)
    }
    private val loadStateAdapter = BaseLoadAdapter(::retryAction)
    private val args by navArgs<MovieByGenreFragmentArgs>()

    override fun initBinding(binding: MovieByGenreFragmentBinding) {
        super.initBinding(binding)
        binding.moviesRecycler.adapter = adapter
        vm.loadMovie(args.genre.toList())
        vm.movieByGenreData.observe(this) {
            CoroutineScope(Dispatchers.Main).launch {
                adapter.submitData(it)
            }
        }
        binding.moviesRecycler.adapter = adapter.withLoadStateFooter(loadStateAdapter)
        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading && adapter.itemCount == 0) {
                binding.loading.visibility = View.VISIBLE
            } else if (it.refresh is LoadState.Error && adapter.itemCount == 0) {
                binding.loading.visibility = View.GONE
                binding.moviesRecycler.visibility = View.GONE
                binding.retryButt.visibility = View.VISIBLE
            } else if (it.refresh is LoadState.NotLoading && adapter.itemCount == 0) {
                binding.loading.visibility = View.GONE
                binding.noMovie.visibility = View.VISIBLE
            } else if (it.refresh is LoadState.NotLoading) {
                binding.loading.visibility = View.GONE
            }
        }
        binding.retryButt.setOnClickListener {
            adapter.retry()
            binding.loading.visibility = View.GONE
            binding.moviesRecycler.visibility = View.VISIBLE
            binding.retryButt.visibility = View.GONE
        }
    }

    fun retryAction() {
        adapter.retry()
        binding.moviesRecycler.visibility = View.VISIBLE
    }
}