package com.example.codechallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.R
import com.example.codechallenge.databinding.ListItemBinding
import com.example.codechallenge.model.Character
import com.squareup.picasso.Picasso

class PictureAdapter(
    private val modelList: List<Character>,
    private val clickListener: OnImageClickListener
) :
    RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

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

    override fun getItemCount(): Int = modelList.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bindData(modelList[position], clickListener)
    }

    class PictureViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: Character, action: OnImageClickListener) {
            val binding = ListItemBinding.bind(view)
            Picasso.get().load(model.image).into(binding.itemImage)
            view.setOnClickListener {
                action.onItemClick(model, adapterPosition)
            }
        }
    }
}

interface OnImageClickListener {
    fun onItemClick(item: Character, position: Int)
}
