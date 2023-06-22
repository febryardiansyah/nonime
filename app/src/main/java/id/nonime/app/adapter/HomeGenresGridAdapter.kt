package id.nonime.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.nonime.app.R
import id.nonime.app.models.GenreModel

class HomeGenresAdapter(private val genres: List<GenreModel?>) :
    RecyclerView.Adapter<HomeGenresAdapter.ViewHolder>() {
    private var listener: ((GenreModel) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.genreTittleTV)
        val img: ImageView = itemView.findViewById(R.id.genreIV)
        fun bind(item: GenreModel) {
            itemView.setOnClickListener {
                listener?.invoke(item)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeGenresAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.genre_gridview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeGenresAdapter.ViewHolder, position: Int) {
        val item = genres[position]
        holder.title.text = item?.genreName
        if (item?.id == "lainnya") {
            holder.img.scaleType = ImageView.ScaleType.CENTER
            holder.img.setImageResource(R.drawable.ic_grid_view)
        } else {
            holder.img.load(item?.imageLink){
                placeholder(R.drawable.logo)
                error(R.drawable.anime_placeholder)
            }
        }
        holder.bind(item!!)
    }

    override fun getItemCount(): Int = genres.size

    fun setOnItemClickListener(listener: (GenreModel) -> Unit) {
        this.listener = listener
    }
}