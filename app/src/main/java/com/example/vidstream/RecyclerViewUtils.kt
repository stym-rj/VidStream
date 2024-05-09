package com.example.vidstream

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.example.vidstream.databinding.ActivityMainVideoItemBinding
import com.example.vidstream.networkUtils.ItemsData

class ItemsViewHolder (
    private val binding: ActivityMainVideoItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ItemsData, context: Context) {
        val player = ExoPlayer.Builder(context).build()
        binding.vid.player = player

        val mediaItem = MediaItem.fromUri(item.video)
        player.setMediaItem(mediaItem)
        player.prepare()
//        player.play()

        binding.tvTitle.text = item.title
        binding.tvChannel.text = item.channel

    }
}

class ItemsAdapter (
    private var items: List<ItemsData>,
    private var context: Context
): RecyclerView.Adapter<ItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            ActivityMainVideoItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(items[position], context)
    }

    fun refreshList(newItems: List<ItemsData>) {
        items = newItems
        notifyDataSetChanged()
    }
}