package com.alharoof.movieez

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alharoof.movieez.MoviesAdapter.MovieViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_list_item.ivPoster
import kotlinx.android.synthetic.main.movie_list_item.tvGenres
import kotlinx.android.synthetic.main.movie_list_item.tvImdbRating
import kotlinx.android.synthetic.main.movie_list_item.tvReleased
import kotlinx.android.synthetic.main.movie_list_item.tvTitle

class MoviesAdapter(private val movies: MutableList<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateData(newData: List<Movie>) {
        movies.clear()
        movies.addAll(newData)
        notifyDataSetChanged()
    }

    class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindView(movie: Movie) {
            tvTitle.text = movie.title
            tvGenres.text = movie.genres.joinToString(separator = " | ")
            tvReleased.text = movie.year.toString()
            tvImdbRating.text = movie.rating.toString()

            Picasso.get()
                .load(movie.posterUrl)
                .resize(convertDpToPx(itemView.context, 80f).toInt(), convertDpToPx(itemView.context, 120f).toInt())
                .centerCrop()
                .into(ivPoster)
        }

        private fun convertDpToPx(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }
    }
}