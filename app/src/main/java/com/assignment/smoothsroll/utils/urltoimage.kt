package com.assignment.smoothsroll.utils

import android.graphics.BitmapFactory

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext
import java.net.URL

suspend fun urlToImageBitmap(url: String): ImageBitmap {
    val urlConnection = URL(url).openConnection()
    val op = withContext(Dispatchers.IO) {
        try {
            urlConnection.connect()
            val imageBytes = urlConnection.inputStream.readBytes()
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            bitmap.asImageBitmap()
        } finally {
//           urlConnection.getInputStream().close()
        }
    }
    return op
}
