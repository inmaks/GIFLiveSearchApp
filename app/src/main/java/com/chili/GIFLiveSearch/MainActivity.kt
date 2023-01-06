package com.chili.GIFLiveSearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.chili.GIFLiveSearch.API.APIGiphy
import com.chili.GIFLiveSearch.Data.GifArray
import com.chili.GIFLiveSearch.ui.theme.GIFLiveSearchTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GIFLiveSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Init()
                }
            }
        }
    }
}

@Composable
fun Init() {
    GifGrid()
}

@Composable
fun GifGrid() {
    val apiGiphy: APIGiphy = APIGiphy()

    var gifArr: GifArray = runBlocking {apiGiphy.getGifs("cheeseburger") }
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(gifArr.gifs.size) {i->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    imageLoader = imageLoader,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(gifArr.gifs[i].images.original.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GIFLiveSearchTheme {
        Init()
    }
}