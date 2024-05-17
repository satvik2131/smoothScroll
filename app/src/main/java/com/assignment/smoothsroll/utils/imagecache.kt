package com.assignment.smoothsroll.utils

import android.graphics.Bitmap
import android.util.LruCache

object ImageCache {
    private val cache: LruCache<String, Bitmap> = object : LruCache<String, Bitmap>(calculateCacheSize()) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount / 1024
        }
    }

    private fun calculateCacheSize(): Int {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        return maxMemory / 8 // Use 1/8th of the available memory for the cache
    }

    fun get(key: String): Bitmap? {
        return cache.get(key)
    }

    fun put(key: String, bitmap: Bitmap) {
        cache.put(key, bitmap)
    }
}
