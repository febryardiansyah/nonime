package id.nonime.app.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import id.nonime.app.R
import id.nonime.app.adapter.DetailAnimeEpisodeAdapter
import id.nonime.app.adapter.DetailAnimeGenreAdapter
import id.nonime.app.models.EpisodeModel
import id.nonime.app.models.GenreModel

class DetailAnime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_anime)
        supportActionBar?.hide()

        val backgroundImg: ImageView = findViewById(R.id.detailAnimeBackground)
        val thumbnail: ImageView = findViewById(R.id.detailAnimeThumbnail)
        val title: TextView = findViewById(R.id.detailAnimeTitle)
        val japaneseTitle: TextView = findViewById(R.id.detailAnimeJapaneseTitle)
        val score: TextView = findViewById(R.id.detailAnimeScore)
        val studio: TextView = findViewById(R.id.detailAnimeStudio)
        val status: TextView = findViewById(R.id.detailAnimeStatus)
        val duration: TextView = findViewById(R.id.detailAnimeDuration)
        val releaseDate: TextView = findViewById(R.id.detailAnimeReleaseDate)
        val synopsis: TextView = findViewById(R.id.detailAnimeSynopsis)
        val backBtn: ImageButton = findViewById(R.id.detailAnimeBackBtn)
        backBtn.setOnClickListener {
            finish()
        }

        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .build()
        val backgroundImgRequest = ImageRequest.Builder(this)
            .data("https://m.media-amazon.com/images/M/MV5BYjEwNjEwYzgtZGZkMy00MTBjLTg2MmYtNDk0MzY2NmU0MmNiXkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_FMjpg_UX1000_.jpg")
            .target(backgroundImg)
            .build()
        val thumbnailImgRequest = ImageRequest.Builder(this)
            .data("https://m.media-amazon.com/images/M/MV5BYjEwNjEwYzgtZGZkMy00MTBjLTg2MmYtNDk0MzY2NmU0MmNiXkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_FMjpg_UX1000_.jpg")
            .target(thumbnail)
            .build()
        imageLoader.enqueue(backgroundImgRequest)
        imageLoader.enqueue(thumbnailImgRequest)

        title.text = "Kaguya sama"
        japaneseTitle.text = "Kaguya sama love is war"
        score.text = "8.6"
        studio.text = "LIDENFILMS"
        status.text = "Ongoing"
        duration.text = "23 Menit"
        releaseDate.text = "Apr 11, 2021"

        val genresRv: RecyclerView = findViewById(R.id.detailAnimeGenreRV)
        val genresLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        genresRv.layoutManager = genresLayoutManager
        genresRv.adapter = DetailAnimeGenreAdapter(dummyGenres)

        synopsis.text =
            """"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue sapien et metus luctus, non consectetur metus tristique. Aenean iaculis neque eget quam euismod volutpat. Donec vehicula aliquam ex, et laoreet augue. Donec diam eros, condimentum sit amet mattis non, tempor in libero. Maecenas tempus eros rutrum, venenatis ex in, porta ex. Aliquam erat volutpat. Fusce pulvinar aliquet ipsum at ultrices. Donec nunc est, tincidunt non tellus ac, aliquam hendrerit tortor. Integer aliquam urna nisl, id porttitor purus ultricies in.
                |Phasellus tincidunt, odio in cursus fermentum, massa turpis bibendum sapien, id fermentum urna augue ac mauris. Aenean ac urna neque. Proin nunc ante, aliquet consectetur congue at, tristique ac orci. Nullam et elit aliquet, luctus justo quis, semper tellus. Phasellus tincidunt neque ut magna accumsan suscipit. Proin diam mi, sodales pretium dui quis, mattis maximus tortor. Nunc scelerisque felis at magna cursus, et laoreet metus viverra. Nam ligula est, fermentum vitae congue in, imperdiet finibus enim.
            """.trimMargin()

        val episodeRv: RecyclerView = findViewById(R.id.detailAnimeEpisodesRV)
        episodeRv.layoutManager = LinearLayoutManager(this)
        episodeRv.adapter = DetailAnimeEpisodeAdapter(dummyEpisodes)
    }
}

private val dummyGenres: List<GenreModel> = listOf(
    GenreModel("Action", "action"),
    GenreModel("Action", "action"),
    GenreModel("Action", "action"),
    GenreModel("Action", "action"),
    GenreModel("Action", "action"),
    GenreModel("Action", "action"),
    GenreModel("Action", "action"),
    GenreModel("Action", "action"),
)

private val dummyEpisodes: List<EpisodeModel> = listOf(
    EpisodeModel("Episode 1","1"),
    EpisodeModel("Episode 2","1"),
    EpisodeModel("Episode 3","1"),
    EpisodeModel("Episode 4","1"),
    EpisodeModel("Episode 5","1"),
    EpisodeModel("Episode 6","1"),
)