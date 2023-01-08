package com.chili.GIFLiveSearch.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import com.chili.GIFLiveSearch.Data.Gif
import com.chili.GIFLiveSearch.TitleProcessing

@Composable
fun GifCard(gif: Gif) {

    //imageLoader from Coil to load GIF in Image component
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()

    Column(
    ) {
        Image(
            //To load GIFs Asynchronously
            painter = rememberAsyncImagePainter(
                model = gif.images.original.url,
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                //AspectRatio is for GIFs to not crop and save it height to staggeredGrid work properly
                .aspectRatio(gif.images.original.width.toFloat() / gif.images.original.height),
            contentScale = ContentScale.Fit
        )

        Text(
            //If GIF has no title, we print -
            text = if (TitleProcessing.removeGIFnBy(gif.title) == ""){"-"} else {TitleProcessing.removeGIFnBy(gif.title)},
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
        )
    }

}