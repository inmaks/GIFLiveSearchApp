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

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()
    Column(

    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = gif.images.original.url,
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .aspectRatio(gif.images.original.width.toFloat() / gif.images.original.height),
            contentScale = ContentScale.Fit
        )

        Text(
            text = if (TitleProcessing.removeGIFnBy(gif.title) == ""){"-"} else {TitleProcessing.removeGIFnBy(gif.title)},
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
        )
    }

}