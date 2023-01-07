package com.chili.GIFLiveSearch.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import com.chili.GIFLiveSearch.Data.Gif
import com.chili.GIFLiveSearch.TitleProcessing

@Composable
fun GifCard(gif: Gif, modifier: Modifier = Modifier) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = gif.images.original.url,
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxWidth()
                .aspectRatio(3f / 2f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = TitleProcessing.removeGIFnBy(gif.title),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    append("by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(gif.gifAuthor.username ?: TitleProcessing.getAuthorFrom(gif.title))
                    }
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}