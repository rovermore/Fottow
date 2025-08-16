package com.fottow.fottow.presentation.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fottow.fottow.presentation.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FTopBar(
    hasNotifications: Boolean = false,
    @DrawableRes icon: Int? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onIconClicked: () -> Unit = { },
    ) {
    TopAppBar(
        colors = AppTheme.TopAppColors.colors,
        title = {
            Text(
                text = "Fottow",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .semantics { testTagsAsResourceId = true }
                    .testTag("cartButton"),
                onClick = { onIconClicked() }
            ){
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center,) {

                    icon?.let {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = "shopping cart",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }


                    if (hasNotifications)
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.Red,
                                    shape = CircleShape
                                )
                                .padding(4.dp)
                                .align(Alignment.TopEnd),
                        )
                }

            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AppBarPreview() {
    FTopBar(
        onIconClicked =  {},
        hasNotifications = true
    )
}