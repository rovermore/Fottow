package com.fottow.fottow.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fottow.fottow.presentation.theme.Primary
import com.fottow.fottow.presentation.theme.White

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = Primary,
    contentColor: Color = White,
    cornerRadius: Int = 12
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.6f),
            disabledContentColor = contentColor.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(cornerRadius.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    borderColor: Color = Primary,
    contentColor: Color = Primary,
    backgroundColor: Color = White,
    cornerRadius: Int = 12
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(
                width = 1.dp,
                color = if (enabled) borderColor else borderColor.copy(alpha = 0.6f),
                shape = RoundedCornerShape(cornerRadius.dp)
            ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = contentColor.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(cornerRadius.dp),
        border = null
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ButtonsPreview() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        androidx.compose.foundation.layout.Column(
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            PrimaryButton(
                text = "Primary Button",
                onClick = { }
            )

            SecondaryButton(
                text = "Secondary Button",
                onClick = { }
            )
        }
    }
}