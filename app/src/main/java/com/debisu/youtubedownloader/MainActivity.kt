package com.debisu.youtubedownloader

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.Provider
import com.google.android.youtube.player.YouTubePlayerView


class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private var youTubeView: YouTubePlayerView? = null
    private var youtubeUrlOperations: YoutubeUrlOperations? = null
    private var myYoutubePlayer: MyYouTubePlayer? = null
    private var btDownloadVideo: Button? = null
    private var btDownloadAudio: Button? = null
    private var etYoutubeUrl: EditText? = null

    protected val youTubePlayerProvider: Provider?
        get() = youTubeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        youTubeView = findViewById<View>(R.id.youtube_view) as YouTubePlayerView?
        btDownloadVideo = findViewById(R.id.btDownloadVideo)
        btDownloadAudio = findViewById(R.id.btDownloadAudio)
        etYoutubeUrl = findViewById(R.id.etYoutubeUrl)
        youtubeUrlOperations = YoutubeUrlOperations()

        youTubeView!!.initialize(Config.YOUTUBE_API_KEY, this)
        loadFrontComponentEvents()
    }

    override fun onInitializationSuccess(provider: Provider, player: YouTubePlayer, wasRestored: Boolean) {
        //myYoutubePlayer?.changePlayerVideo(player,wasRestored)
        myYoutubePlayer = MyYouTubePlayer(provider,player,wasRestored)
        changePlayerVideo(player,wasRestored)
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
            youTubePlayerProvider!!.initialize(Config?.YOUTUBE_API_KEY, this)
        }
    }

    companion object {
        private val RECOVERY_REQUEST = 1
    }

    private fun loadFrontComponentEvents(){
        btDownloadVideo!!.setOnClickListener {
        /*
         *Toast.makeText(this@MainActivity,
         * "Downloading video in max quality",
         * Toast.LENGTH_SHORT).show()
         */
            youtubeUrlOperations?.getYoutubeSufixFromYoutubeFullUrl()
            Toast.makeText(this@MainActivity,
                    youtubeUrlOperations?.youtubeUrlSufix,
                    Toast.LENGTH_SHORT).show()

        }

        btDownloadAudio?.setOnClickListener {
            Toast.makeText(this@MainActivity,
                    "Downloading audio in max quality",
                    Toast.LENGTH_SHORT).show()
        }

        //After typing the youtube url it automatically changes to the video.
        etYoutubeUrl?.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    event.action == KeyEvent.ACTION_DOWN &&
                    event.keyCode == KeyEvent.KEYCODE_ENTER) {

                changeVideoAfterTippyng()

                true
            }
            false
        }

    }

    private fun changeVideoAfterTippyng(){
        youtubeUrlOperations?.setYoutubeSufixAndFullUrls(etYoutubeUrl?.text.toString())
        onInitializationSuccess(
                myYoutubePlayer!!.provider!!,
                myYoutubePlayer!!.player!!,
                myYoutubePlayer!!.wasRestored!!)
        changePlayerVideo(
                myYoutubePlayer!!.player!!,
                myYoutubePlayer!!.wasRestored!!)
    }

    private fun downloadVideo(){
        //var extractor = YouTubeExtractor
        //var extraction = extractor.extract("").blockingGet()
    }

    private fun changePlayerVideo(player: YouTubePlayer, wasRestored: Boolean){
        if (!wasRestored) {
            // Plays https://www.youtube.com/watch?v=yvdXkHuV0DE just need the yvdXkHuV0DE part
            player.cueVideo(youtubeUrlOperations?.youtubeUrlSufix)
        }
    }
}
