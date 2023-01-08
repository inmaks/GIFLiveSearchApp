package com.chili.GIFLiveSearch

//I made this to remove unneeded parts of title
object TitleProcessing {

    fun removeGIFnBy(title: String) : String {
        return title.substringBefore(" GIF", title.substringBefore(" by "))
    }

    //In commit history You can see that I was using Material Card
    //I made this to fetch Author from title if there was no Author field in response from API
    fun getAuthorFrom(title: String) : String {
        return title.substringAfter(" by ", "unknown")
    }
}