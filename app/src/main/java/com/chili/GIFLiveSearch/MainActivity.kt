@file:OptIn(ExperimentalMaterial3Api::class)

package com.chili.GIFLiveSearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chili.GIFLiveSearch.Data.GifArray
import com.chili.GIFLiveSearch.ui.GifStaggeredGrid
import com.chili.GIFLiveSearch.ui.SearchView
import com.chili.GIFLiveSearch.ui.isScrolledToTheEnd
import com.chili.GIFLiveSearch.ui.theme.GIFLiveSearchTheme
import com.chili.GIFLiveSearch.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GIFLiveSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { SearchView(model) },
                        modifier = Modifier.fillMaxSize(),
                        content = { padding ->
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    top = padding.calculateTopPadding() + 16.dp,
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 16.dp
                                )) {
                                MainScreen(model)
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    val gifs: GifArray? by mainViewModel.gifs.observeAsState(null)

    val state = rememberLazyStaggeredGridState(
        initialFirstVisibleItemIndex = 0
    )

    if(state.isScrolledToTheEnd()) {
        mainViewModel.onScrolledToTheEnd()
    }

    GifStaggeredGrid(gifs, state)
}





