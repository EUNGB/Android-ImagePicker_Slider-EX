package com.meltingB.imagepicker_slider_ex

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.esafirm.imagepicker.model.Image


class ImageSliderAdapter(private var context: Context, private var images: List<Image>) :
    RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_slide, parent, false)
    ) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (images.isEmpty()) {
            Glide.with(context)
                .load(R.drawable.noimage)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView)
        } else {
            images[position].let {
                with(holder) {

                    imageView.isVisible = true
                    Glide.with(context)
                        .load(it.uri)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(imageView)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (images.isNotEmpty()) images.size else 1
    }

}