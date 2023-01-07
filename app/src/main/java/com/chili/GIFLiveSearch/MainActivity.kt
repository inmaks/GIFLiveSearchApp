package com.chili.GIFLiveSearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.chili.GIFLiveSearch.Data.GifArray
import com.chili.GIFLiveSearch.ui.theme.GIFLiveSearchTheme
import com.chili.GIFLiveSearch.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GIFLiveSearchTheme {
                Scaffold(
                    topBar = { SearchView(model) },
                    modifier = Modifier.fillMaxSize(),
                    content= { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            MainScreen(model)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    val gifs: GifArray? by mainViewModel.gifs.observeAsState(null)

    GifGrid(gifs)
}

@Composable
fun SearchView(mainViewModel: MainViewModel) {

    val q: String by mainViewModel.q.observeAsState("")

    TextField(
        value = q,
        onValueChange = { value ->
            mainViewModel.onTextChange(value) },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (q != "") {
                IconButton(
                    onClick = {mainViewModel.onCrossClick()}
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}



@Composable
fun GifGrid(gifArr:GifArray?) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()
    gifArr?.let {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                        modifier = Modifier.size(150.dp)
                    )
                }
            }
        }
    } ?: Text(text = "Please start searching...")
}