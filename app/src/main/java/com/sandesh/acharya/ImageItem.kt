package com.sandesh.acharya

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

@Composable
fun ImageItem() {
    val photos = remember { mutableStateOf<List<MediaCoverageItem>>(emptyList()) }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            withContext(Dispatchers.IO) {
                val response = apiService.getPhotos()
                photos.value = response
            }
        } catch (e: IOException) {
            errorMessage.value = "Network error occurred"
        }
    }

    Box(Modifier.fillMaxSize()) { // Center the Snackbar
        if (errorMessage.value != null) {
            Snackbar {
                Text(errorMessage.value!!)
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(top = 60.dp)
            ) {
                items(photos.value.chunked(3)) { rowOfPhotos ->
                    Row {
                        rowOfPhotos.forEach { photo ->
                            val logoImage =
                                "${photo.thumbnail!!.domain}/${photo.thumbnail!!.basePath}/0/${photo.thumbnail!!.key}"

                            LoadImage(
                                desc = "",
                                modifier = Modifier
                                    .height(120.dp)
                                    .width(120.dp)
                                    .weight(1f),
                                imageUri = logoImage,
                            )

                        }
                    }
                }
            }
        }
    }
}


