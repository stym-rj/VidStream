package com.example.vidstream

import android.media.browse.MediaBrowser.MediaItem
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import com.example.vidstream.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.AbstractMap.SimpleEntry

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val player = ExoPlayer.Builder(this).build()
//        binding.vid.player = player
//
//        val mediaItem = androidx.media3.common.MediaItem.fromUri("android.resource://" + getPackageName() + "/" + R.raw.retrofit)
//
//        player.setMediaItem(mediaItem)
//        player.prepare()
//        player.play()
    }
}