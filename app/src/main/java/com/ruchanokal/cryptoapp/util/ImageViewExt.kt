package com.ruchanokal.cryptoapp.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ruchanokal.cryptoapp.R
import com.ruchanokal.cryptoapp.util.Constants.IMAGE_BASE_URL


fun ImageView.downloadImage(url: String, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_baseline_error_24)


    val lastURL = "${IMAGE_BASE_URL}${url}.png"

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(lastURL)
        .into(this)
}



fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun download(view: ImageView, url: String?) {

    url?.let {
        view.downloadImage(it, placeholderProgressBar(view.context))
    }
}

