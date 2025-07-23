package com.mangayo.mangayo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(this@SearchActivity, SearchResultsActivity::class.java)
                intent.putExtra("query", query)
                startActivity(intent)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // Aggiorna la query di ricerca
                return true
            }
        })
    }
}