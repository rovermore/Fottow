package com.fottow.fottow.presentation.gallery

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
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

    ScreenContainer(
        topBar = { FTopBar() }
    ) {
        LazyColumn {
            if (error.not()) {
                items(result) {
                    ImageCustom(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        imageUrl = it.url
                    )
                }
            } else {
                item {
                    ErrorView(modifier = Modifier.fillParentMaxWidth()) {}
                }
            }

            if (loading)
                item { Loader() }
        }
    }
}