package com.debisu.youtubedownloader

import android.content.Intent;
import android.os.Bundle;
import android.view.View
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;


class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private var youTubeView: YouTubePlayerView? = null
    private var youtubeUrl: String? = "https://www.youtube.com/watch?v="

    protected val youTubePlayerProvider: Provider?
        get() = youTubeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        youTubeView = findViewById<View>(R.id.youtube_view) as YouTubePlayerView?
        youTubeView!!.initialize(Config.YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(provider: Provider, player: YouTubePlayer, wasRestored: Boolean) {
        if (!wasRestored) {
            player.cueVideo("yvdXkHuV0DE") // Plays https://www.youtube.com/watch?v=yvdXkHuV0DE
        }
    }

    override fun onInitializationFailure(provider: Provider, errorReason: YouTubeInitializationResult) {
        if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val error = String.format(getString(R.string.player_error), errorReason.toString())
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            youTubePlayerProvider!!.initialize(Config.YOUTUBE_API_KEY, this)
        }
    }

    companion object {
        private val RECOVERY_REQUEST = 1
    }
}
