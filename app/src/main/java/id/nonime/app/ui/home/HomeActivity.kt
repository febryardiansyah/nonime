package id.nonime.app.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.nonime.app.R
import id.nonime.app.adapter.HomeCompleteAdapter
import id.nonime.app.adapter.HomeGenresAdapter
import id.nonime.app.adapter.HomeOngoingAdapter
import id.nonime.app.fragments.GenresBottomSheetFragment
import id.nonime.app.models.GenreModel
import id.nonime.app.models.OnGoingAnimeModel

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        /**
         * genres
         */
        val genreMore = GenreModel("Lainnya", "lainnya")
        val convertedGenres: MutableList<GenreModel> = dummyGenres.subList(0, 8).toMutableList()
        convertedGenres[convertedGenres.size - 1] = genreMore

        val genresRV: RecyclerView = findViewById(R.id.homeGenresRV)
        genresRV.layoutManager = GridLayoutManager(this, 4)
        val genresAdapter = HomeGenresAdapter(convertedGenres)
        genresAdapter.setOnItemClickListener {
            if (it.id == "lainnya") {
                val bottomSheet = GenresBottomSheetFragment(windowManager)
                bottomSheet.show(supportFragmentManager, "genre_bottom_sheet")
//                val dialog = BottomSheetDialog(this, R.style.rounded_bottom_sheet_theme)
//                val bottomView = LayoutInflater.from(this)
//                    .inflate(R.layout.genre_bottom_sheet, null, false)
//                dialog.setContentView(bottomView)
//                dialog.show()
            }
        }
        genresRV.adapter = genresAdapter

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