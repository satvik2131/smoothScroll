package com.assignment.smoothsroll.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.assignment.smoothsroll.utils.ImageCache
import com.assignment.smoothsroll.utils.urlToImageBitmap
import com.assignment.smoothsroll.viewmodel.DataVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun ContentGrid(paddingValues: PaddingValues) {
    val vm: DataVM = DataVM()
    val imageData = vm.getDataFlow().collectAsLazyPagingItems()

    LazyVerticalGrid(
        contentPadding = paddingValues,
        columns = GridCells.Adaptive(minSize = 100.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        items(imageData.itemCount) { index ->
            Surface(
                tonalElevation = 3.dp,
                modifier = Modifier.aspectRatio(1f)
            ) {
                ImagePresenter(
                    title = imageData[index]?.title,
                    domain = imageData[index]?.thumbnail?.domain.toString(),
                    key = imageData[index]?.thumbnail?.key,
                    basePath = imageData[index]?.thumbnail?.basePath
                )
            }
        }
    }
}

@Composable
fun ImagePresenter(title: String?, domain: String?, key: String?, basePath: String?) {
    var imgBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val testUrl = domain + "/" + basePath + "/0/" + key

    LaunchedEffect(key1 = true) {
        val cachedImage = ImageCache.get(testUrl)
        if (cachedImage != null) {
            imgBitmap = cachedImage.asImageBitmap()
        } else {
            // Load the image from the network and cache it
            withContext(Dispatchers.IO) {
                val loadedImage = urlToImageBitmap(testUrl)
                if (loadedImage != null) {
                    ImageCache.put(testUrl, loadedImage)
                    imgBitmap = loadedImage.asImageBitmap()
                }
            }
        }
//        imgBitmap = urlToImageBitmap(testUrl)
    }

    imgBitmap?.let { bitmap ->
        Image(
            modifier = Modifier
                .width(130.dp)
                .height(200.dp), bitmap = bitmap, contentDescription = "Loaded Image"
        );

    }
}