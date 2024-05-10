package com.example.vidstream

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.example.vidstream.databinding.ActivityVideoBinding
import com.example.vidstream.networkUtils.ItemsData

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private lateinit var player: ExoPlayer
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val item = bundle!!.getSerializable("data") as ItemsData

        player = ExoPlayer.Builder(this).build()
        binding.vid.player = player


        val mediaItem = MediaItem.fromUri(item.video)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

        player.addListener(object: Player.Listener {
            @UnstableApi
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    binding.vid.hideController()
                }
            }
        })

        binding.tvTitle.text = item.title
        binding.tvChannel.text = item.channel

        val spannable = SpannableString("Description : \n${item.description}")
        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvDescription.text = spannable
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}