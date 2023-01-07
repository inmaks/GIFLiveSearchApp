package com.chili.GIFLiveSearch

object TitleProcessing {

    fun removeGIFnBy(title: String) : String {
        return title.substringBefore(" GIF", title.substringBefore(" by "))
    }

    fun getAuthorFrom(title: String) : String {
        return title.substringAfter(" by ", "unknown")
    }
}