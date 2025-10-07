package com.ivy.onboarding.steps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.design.l0_system.UI
import com.ivy.design.l0_system.style
import com.ivy.legacy.IvyWalletPreview
import com.ivy.legacy.utils.addKeyboardListener
import com.ivy.legacy.utils.densityScope
import com.ivy.legacy.utils.isNotNullOrBlank
import com.ivy.legacy.utils.keyboardOnlyWindowInsets
import com.ivy.legacy.utils.onScreenStart
import com.ivy.legacy.utils.springBounceSlow
import com.ivy.ui.R
import com.ivy.ui.component.AnimatedGradientBackground
import com.ivy.ui.component.PulsingGradientOrbs
import com.ivy.wallet.ui.theme.Gradient
import androidx.compose.ui.graphics.Color
import com.ivy.wallet.ui.theme.components.IvyOutlinedTextField
import com.ivy.wallet.ui.theme.components.OnboardingButton

@Composable
fun OnboardingNameInput(
    onNameSet: (String) -> Unit,
    onSkip: () -> Unit
) {
    val rootView = LocalView.current
    var keyboardShown by remember { mutableStateOf(false) }

    onScreenStart {
        rootView.addKeyboardListener {
            keyboardShown = it
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Animated orange gradient background
        AnimatedGradientBackground()
        PulsingGradientOrbs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Adaptive spacing based on keyboard state
            Spacer(Modifier.height(if (keyboardShown) 20.dp else 60.dp))

            // HI.png image - smaller when keyboard is shown
            Image(
                modifier = Modifier.size(if (keyboardShown) 80.dp else 120.dp),
                painter = painterResource(id = R.drawable.hi),
                contentDescription = "Welcome greeting",
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(if (keyboardShown) 16.dp else 32.dp))

            Text(
                text = stringResource(R.string.what_is_your_name),
                style = UI.typo.h2.style(
                    color = UI.colors.pureInverse,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "This helps us personalize your experience",
                style = UI.typo.b2.style(
                    color = UI.colors.pureInverse.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )
            )

            // Adaptive spacing - less when keyboard is shown
            Spacer(Modifier.height(if (keyboardShown) 24.dp else 40.dp))

            var nameTextField by remember { mutableStateOf(TextFieldValue("")) }
            val nameFocus = FocusRequester()

            onScreenStart {
                nameFocus.requestFocus()
            }

            IvyOutlinedTextField(
                Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .focusRequester(nameFocus),
                value = nameTextField,
                hint = "Enter your name",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (nameTextField.text.trim().isNotNullOrBlank()) {
                            onNameSet(nameTextField.text.trim())
                        }
                    }
                )
            ) {
                nameTextField = it.copy(text = it.text.trim())
            }

            Spacer(Modifier.height(24.dp))

            // Continue button with orange gradient
            OnboardingButton(
                Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                text = "Continue",
                textColor = UI.colors.pureInverse,
                backgroundGradient = Gradient(
                    startColor = Color(0xFFFF9500), // Orange
                    endColor = Color(0xFFFF6B35)   // Deep Orange
                ),
                hasNext = true,
                enabled = nameTextField.text.trim().isNotNullOrBlank()
            ) {
                onNameSet(nameTextField.text.trim())
            }

            Spacer(Modifier.height(16.dp))

            // Skip button with orange accent
            Text(
                modifier = Modifier
                    .padding(bottom = if (keyboardShown) 16.dp else 32.dp)
                    .clickable { onSkip() },
                text = "Skip for now",
                style = UI.typo.c.style(
                    color = Color(0xFFFF9500).copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium
                )
            )
            
            // Add bottom spacing when keyboard is shown to ensure content is visible
            if (keyboardShown) {
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    IvyWalletPreview {
        OnboardingNameInput(
            onNameSet = {},
            onSkip = {}
        )
    }
}
