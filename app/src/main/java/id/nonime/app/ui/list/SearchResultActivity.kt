package id.nonime.app.ui.list

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.nonime.app.R
import id.nonime.app.adapter.SearchResultAdapter
import id.nonime.app.view_model.SearchResultViewModel

class SearchResultActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchResultViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        val actionBar = supportActionBar
        val keyword = intent.getStringExtra("keyword") ?: ""
        actionBar!!.title = keyword
        actionBar.setDisplayHomeAsUpEnabled(true)
        viewModel = SearchResultViewModel(keyword)
        subscribeData()

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun subscribeData(){
        val rv:RecyclerView = findViewById(R.id.searchResultRV)
        val progressBar: ProgressBar = findViewById(R.id.searchResultPB)
        viewModel.isLoading.observe(this){
            if(it){
                progressBar.visibility = View.VISIBLE
                rv.visibility = View.GONE
            }
        }
        viewModel.data.observe(this){
            progressBar.visibility = View.GONE
            rv.visibility = View.VISIBLE

            rv.adapter = SearchResultAdapter(it.results!!)
            rv.layoutManager = LinearLayoutManager(this)
        }
    }
}