package id.nonime.app.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.nonime.app.R
import id.nonime.app.adapter.SearchResultAdapter

class SearchResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        val actionBar = supportActionBar
        val keyword = intent.getStringExtra("keyword") ?: "KONSOL"
        actionBar!!.title = keyword
        actionBar.setDisplayHomeAsUpEnabled(true)
        val rv:RecyclerView = findViewById(R.id.searchResultRV)
        rv.adapter = SearchResultAdapter()
        rv.layoutManager = LinearLayoutManager(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}