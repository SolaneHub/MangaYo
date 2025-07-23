package com.mangayo.mangayo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mangayo.mangayo.adapter.SearchProductAdapter
import com.mangayo.mangayo.model.Product

class SearchResultsActivity : AppCompatActivity() {
    private lateinit var bannerViewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(this@SearchResultsActivity, SearchResultsActivity::class.java)
                intent.putExtra("query", query)
                startActivity(intent)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // Aggiorna la query di ricerca
                return true
            }
        })

        bannerViewPager2 = findViewById(R.id.viewPager2Banner)

        val products = ArrayList<Product>()

        for (n in 1..6) {
            val product = Product("Prodotto $n", (n).toFloat())
            products.add(product)
        }

        val searchResultsView = findViewById<RecyclerView>(R.id.search_results)
        val searchResultsAdapter = SearchProductAdapter(products)
        searchResultsView.apply {
            adapter = searchResultsAdapter
            layoutManager = LinearLayoutManager(this@SearchResultsActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}