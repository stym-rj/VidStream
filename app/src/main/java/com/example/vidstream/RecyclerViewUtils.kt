package com.example.vidstream

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.example.vidstream.databinding.ActivityMainVideoItemBinding
import com.example.vidstream.networkUtils.ItemsData
import com.google.common.reflect.Reflection.getPackageName

class ItemsViewHolder (
    private val binding: ActivityMainVideoItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ItemsData, context: Context) {
        val player = ExoPlayer.Builder(context).build()
        binding.vid.player = player

        val packageName = context.packageName
        val mediaItem = MediaItem.fromUri("android.resource://$packageName/raw/retrofit")
        player.setMediaItem(mediaItem)
        player.prepare()
//        player.play()
    }
}

class ItemsAdapter (
    private var items: MutableList<ItemsData>,
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
}