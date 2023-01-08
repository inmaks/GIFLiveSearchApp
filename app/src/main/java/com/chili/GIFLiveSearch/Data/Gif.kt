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