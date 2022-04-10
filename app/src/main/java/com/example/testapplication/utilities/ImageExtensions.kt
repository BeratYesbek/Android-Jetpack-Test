package com.example.testapplication.utilities

import android.content.Context
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testapplication.R

fun ImageView.downloadedUrl(url: String?, progressBar: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(progressBar).error(R.drawable.ic_launcher_background)

    Glide.with(context).setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
@BindingAdapter("android:downloadUrl")
fun downloadImage(imageView: ImageView?, url: String?) {
    imageView?.downloadedUrl(url, placeHolderProgressBar(imageView.context))
}