package id.nonime.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.nonime.app.R
import id.nonime.app.models.OnGoingAnimeModel
import id.nonime.app.ui.detail.DetailAnime

class HomeOngoingAdapter(private val animeList: List<OnGoingAnimeModel>) :
    RecyclerView.Adapter<HomeOngoingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        val title: TextView = itemView.findViewById(R.id.itemCardAnimeTitle)
        override fun onClick(v: View) {
            val intent = Intent(v.context, DetailAnime::class.java)
            v.context.startActivity(intent)
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
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}