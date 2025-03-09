package com.fottow.fottow.presentation.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.fottow.fottow.R

@Composable
fun ImageCustom(
    modifier: Modifier = Modifier,
    imageUrl: String = ""
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .diskCachePolicy(CachePolicy.DISABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .error(R.drawable.ic_baseline_error_24)
            .fallback(R.drawable.ic_baseline_error_24)
            .build(),
        contentDescription = null,
        onSuccess = {},
        onLoading = {},
        onError = {},
        modifier = modifier
    )
}