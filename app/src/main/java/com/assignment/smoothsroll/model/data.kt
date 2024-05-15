package com.assignment.smoothsroll.model

import java.net.URI


data class Thumbnail(
    val id:String,
    val version: Int,
    val domain: URI,
    val basePath: String,
    val key: String,
    val qualities: List<Int>,
    val aspectRatio: Float
)

data class BackupDetails(
    val pdfLink:URI,
    val screenshotURL:URI
)
data class Data(
    val id: String,
    val title:String,
    val language:String,
    val thumbnail: Thumbnail,
    val mediaType: Int,
    val coverageURL: URI,
    val publishedAt:String,
    val publishedBy:String,
    val backupDetails:BackupDetails
)