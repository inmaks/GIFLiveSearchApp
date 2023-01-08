package com.chili.GIFLiveSearch.API

import com.chili.GIFLiveSearch.Data.GifArray
import io.ktor.client.call.*
import io.ktor.client.request.*

object GifRepo {
    //Gets all needed info from API
    suspend fun getGifs(querySearch: String, limit: Int = 25, offset: Int = 0): GifArray {

        val ktorClient: KtorClient = KtorClient()

        return ktorClient.client.use {
            it.get(APIConsts.BaseUrl){
                url {
                    parameters.append("api_key", APIConsts.APIKey)
                    parameters.append("q", querySearch)
                    parameters.append("limit", limit.toString())
                    parameters.append("offset", offset.toString())
                }
            }
        }.body()
    }
}