package id.nonime.app.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.nonime.app.R
import id.nonime.app.adapter.DetailAnimeEpisodeAdapter
import id.nonime.app.adapter.DetailAnimeGenreAdapter
import id.nonime.app.models.EpisodeModel
import id.nonime.app.models.GenreModel
import id.nonime.app.view_model.DetailAnimeViewModel

class DetailAnime : AppCompatActivity() {
    private lateinit var detailViewModel: DetailAnimeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_anime)
        supportActionBar?.hide()
        val id = intent.getStringExtra("id") ?: ""
        detailViewModel = DetailAnimeViewModel(id)
        subscribeData()

        val backBtn: ImageButton = findViewById(R.id.detailAnimeBackBtn)
        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun subscribeData() {
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
        detailViewModel.data.observe(this) {
            backgroundImg.load(it.thumb)
            thumbnail.load(it.thumb)

            title.text = it.title
            japaneseTitle.text = it.japanese
            score.text = it.score.toString()
            studio.text = it.studio
            status.text = it.status
            duration.text = it.duration
            releaseDate.text = it.releaseDate

            val genresRv: RecyclerView = findViewById(R.id.detailAnimeGenreRV)
            val genresLayoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            genresRv.layoutManager = genresLayoutManager
            genresRv.adapter = DetailAnimeGenreAdapter(it.genreList!!.filterNotNull())

            synopsis.text = it.synopsis.takeUnless { text -> text!!.isEmpty() } ?: "-"

            val episodeRv: RecyclerView = findViewById(R.id.detailAnimeEpisodesRV)
            episodeRv.layoutManager = LinearLayoutManager(this)
            episodeRv.adapter = DetailAnimeEpisodeAdapter(it.episodeList!!.filterNotNull())
        }
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
    EpisodeModel("Episode 1", "1"),
    EpisodeModel("Episode 2", "1"),
    EpisodeModel("Episode 3", "1"),
    EpisodeModel("Episode 4", "1"),
    EpisodeModel("Episode 5", "1"),
    EpisodeModel("Episode 6", "1"),
)