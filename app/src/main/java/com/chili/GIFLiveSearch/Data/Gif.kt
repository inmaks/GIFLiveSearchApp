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
    val images: GifImages) {
}

@Serializable
data class GifImages(
    @SerialName("original")
    val original: GifImagesOriginal) {
}

@Serializable
data class GifImagesOriginal(
    @SerialName("url")
    val url: String) {
}