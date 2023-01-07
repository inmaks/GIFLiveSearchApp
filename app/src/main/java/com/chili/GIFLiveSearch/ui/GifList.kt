package com.chili.GIFLiveSearch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.chili.GIFLiveSearch.Data.GifArray

@Composable
fun GifList(gifArr: GifArray?) {
    gifArr?.let {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(gifArr.gifs.size) {i->
                GifCard(gifArr.gifs[i])
            }
        }
    } ?: Text(text = "Please start searching...")
}