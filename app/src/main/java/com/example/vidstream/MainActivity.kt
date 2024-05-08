package com.example.vidstream

import android.media.browse.MediaBrowser.MediaItem
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vidstream.databinding.ActivityMainBinding
import com.example.vidstream.networkUtils.ItemsData
import java.text.SimpleDateFormat
import java.util.AbstractMap.SimpleEntry

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        var list: MutableList<ItemsData> = mutableListOf()
        list.add(ItemsData("abc", "abc", "abc", "abc", "abc", 3))
        list.add(ItemsData("abc", "abc", "abc", "abc", "abc", 3))
        list.add(ItemsData("abc", "abc", "abc", "abc", "abc", 3))
        list.add(ItemsData("abc", "abc", "abc", "abc", "abc", 3))
        list.add(ItemsData("abc", "abc", "abc", "abc", "abc", 3))
        list.add(ItemsData("abc", "abc", "abc", "abc", "abc", 3))
        adapter = ItemsAdapter(list, this)
        binding.rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rv.adapter = adapter
    }
}