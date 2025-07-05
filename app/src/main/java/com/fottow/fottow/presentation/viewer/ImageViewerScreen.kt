package com.fottow.fottow.presentation.viewer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.fottow.fottow.R
import com.fottow.fottow.domain.photo.model.FottowImage
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun ImageViewerScreen(
    imageUrl: String,
    photos: List<FottowImage>,
    navController: NavController
) {

    val pagerState = rememberPagerState(
        initialPage = photos.indexOfFirst { it.url == imageUrl },
        initialPageOffsetFraction = 0f
    ) {
        photos.size
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            AsyncImage(
                modifier = Modifier
                    .zoomable(rememberZoomState())
                    .fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photos[page].url)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .error(R.drawable.ic_baseline_error_24)
                    .fallback(R.drawable.ic_baseline_error_24)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                onSuccess = {},
                onLoading = {},
                onError = {},
            )
        }
    }

}