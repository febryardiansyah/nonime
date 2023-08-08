package id.nonime.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.nonime.app.R
import id.nonime.app.models.AnimeItemModel
import id.nonime.app.ui.detail.DetailAnimeActivity

class SearchResultAdapter(private val results: List<AnimeItemModel>) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val title:TextView = view.findViewById(R.id.searchResultItemTitle)
        val img: ImageView = view.findViewById(R.id.searchResultItemThumb)
        val status: TextView = view.findViewById(R.id.searchResultItemStatus)
        val score: TextView = view.findViewById(R.id.searchResultItemStar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = results[position]
        holder.title.text = item.title
        holder.img.load(item.thumb)
        holder.status.text = item.status
        holder.score.text = item.score?.toString() ?: "-"
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailAnimeActivity::class.java)
            intent.putExtra("id",item.id)
            holder.itemView.context.startActivity(intent)
        }
    }
}