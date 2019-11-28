package id.haadii.learn.exoplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.activity_detail.*
import com.google.android.exoplayer2.util.Util
import id.haadii.learn.exoplayer.data.model.Hits
import kotlin.properties.Delegates

class DetailActivity : AppCompatActivity(), PlaybackPreparer {

    private var playerView: PlayerView? = null
    private var mediaSource: MediaSource? = null
    private var player : SimpleExoPlayer? = null
    private lateinit var componentListener: ComponentListener
    private lateinit var hits: Hits

    private var url: String? = null
    private var playWhenReady : Boolean = true
    private var currentWindow : Int? = null
    private var playbackPosition : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        hits = intent?.getParcelableExtra("hits") as Hits
        url = hits.videos.medium.url
        Glide.with(this)
            .load(hits.userImageURL)
            .placeholder(R.drawable.progress_animation)
            .into(civ_profile)
        tv_profile.text = hits.user
        componentListener = ComponentListener()

        playerView = pv_video_player
        
        title = "Detail Video"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer(url.toString())
        }
    }

    override fun onResume() {
        super.onResume()
//        hideSystemUi()
        if (Util.SDK_INT > 23) {
            initializePlayer(url.toString())
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        Log.e("onstop", "True")
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    override fun preparePlayback() {
        player?.retry()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initializePlayer(url: String) {

        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(this,
                DefaultRenderersFactory(this),
                DefaultTrackSelector(),
                DefaultLoadControl())

            player?.addListener(componentListener)
            playerView?.player = player
            playerView?.setPlaybackPreparer(this)

            player?.playWhenReady = playWhenReady
            if (currentWindow != null && playbackPosition != null) {
                player?.seekTo(currentWindow!!, playbackPosition!!)
            }

            val uri = Uri.parse(url)
            mediaSource = buildMediaSource(uri)
            player?.prepare(mediaSource, true, false)
        }

    }

    private fun releasePlayer() {
        if (player != null) {
            playbackPosition = player?.currentPosition
            currentWindow = player?.currentWindowIndex
            playWhenReady = player?.playWhenReady!!
            player?.removeListener(componentListener)
            player?.stop()
            player?.release()

            player = null
            playerView = null
            mediaSource = null
        }
    }

    private fun buildMediaSource(uri: Uri) : MediaSource {
        val manifestDataSource = DefaultHttpDataSourceFactory("ua")
        return ProgressiveMediaSource.Factory(
            manifestDataSource
        ).createMediaSource(uri)
    }

    private fun hideSystemUi() {
        pv_video_player.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}
