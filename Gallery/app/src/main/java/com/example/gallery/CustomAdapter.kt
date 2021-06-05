package com.example.gallery

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val data: MutableList<Image>, private val mainActivity: MainActivity): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rat: RatingBar
        val img: ImageView
        init {
            rat = view.findViewById(R.id.ratingBar)
            img = view.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(data[position].image)
        holder.rat.numStars = 5
        holder.rat.rating = data[position].rating

        holder.itemView.setOnClickListener {
            mainActivity.showImageDetail(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

