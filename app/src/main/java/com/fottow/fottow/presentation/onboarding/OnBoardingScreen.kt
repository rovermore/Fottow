package com.fottow.fottow.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fottow.fottow.R
import com.fottow.fottow.presentation.navigation.LoginScreen
import com.fottow.fottow.presentation.widgets.PrimaryButton
import com.fottow.fottow.presentation.widgets.ScreenContainer

@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val onboardingPages = listOf(
        OnboardingPage(
            image = R.drawable.onboarding_image_1,
            title = "¡Bienvenido a Fottow!",
            description = "Descubre una manera nueva de compartir fotos.",
        ),
        OnboardingPage(
            image = R.drawable.onboarding_image_2,
            title = "Súbela a Fottow",
            description = "Comparte tus fotos a través de Fottow.\nSube tus fotos con familiares y amigos a la app.",
        ),
        OnboardingPage(
            image = R.drawable.onboarding_image_3,
            title = "Se comparte sola",
            description = "Tus fotos se comparten automáticamente solo con las personas que aparecen en la foto. No tienes que hacer nada, fottow lo hace automáticamente por ti.",
        )
    )

    var currentPage by remember { mutableIntStateOf(0) }

    ScreenContainer {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = onboardingPages[currentPage].title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Image(
                    painter = painterResource(id = onboardingPages[currentPage].image),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 48.dp, vertical = 44.dp)
                )
                Text(
                    text = onboardingPages[currentPage].description,
                    modifier = Modifier.padding(top = 24.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            DotsIndicator(
                totalDots = onboardingPages.size,
                selectedIndex = currentPage,
                selectedColor = MaterialTheme.colorScheme.primary,
                unSelectedColor = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
            ) {
                if (currentPage > 0) {
                    PrimaryButton(
                        text = "Anterior",
                        onClick = { currentPage-- },
                        modifier = Modifier.weight(1f)
                    )
                }
                if (currentPage < onboardingPages.lastIndex) {
                    PrimaryButton(
                        text = "Siguiente",
                        onClick = { currentPage++ },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    PrimaryButton(
                        text = "Empezar",
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(LoginScreen)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

private data class OnboardingPage(
    val image: Int,
    val title: String,
    val description: String
)

@Composable
private fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 24.dp)
    ) {
        repeat(totalDots) { index ->
            val color = if (index == selectedIndex) selectedColor else unSelectedColor
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (index == selectedIndex) 12.dp else 8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}