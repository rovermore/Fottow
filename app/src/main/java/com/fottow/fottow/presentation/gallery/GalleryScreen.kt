package com.fottow.fottow.presentation.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.fottow.fottow.presentation.navigation.ImageViewerScreen
import com.fottow.fottow.presentation.navigation.navigateToImageViewer
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.theme.Spacing
import com.fottow.fottow.presentation.widgets.ErrorView
import com.fottow.fottow.presentation.widgets.FTopBar
import com.fottow.fottow.presentation.widgets.ImageCustom
import com.fottow.fottow.presentation.widgets.Loader
import com.fottow.fottow.presentation.widgets.ScreenContainer
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel = koinViewModel<GalleryViewModel>(),
    navController: NavController
) {

    val result by viewModel.success.collectAsStateWithLifecycle()
    val error by viewModel.onError.collectAsStateWithLifecycle()
    val loading by viewModel.isLoading.collectAsStateWithLifecycle()

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
                                navController.navigateToImageViewer(it.url)

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