package com.mangayo.mangayo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mangayo.mangayo.adapter.ImagePagerAdapter
import com.mangayo.mangayo.adapter.ProductAdapter
import com.mangayo.mangayo.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var bannerViewPager2: ViewPager2
    private val images = intArrayOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3,
        R.drawable.banner4
    )

    private var currentPage = 0
    private val delay = 5000L // 5 secondi
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bannerViewPager2 = findViewById(R.id.viewPager2Banner)

        val bannerAdapter = ImagePagerAdapter(images)
        bannerViewPager2.adapter = bannerAdapter

        startAutoScroll()

        val products = ArrayList<Product>()

        for (n in 1..6) {
            val product = Product("Prodotto $n", (n).toFloat())
            products.add(product)
        }

        //Ultime Uscite
        val latestReleaseRecyclerView = findViewById<RecyclerView>(R.id.latestReleaseRecyclerView)
        val latestReleaseProductAdapter = ProductAdapter(products)
        latestReleaseRecyclerView.apply {
            adapter = latestReleaseProductAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        //Novità dal Giappone
        val latestJapanRecyclerView = findViewById<RecyclerView>(R.id.latestJapanRecyclerView)
        val latestJapanProductAdapter = ProductAdapter(products)
        latestJapanRecyclerView.apply {
            adapter = latestJapanProductAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        //Novità Figures
        val latestFiguresRecyclerView = findViewById<RecyclerView>(R.id.latestFiguresRecyclerView)
        val latestFiguresProductAdapter = ProductAdapter(products)
        latestFiguresRecyclerView.apply {
            adapter = latestFiguresProductAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        //Più popolari
        val mostPopularRecyclerView = findViewById<RecyclerView>(R.id.mostPopularRecyclerView)
        val mostPopularProductAdapter = ProductAdapter(products)
        mostPopularRecyclerView.apply {
            adapter = mostPopularProductAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }



        findViewById<ImageView>(R.id.search_Icon).setOnClickListener {
            // Crea un Intent per aprire la nuova schermata
            val intent = Intent(this, SearchActivity::class.java)

            // Avvia la nuova schermata
            startActivity(intent)
        }

    }

    private fun startAutoScroll() {
        // Avvia la coroutine per lo scorrimento delle immagini
        job = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                // Incrementa l'indice della pagina
                currentPage++

                // Se siamo arrivati all'ultima immagine, ritorniamo alla prima
                if (currentPage >= images.size) {
                    currentPage = 0
                }

                // Cambia la pagina del ViewPager2 per mostrare la nuova immagine
                bannerViewPager2.setCurrentItem(currentPage, true)

                // Aggiorna l'immagine ogni 5 secondi
                delay(delay)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Annulla la coroutine quando l'activity viene distrutta per evitare memory leak
        job?.cancel()
    }
}