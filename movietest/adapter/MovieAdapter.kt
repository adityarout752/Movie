package com.example.movietest.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.databinding.MovieItemLayoutBinding
import com.example.movietest.model.MovieModel
import com.example.movietest.model.Search
import com.example.movietest.util.onMovieItemClick

class MovieAdapter(val context: Context,val movieData:MovieModel,private val onMovieItemClick: onMovieItemClick) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: MovieItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(search: Search) {
            binding.search = search
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return movieData.Search.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentposition =movieData.Search[position]
        holder.bind(currentposition)
        holder.binding.root.setOnClickListener {
           onMovieItemClick.onMovieClicked(currentposition)
        }
    }
}