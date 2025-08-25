package com.fottow.fottow.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fottow.fottow.R
import com.fottow.fottow.presentation.widgets.PrimaryButton

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String = "Error occurred",
    onReloadCLicked: () -> Unit
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .padding(6.dp),
            painter = painterResource(id = R.drawable.ic_baseline_error_24),
            contentDescription = "error logo"
        )

        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = message,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        SecondaryButton(
            text = "Reload",
            onClick = { onReloadCLicked() }
        )

    }
}

@Preview(widthDp = 340, showBackground = true , backgroundColor = 0xFFFFFF)
@Composable
private fun ErrorPreview() {
    ErrorView(onReloadCLicked = {})
}
