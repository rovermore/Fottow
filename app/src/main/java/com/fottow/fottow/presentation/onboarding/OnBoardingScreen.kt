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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fottow.fottow.R
import com.fottow.fottow.presentation.navigation.LoginScreen
import com.fottow.fottow.presentation.widgets.PrimaryButton
import com.fottow.fottow.presentation.widgets.ScreenContainer
import com.fottow.fottow.presentation.widgets.SecondaryButton

@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val onboardingPages = listOf(
        OnboardingPage(
            image = R.drawable.onboarding_image_1,
            title = stringResource(R.string.onboarding_welcome_title),
            description = stringResource(R.string.onboarding_welcome_description),
        ),
        OnboardingPage(
            image = R.drawable.onboarding_image_2,
            title = stringResource(R.string.onboarding_upload_title),
            description = stringResource(R.string.onboarding_upload_description),
        ),
        OnboardingPage(
            image = R.drawable.onboarding_image_3,
            title = stringResource(R.string.onboarding_auto_title),
            description = stringResource(R.string.onboarding_auto_description),
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
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = onboardingPages[currentPage].title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Image(
                    painter = painterResource(id = onboardingPages[currentPage].image),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 48.dp, vertical = 24.dp)
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
                    SecondaryButton(
                        text = stringResource(R.string.onboarding_previous),
                        onClick = { currentPage-- },
                        modifier = Modifier.weight(1f)
                    )
                }
                if (currentPage < onboardingPages.lastIndex) {
                    PrimaryButton(
                        text = stringResource(R.string.onboarding_next),
                        onClick = { currentPage++ },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    PrimaryButton(
                        text = stringResource(R.string.onboarding_start),
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