package id.iglo.themoviedbtest.ui.movie_by_genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.iglo.common.entity.movie_by_genre.Result
import id.iglo.themoviedbtest.databinding.MovieByGenreFragmentItemBinding


class MovieByGenreAdapter (
    val getMovie: (Result) -> Unit
) : PagingDataAdapter<Result, MoviesByGenresViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: MoviesByGenresViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
        holder.binding.cardView.setOnClickListener{
            if (data != null) {
                getMovie(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByGenresViewHolder {
        return MoviesByGenresViewHolder(
            MovieByGenreFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }

}

class MoviesByGenresViewHolder(val binding: MovieByGenreFragmentItemBinding) :
    RecyclerView.ViewHolder(binding.root)