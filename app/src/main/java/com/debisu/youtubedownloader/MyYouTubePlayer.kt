package com.debisu.youtubedownloader

import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.Provider

/**
 * Created by caamanod on 17/01/2018.
 */

class MyYouTubePlayer {
    var provider:  Provider? = null
    var player:  YouTubePlayer? = null
    var wasRestored:  Boolean? = null
    var youtubeUrlOperations: YoutubeUrlOperations? = null


    constructor(provider: Provider,player: YouTubePlayer,wasRestored: Boolean){
        this.provider = provider
        this.player = player
        this.wasRestored = wasRestored
    }

    /*
     *TODO: think about how to implment in a class or fragment the youtube player
     *TODO: extending YouTubePlayer.OnInitializedListener or fragment to make it works.
    */
//    override fun onInitializationSuccess(p0: Provider?, p1: YouTubePlayer?, p2: Boolean) {
//        if (p1 != null){
//            changePlayerVideo(p1,p2)
//        }
//    }

//    fun changePlayerVideo(player: YouTubePlayer, wasRestored: Boolean){
//        if (!wasRestored) {
//            player.cueVideo(youtubeUrlOperations?.youtubeUrlSufix) // Plays https://www.youtube.com/watch?v=yvdXkHuV0DE
//        }
//    }
}
