package com.example.vidstream

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.vidstream.databinding.ActivityVideoBinding
import com.example.vidstream.networkUtils.ItemsData

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val item = bundle!!.getSerializable("data") as ItemsData

        val player = ExoPlayer.Builder(this).build()
        binding.vid.player = player

        val mediaItem = MediaItem.fromUri(item.video)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

        binding.tvTitle.text = item.title
        binding.tvChannel.text = item.channel
        binding.tvDescription.text = item.description
    }
}