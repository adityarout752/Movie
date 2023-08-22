package com.example.movietest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.adapter.MovieAdapter
import com.example.movietest.databinding.ActivityMainBinding
import com.example.movietest.model.MovieModel
import com.example.movietest.model.Search
import com.example.movietest.moviewviewmodel.MovieViewModel
import com.example.movietest.util.onMovieItemClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,onMovieItemClick{
    private lateinit var binding:ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
    private var isLoading = false
    private var list:MovieModel?=null
    private var page=1
    lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        val movieMap= HashMap<String, String>()
        movieMap["apikey"] = "efb53b4b"
        movieMap["s"] = "Batman"
        movieMap["page"] = "1"
        movieViewModel.getMovies(movieMap)
       movieViewModel.readMovies.observe(this){
           adapter =MovieAdapter(this,it[0].movie,this)
          binding.rvMovieList.adapter = adapter
           list = it[0].movie
       }

        binding.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() ==
                        list!!.Search.size- 1) {
                        movieMap["page"] = page++.toString()

                        movieViewModel.getMovies(movieMap)
                      adapter.notifyDataSetChanged()
                        isLoading = true
                    }
                }
            }
        })
    }

    override fun onMovieClicked(search: Search) {
       val intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("movie",search)
        startActivity(intent)
    }
}