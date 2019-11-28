package id.haadii.learn.exoplayer

import android.util.Log
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.AudioRendererEventListener
import com.google.android.exoplayer2.video.VideoListener
import com.google.android.exoplayer2.video.VideoRendererEventListener

class ComponentListener : Player.EventListener{

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        val stateString: String = when (playbackState) {
            Player.STATE_IDLE -> "ExoPlayer.STATE_IDLE            -"
            Player.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING  -"
            Player.STATE_READY -> "ExoPlayer.STATE_READY          -"
            Player.STATE_ENDED -> "ExoPlayer.STATE_ENDED          -"
            else -> "UNKNOWN_STATE"
        }
        Log.d("ComponentListener", "changed state to $stateString playWhenReady: $playWhenReady")
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        super.onPlayerError(error)
        Log.e("errorexo", "$error")
    }
}