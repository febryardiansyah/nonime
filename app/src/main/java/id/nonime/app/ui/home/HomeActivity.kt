package id.nonime.app.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.nonime.app.R
import id.nonime.app.adapter.HomeCompleteAdapter
import id.nonime.app.adapter.HomeGenresAdapter
import id.nonime.app.adapter.HomeOngoingAdapter
import id.nonime.app.fragments.GenresBottomSheetFragment
import id.nonime.app.models.GenreModel
import id.nonime.app.models.OnGoingAnimeModel
import id.nonime.app.view_model.GenreListViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var genresViewModel: GenreListViewModel
    private lateinit var genresRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        /**
         * genres
         */
        genresRV = findViewById(R.id.homeGenresRV)
        genresRV.isNestedScrollingEnabled = false
        genresViewModel = GenreListViewModel()
        genresViewModel.getGenresData()
        subscribeGenres()

        /**
         * ongoing
         */
        val ongoingRV: RecyclerView = findViewById(R.id.homeOngoingRV)
        ongoingRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val ongoingAdapter = HomeOngoingAdapter(dummyOngoing)
        ongoingRV.adapter = ongoingAdapter

        /**
         * complete
         */
        val completeRV: RecyclerView = findViewById(R.id.homeCompleteRV)
        completeRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val completeAdapter = HomeCompleteAdapter(dummyOngoing)
        completeRV.adapter = completeAdapter
    }

    private fun subscribeGenres() {
        val progress = findViewById<ProgressBar>(R.id.homeGenresLoading)
        genresViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                progress.visibility = View.VISIBLE
                genresRV.visibility = View.GONE
            }
        }
        genresViewModel.isError.observe(this) { isError ->
            if (isError) {
                progress.visibility = View.GONE
                genresRV.visibility = View.GONE
            }
        }
        genresViewModel.genreData.observe(this) { data ->
            progress.visibility = View.GONE
            genresRV.visibility = View.VISIBLE
            val convertedGenres: MutableList<GenreModel?> =
                data.genreList!!.subList(0, 8).toMutableList()
            convertedGenres[convertedGenres.size - 1] = GenreModel("Lainnya", "lainnya")

            genresRV.layoutManager = GridLayoutManager(this, 4)
            val genresAdapter = HomeGenresAdapter(convertedGenres)
            genresAdapter.setOnItemClickListener {
                if (it.id == "lainnya") {
                    val bottomSheet = GenresBottomSheetFragment(windowManager)
                    bottomSheet.show(supportFragmentManager, "genre_bottom_sheet")
                }
            }
            genresRV.adapter = genresAdapter
        }
    }
}


val dummyGenres: List<GenreModel> = listOf(
    GenreModel("Action", "action"),
    GenreModel("Adventure", "action"),
    GenreModel("Comedy", "action"),
    GenreModel("Demons", "action"),
    GenreModel("Drama", "action"),
    GenreModel("Ecchi", "action"),
    GenreModel("Fantasy", "action"),
    GenreModel("Game", "action"),
    GenreModel("Harem", "action"),
    GenreModel("Historical", "action"),
    GenreModel("Horror", "action"),
    GenreModel("Josei", "action"),
    GenreModel("Action", "action"),
    GenreModel("Adventure", "action"),
    GenreModel("Comedy", "action"),
    GenreModel("Demons", "action"),
    GenreModel("Drama", "action"),
    GenreModel("Ecchi", "action"),
    GenreModel("Fantasy", "action"),
    GenreModel("Game", "action"),
    GenreModel("Harem", "action"),
    GenreModel("Historical", "action"),
    GenreModel("Horror", "action"),
    GenreModel("Josei", "action"),
)

private val dummyOngoing: List<OnGoingAnimeModel> = listOf(
    OnGoingAnimeModel("a", "Anime", "https:google.com", "10"),
    OnGoingAnimeModel("a", "Anime", "https:google.com", "10"),
    OnGoingAnimeModel("a", "Anime", "https:google.com", "10"),
    OnGoingAnimeModel("a", "Anime", "https:google.com", "10"),
    OnGoingAnimeModel("a", "Anime", "https:google.com", "10"),
    OnGoingAnimeModel("a", "Anime", "https:google.com", "10"),
    OnGoingAnimeModel("a", "Anime", "https:google.com", "10"),
)