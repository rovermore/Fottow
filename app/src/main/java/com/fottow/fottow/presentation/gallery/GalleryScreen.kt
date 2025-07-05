package com.fottow.fottow.presentation.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.presentation.navigation.navigateToImageViewer
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.widgets.ErrorView
import com.fottow.fottow.presentation.widgets.FTopBar
import com.fottow.fottow.presentation.widgets.ImageCustom
import com.fottow.fottow.presentation.widgets.Loader
import com.fottow.fottow.presentation.widgets.ScreenContainer
import org.koin.androidx.compose.koinViewModel

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel = koinViewModel<GalleryViewModel>(),
    navController: NavController
) {


    val result by viewModel.success.collectAsStateWithLifecycle()
    val error by viewModel.onError.collectAsStateWithLifecycle()
    val loading by viewModel.isLoading.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    viewModel.getImages()
                }
                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    ScreenContainer(
        topBar = { FTopBar() }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxSize().padding(AppTheme.Spacing.S)
        ) {
            if (error.not()) {
                items(items = result, key = {it.url}) {
                    ImageCustom(
                        modifier = Modifier
                            .padding(AppTheme.Spacing.XXS)
                            .size(height = 100.dp, width = 100.dp)
                            .clip(RoundedCornerShape(AppTheme.Spacing.XS))
                            .clickable {
                                navController.navigateToImageViewer(it.url, result)
                            },
                        imageUrl = it.url
                    )
                }
            } else {
                item {
                    ErrorView(modifier = Modifier.fillMaxWidth()) {}
                }
            }

            if (loading)
                item { Loader() }
        }
    }
}