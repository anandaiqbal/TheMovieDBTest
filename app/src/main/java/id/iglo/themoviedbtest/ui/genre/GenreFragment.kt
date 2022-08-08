package id.iglo.themoviedbtest.ui.genre

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import dagger.hilt.android.AndroidEntryPoint
import id.iglo.common.base.BaseFragment
import id.iglo.themoviedbtest.R
import id.iglo.themoviedbtest.databinding.GenreFragmentBinding
import id.iglo.themoviedbtest.view_model.GenreViewModel

@AndroidEntryPoint
class GenreFragment : BaseFragment<GenreViewModel, GenreFragmentBinding>() {
    override val layoutId: Int = R.layout.genre_fragment
    override val vm: GenreViewModel by viewModels()
    private val adapter= GenreAdapter{
        vm.selectionTracker?.isSelected(it) ?: false
    }

    override fun initBinding(binding: GenreFragmentBinding) {
        super.initBinding(binding)
        binding.genresRecycler.adapter = adapter
        createTracker()
        observeResponseData(vm.genreData, {
            binding.loading.visibility = View.GONE
            binding.fab.visibility = View.VISIBLE
            adapter.differ.submitList(it)
        }, {
            binding.loading.visibility = View.GONE
            binding.retryButt.visibility = View.VISIBLE
        }, {
            binding.loading.visibility = View.VISIBLE
            binding.fab.visibility = View.GONE
        })
        binding.retryButt.setOnClickListener {
            vm.loadGenre()
            binding.retryButt.visibility = View.GONE
            binding.loading.visibility = View.GONE
            binding.genresRecycler.visibility = View.VISIBLE
        }
        binding.fab.setOnClickListener {
            vm.getGenreForMovie()
        }


    }

    fun createTracker() {
        vm.selectionTracker = vm.selectionTracker?.let {
            SelectionTracker.Builder<Long>(
                "genreId", binding.genresRecycler,
                GenreItemKeyProvider(adapter), GenreItemDetailsLookup(binding.genresRecycler),
                StorageStrategy.createLongStorage()
            ).withOnItemActivatedListener { itemDetails, _ ->
                vm.selectionTracker?.select(itemDetails.selectionKey ?: 0)
                true
            }.withSelectionPredicate(SelectionPredicates.createSelectAnything()).build().apply {
                it.selection.forEach {
                    this.select(it)
                }
            }
        } ?: run {
            SelectionTracker.Builder<Long>(
                "genreId", binding.genresRecycler,
                GenreItemKeyProvider(adapter), GenreItemDetailsLookup(binding.genresRecycler),
                StorageStrategy.createLongStorage()
            ).withOnItemActivatedListener { itemDetails, _ ->
                vm.selectionTracker?.select(itemDetails.selectionKey ?: 0)
                true
            }.withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()
        }
    }


}