package com.adulting21.adulting21

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchResultsAdapter(private val results: List<SearchResults>) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.result_imageview)
        val textView: TextView = itemView.findViewById(R.id.result_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        // Load the image using an image loading library (e.g., Glide, Picasso)
        // Set the text and image URL
        holder.textView.text = result.Name

        // Glide.with(holder.imageView).load(result.Image).into(holder.imageView)
        // Load image using Glide
        Glide.with(holder.imageView.context)
            .load(result.Image) // Assuming `result.Image` is the URL
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return results.size
    }
}
