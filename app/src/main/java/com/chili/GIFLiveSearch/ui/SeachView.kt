package com.chili.GIFLiveSearch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chili.GIFLiveSearch.viewmodels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchView(mainViewModel: MainViewModel) {

    val q: String by mainViewModel.q.observeAsState("")

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(){
        TextField(
            placeholder = {Text("Search for GIFS...")},
            value = q,
            onValueChange = { value ->
                mainViewModel.onTextChange(value) },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(8.dp)
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
                                .padding(8.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape,
            //color roles are taken from Material3 guidelines
            colors = TextFieldDefaults.textFieldColors(
                containerColor  = MaterialTheme.colorScheme.surface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        //Material guidelines says there has to be "outline"
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.outline),
        )
    }
}