package id.nonime.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.nonime.app.R
import id.nonime.app.models.OnGoingAnimeModel

class HomeCompleteAdapter(private val animeList: List<OnGoingAnimeModel>) :
    RecyclerView.Adapter<HomeCompleteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.itemCardAnimeTitle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCompleteAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_anime, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeCompleteAdapter.ViewHolder, position: Int) {
        val itemViewModel = animeList[position]
        holder.title.text = itemViewModel.title
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}