package com.example.movietest.bindingadapter

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import coil.load
import com.example.movietest.R
import org.jsoup.Jsoup


class RecipesRowBinding {
    companion object {


        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {

            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_launcher_background)
            }
        }






        @BindingAdapter("parseHtml")
        @JvmStatic
        fun parseHtml(textview: TextView, description: String?) {
            if (description != null) {
                val desc =Jsoup.parse(description).text()
                textview.text =desc
            }
        }
    }
}