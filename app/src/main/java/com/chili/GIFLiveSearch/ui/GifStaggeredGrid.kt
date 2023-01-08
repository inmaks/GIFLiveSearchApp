package com.chili.GIFLiveSearch.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chili.GIFLiveSearch.Data.GifArray

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GifStaggeredGrid(gifArr: GifArray?, state: LazyStaggeredGridState) {

    //Null check
    gifArr?.let {
        if(it.gifs.isNotEmpty()) {
            LazyVerticalStaggeredGrid(
                //Adaptive for bigger screens and Landscape mode
                columns = StaggeredGridCells.Adaptive(150.dp),
                //Dividers
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                //We pass state for isScrolledToTheEnd check to work
                state = state
            ) {
                items(it.gifs.size) {i->
                    GifCard(it.gifs[i])
                }
            }
        } else {
            //If API didn't return anything we show simple 404
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "404",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.wrapContentSize()
                )
                Text(
                    text = "Nothing was found...",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.wrapContentSize()
                )
                Text(
                    text = "Try searching something else.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
    } ?:
    //Just to show something before user starts searching
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = "",
            modifier = Modifier
                .padding(8.dp)
                .size(192.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "Please start searching...",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.wrapContentSize()
        )
    }
}

//Creating extension for isScrolledToTheEnd
@OptIn(ExperimentalFoundationApi::class)
fun LazyStaggeredGridState.isScrolledToTheEnd(): Boolean {
    //If is needed to skip check if list is empty
    return if(layoutInfo.visibleItemsInfo.isNotEmpty()) {
        // 11 to start loading new gifs before("seamlessly") it is scrolled to end
        layoutInfo.visibleItemsInfo.last().index >= layoutInfo.totalItemsCount - 11
    } else {
        false
    }
}