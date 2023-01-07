package com.chili.GIFLiveSearch.API

import com.chili.GIFLiveSearch.Data.GifArray
import io.ktor.client.call.*
import io.ktor.client.request.*

object GifRepo {
    suspend fun getGifs(querySearch: String): GifArray {
        val ktorClient: KtorClient = KtorClient()
        return ktorClient.client.use {
            it.get(APIConsts.BaseUrl){
                url {
                    parameters.append("api_key", APIConsts.APIKey)
                    parameters.append("q", querySearch)
                }
            }
        }.body()
    }

}