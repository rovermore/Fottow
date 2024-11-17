package com.fottow.fottow.presentation.widgets

import android.content.Loader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.fottow.fottow.presentation.Action
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.background,
    onRefresh: Action? = null,
    topBar: @Composable Action = {},
    bottomBar: @Composable Action = {},
    isLoading: Boolean = false,
    content: @Composable (PaddingValues) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(color = background) {
            Scaffold(
                modifier = modifier,
                topBar = topBar,
                bottomBar = bottomBar,
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        content = {
                            onRefresh?.let {
                                SwipeRefresh(
                                    state = rememberSwipeRefreshState(isRefreshing = false),
                                    onRefresh = { onRefresh() },
                                    indicator = { state, trigger ->
                                        SwipeRefreshIndicator(
                                            state = state,
                                            refreshTriggerDistance = trigger,
                                            scale = true,
                                            arrowEnabled = false,
                                            contentColor = Color.Blue,
                                            largeIndication = true
                                        )
                                    },
                                    content = { content(paddingValues) }
                                )
                            } ?: kotlin.run {
                                content(paddingValues)
                            }
                        }
                    )
                }
            )
        }
        if (isLoading) {
            Loader()
        }
    }
}