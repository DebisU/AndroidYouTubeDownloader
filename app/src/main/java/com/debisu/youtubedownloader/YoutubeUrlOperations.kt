package com.debisu.youtubedownloader

import android.widget.Toast

/**
 * Created by caamanod on 17/01/2018.
 */
class YoutubeUrlOperations {
    var youtubeUrlPrefix: String? = "https://www.youtube.com/watch?v="
    var youtubeUrlSufix: String? = "yvdXkHuV0DE"
    var youtubeUrlFull: String? = "https://www.youtube.com/watch?v=yvdXkHuV0DE"

    fun getYoutubeFullUrl() : String {
        return  youtubeUrlPrefix + youtubeUrlSufix;
    }

    fun getYoutubeSufixFromYoutubeFullUrl(){
        var indexWordToSplit = "watch?v="
        if (!youtubeUrlFull.isNullOrEmpty()){
            var splitIndexNullable: Int? = youtubeUrlFull?.indexOf(indexWordToSplit)
            if (splitIndexNullable != null){
                var indexWordLength: Int = indexWordToSplit.length + splitIndexNullable
                youtubeUrlSufix = youtubeUrlFull?.substring(indexWordLength)

            }
        }
    }

    fun setYoutubeSufixAndFullUrls(fullYoutubePath: String){
        youtubeUrlFull = fullYoutubePath
        getYoutubeSufixFromYoutubeFullUrl()
    }


}