package com.example.codechallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.R
import com.example.codechallenge.databinding.ListItemBinding
import com.example.codechallenge.model.Character
import com.squareup.picasso.Picasso

class PictureAdapter(
    private val clickListener: OnImageClickListener
) :
    PagingDataAdapter<Character, PictureAdapter.PictureViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PictureViewHolder(
            layoutInflater.inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it, clickListener) }
    }

    class PictureViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(character: Character, action: OnImageClickListener) {
            val binding = ListItemBinding.bind(view)
            Picasso.get().load(character.image).into(binding.itemImage)
            view.setOnClickListener {
                action.onItemClick(character, adapterPosition)
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
            && oldItem.species == newItem.species
    }

}

interface OnImageClickListener {
    fun onItemClick(item: Character, position: Int)
}