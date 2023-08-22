package com.example.movietest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import coil.load
import com.example.movietest.databinding.ActivityMain2Binding
import com.example.movietest.model.Search

class MainActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMain2Binding
    lateinit var search: Search
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding =DataBindingUtil.setContentView(this,R.layout.activity_main2)
        search = intent.getParcelableExtra("movie")!!
        binding.iv.load(search.Poster)
        binding.tv.text = "Movie Name : ${search.Title}"
    }
}