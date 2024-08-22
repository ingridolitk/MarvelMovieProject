package com.example.marvelmovie.presentation.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelmovie.databinding.ItemRvBinding
import com.example.marvelmovie.model.MovieResult
import com.example.marvelmovie.utils.layoutInflater

class MovieAdapter(
    private val list: List<MovieResult>,
    private val clickListener: (MovieResult) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        binding = ItemRvBinding.inflate(parent.layoutInflater, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val currentItem = list[position]

        if (list[position].poster !== "") {
            holder.imgMovie.let {
                Glide.with(holder.imgMovie.context).load(
                    list[position].poster
                ).into(it)
            }
            holder.imgMovie.setOnClickListener {
                clickListener.invoke(currentItem)
            }

        }
        if (holder is MovieViewHolder)
            holder.title.text = currentItem.title
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class MovieViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgMovie: ImageView = binding.imgMovie
        val title: TextView = binding.titleMovie

    }
}