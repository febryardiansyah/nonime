package id.nonime.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.nonime.app.R
import id.nonime.app.models.GenreModel

class DetailAnimeGenreAdapter(private val genres: List<GenreModel>) :
    RecyclerView.Adapter<DetailAnimeGenreAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.detailAnimeGenreTitle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailAnimeGenreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_anime_genre_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailAnimeGenreAdapter.ViewHolder, position: Int) {
        val item = genres[position]
        holder.title.text = item.genreName
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}