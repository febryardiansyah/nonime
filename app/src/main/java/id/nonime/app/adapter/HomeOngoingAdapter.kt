package id.nonime.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.nonime.app.R
import id.nonime.app.models.AnimeItemModel
import id.nonime.app.models.OnGoingAnimeModel
import id.nonime.app.ui.detail.DetailAnime

class HomeOngoingAdapter(private val animeList: List<AnimeItemModel>) :
    RecyclerView.Adapter<HomeOngoingAdapter.ViewHolder>() {
    private var listener: ((AnimeItemModel) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.itemCardAnimeTitle)
        val img: ImageView = itemView.findViewById(R.id.itemCardAnimeThumb)
        val episode: TextView = itemView.findViewById(R.id.itemCardAnimeEpisode)
        fun bind(item: AnimeItemModel) {
            itemView.setOnClickListener {
                listener?.invoke(item)
                val intent = Intent(itemView.context, DetailAnime::class.java)
                intent.putExtra("id", item.id)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeOngoingAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_anime, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeOngoingAdapter.ViewHolder, position: Int) {
        val itemViewModel = animeList[position]
        holder.title.text = itemViewModel.title
        holder.episode.text = itemViewModel.episode
        holder.img.load(itemViewModel.thumb) {
            placeholder(R.drawable.logo)
        }
        holder.bind(itemViewModel)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}