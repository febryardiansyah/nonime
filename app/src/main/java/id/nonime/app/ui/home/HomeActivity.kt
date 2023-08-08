package id.nonime.app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.nonime.app.R
import id.nonime.app.adapter.HomeCompleteAdapter
import id.nonime.app.adapter.HomeGenresAdapter
import id.nonime.app.adapter.HomeOngoingAdapter
import id.nonime.app.fragments.GenresBottomSheetFragment
import id.nonime.app.models.GenreModel
import id.nonime.app.ui.list.SearchResultActivity
import id.nonime.app.view_model.GenreListViewModel
import id.nonime.app.view_model.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var genresViewModel: GenreListViewModel
    private lateinit var genresRV: RecyclerView
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        var searchEdt: EditText = findViewById(R.id.etSearch)
        searchEdt.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                val text: String = searchEdt.text.toString().trim()
                val intent = Intent(this, SearchResultActivity::class.java)
                if (text.isNotEmpty()) {
                    intent.putExtra("keyword", text)
                    startActivity(intent)
                    return@setOnEditorActionListener true
                }else{
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(searchEdt.windowToken, 0)
                }
            }
            return@setOnEditorActionListener false
        }
        /**
         * genres
         */
        genresRV = findViewById(R.id.homeGenresRV)
        genresRV.isNestedScrollingEnabled = false
        genresViewModel = GenreListViewModel()
        fetchGenres()

        /**
         * home
         */
        homeViewModel = HomeViewModel()
        fetchHome()

        val swipe: SwipeRefreshLayout = findViewById(R.id.homeSwipe)
        swipe.setOnRefreshListener {
            fetchGenres()
            fetchHome()
            swipe.isRefreshing = false
        }
    }

    private fun fetchGenres() {
        genresViewModel.fetchGenresData()
        subscribeGenres()
    }

    private fun fetchHome() {
        homeViewModel.fetchHome()
        subscribeHome()
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
                    val bottomSheet = GenresBottomSheetFragment(windowManager, data.genreList)
                    bottomSheet.show(supportFragmentManager, "genre_bottom_sheet")
                }
            }
            genresRV.adapter = genresAdapter
        }
    }

    private fun subscribeHome() {
        val ongoingRV: RecyclerView = findViewById(R.id.homeOngoingRV)
        val ongoingProgressBar: ProgressBar = findViewById(R.id.homeOngoingLoading)
        val completeRV: RecyclerView = findViewById(R.id.homeCompleteRV)
        val completeProgressBar: ProgressBar = findViewById(R.id.homeCompleteLoading)

        homeViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                ongoingProgressBar.visibility = View.VISIBLE
                ongoingRV.visibility = View.GONE
                completeProgressBar.visibility = View.VISIBLE
                completeRV.visibility = View.GONE
            }
        }
        homeViewModel.isError.observe(this) { isError ->
            if (isError) {
                Toast.makeText(this, homeViewModel.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        homeViewModel.data.observe(this) { data ->
            /**
             * ongoing
             */
            ongoingProgressBar.visibility = View.GONE
            ongoingRV.visibility = View.VISIBLE
            ongoingRV.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val ongoingAdapter = HomeOngoingAdapter(data.data?.onGoing!!.filterNotNull())
            ongoingRV.adapter = ongoingAdapter

            /**
             * complete
             */
            completeProgressBar.visibility = View.GONE
            completeRV.visibility = View.VISIBLE
            completeRV.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val completeAdapter = HomeCompleteAdapter(data.data.complete!!.filterNotNull())
            completeRV.adapter = completeAdapter
        }
    }
}