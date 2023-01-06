package com.chili.GIFLiveSearch.API

import com.chili.GIFLiveSearch.Data.GifArray
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class APIGiphy {
    suspend fun getGifs(querySearch: String): GifArray {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        val response: HttpResponse = client.get(APIConsts.BaseUrl){
            url {
                parameters.append("api_key", APIConsts.APIKey)
                parameters.append("q", querySearch)
            }
        }

        return response.body()
    }

}