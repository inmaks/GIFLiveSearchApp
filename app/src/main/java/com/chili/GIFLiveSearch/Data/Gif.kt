package com.chili.GIFLiveSearch.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//This classes are for JSON parser to work properly, couldn't find any better way to do this
//P.S. I would prefer developing this API using GraphQL

@Serializable
data class GifArray(
    @SerialName("data")
    val gifs: ArrayList<Gif>) {
}

@Serializable
data class Gif(
    @SerialName("images")
    val images: GifImages,
    @SerialName("title")
    val title: String,
    @SerialName("user")
    val gifAuthor: GifAuthor = GifAuthor(null)) {
}

@Serializable
data class GifImages(
    @SerialName("fixed_width_downsampled")
    val original: GifImagesOriginal) {
}

@Serializable
data class GifAuthor(
    @SerialName("display_name")
    val username: String? = null
) {
}

@Serializable
data class GifImagesOriginal(
    @SerialName("url")
    val url: String,
    @SerialName("height")
    val height: Int,
    @SerialName("width")
    val width: Int) {
}