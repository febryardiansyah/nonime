package id.nonime.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.nonime.app.R
import id.nonime.app.models.EpisodeListItem

class DetailAnimeEpisodeAdapter(private val episodes: List<EpisodeListItem>) :
    RecyclerView.Adapter<DetailAnimeEpisodeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.detailAnimeEpisodeTitle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailAnimeEpisodeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_anime_episode_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = episodes[position]
        holder.title.text = item.title
    }
}