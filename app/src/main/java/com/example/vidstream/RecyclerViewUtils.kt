package com.example.vidstream

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vidstream.databinding.ActivityMainVideoItemBinding
import com.example.vidstream.networkUtils.ItemsData

class ItemsViewHolder (
    private val binding: ActivityMainVideoItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ItemsData, context: Context, listener: MyItemClickListener) {

        Glide.with(context)
            .load(item.thumbnail)
            .apply(RequestOptions().placeholder(R.drawable.thumbnail_placeholder))
            .into(binding.thumbnail)

        binding.tvTitle.text = item.title
        binding.tvChannel.text = item.channel

        itemView.setOnClickListener {
            listener.onItemClicked(item)
        }
    }
}

class ItemsAdapter (
    private var items: List<ItemsData>,
    private var context: Context,
    private val listener: MyItemClickListener
): RecyclerView.Adapter<ItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            ActivityMainVideoItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(items[position], context, listener)
    }

    fun refreshList(newItems: List<ItemsData>) {
        items = newItems
        notifyDataSetChanged()
    }
}

interface MyItemClickListener {
    fun onItemClicked(item: ItemsData)
}