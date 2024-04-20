package com.sandesh.acharya

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(
    desc: String,
    imageUri: String,
    modifier: Modifier = Modifier,
) {
    GlideImage(
        model = desc,
        contentDescription = desc,
        modifier = modifier,
        contentScale = ContentScale.Fit,
    ) {
        it.error(R.drawable.ic_placeholder).placeholder(R.drawable.ic_placeholder)
            .load(imageUri)
    }
}



