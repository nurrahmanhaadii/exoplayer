package id.haadii.learn.exoplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getFilm()

        viewModel.setFilm().observe(this, Observer {
            rv_film.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = FilmAdapter(it.hits) { hits ->
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("url", hits.videos.medium.url)
                    intent.putExtra("hits", hits)
                    startActivity(intent)
                }
            }
        })

    }
}
