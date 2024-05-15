package com.assignment.smoothsroll.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.assignment.smoothsroll.utils.urlToImageBitmap
import com.assignment.smoothsroll.viewmodel.DataVM
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ContentGrid(paddingValues: PaddingValues) {
    val vm: DataVM = DataVM()
    vm.fetchImages()
    val imageData by remember {
        vm.data
    }

    LazyVerticalGrid(
        contentPadding = paddingValues,
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(imageData) { curr ->
            Surface( tonalElevation = 3.dp,
                modifier = Modifier.aspectRatio(1f)) {
                ImagePresenter(
                    title = curr.title,
                    domain = curr.thumbnail.domain.toString(),
                    key = curr.thumbnail.key,
                    basePath = curr.thumbnail.basePath
                )
            }
        }
    }
}

@Composable
fun ImagePresenter(title: String, domain: String, key: String, basePath: String) {
    var imgBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val testUrl = domain + "/" + basePath + "/0/" + key
    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {
            imgBitmap = urlToImageBitmap(testUrl)
    }

    imgBitmap?.let { bitmap ->
        Image(modifier = Modifier
            .width(130.dp)
            .height(200.dp),bitmap = bitmap, contentDescription = "Loaded Image");

    }
}