package com.example.exoplayerr

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource.Factory
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.android.synthetic.main.fragment_player.*



class PlayerFragment : Fragment() {
    private var exoPlayer: SimpleExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.stop()
        exoPlayer?.release()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_raw.setOnClickListener {
            exoPlayer?.stop()

            val rawResourceDataSource = RawResourceDataSource(requireContext()).apply {
                this.open(DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.shtil)))
            }

            val mediaItem = MediaItem.fromUri(rawResourceDataSource.uri!!)
            val mediaSource = ProgressiveMediaSource.Factory { rawResourceDataSource }
                .createMediaSource(mediaItem)

            exoPlayer?.setMediaSource(mediaSource)
            exoPlayer?.playWhenReady = true
            exoPlayer?.prepare()
        }

        button_assets.setOnClickListener {
            exoPlayer?.stop()

            val assetDataSource = AssetDataSource(requireContext())
            val dataSourceFactory = Factory { assetDataSource }

            val progressiveMediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("assets:///world.mp3"))

            exoPlayer?.setMediaSource(progressiveMediaSource)
            exoPlayer?.playWhenReady = true
            exoPlayer?.prepare()
        }

        button_stop.setOnClickListener {
            exoPlayer?.stop()
        }
    }
}
