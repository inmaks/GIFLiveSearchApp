package com.chili.GIFLiveSearch.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifArray(
    @SerialName("data")
    val gifs: List<Gif>) {
}

@Serializable
data class Gif(
    @SerialName("images")
    val images: GifImages,
    @SerialName("title")
    val title: String,
    @SerialName("user")
    val gifAuthor: GifAuthor = GifAuthor("unknown")) {
}

@Serializable
data class GifImages(
    @SerialName("fixed_width_downsampled")
    val original: GifImagesOriginal) {
}

@Serializable
data class GifAuthor(
    @SerialName("username")
    val username: String) {
}

@Serializable
data class GifImagesOriginal(
    @SerialName("url")
    val url: String) {
}