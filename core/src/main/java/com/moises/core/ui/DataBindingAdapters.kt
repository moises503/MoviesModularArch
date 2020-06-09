package com.moises.core.ui

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.moises.core.constants.Constants.URL_IMAGE
import com.squareup.picasso.Picasso


@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = true)
fun loadImage(view: ImageView, imageUrl: String, placeholder: Drawable) =
    Picasso.get()
        .load(URL_IMAGE + imageUrl)
        .placeholder(placeholder)
        .into(view)