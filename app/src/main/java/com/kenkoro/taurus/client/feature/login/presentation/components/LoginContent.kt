package com.kenkoro.taurus.client.feature.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.kenkoro.taurus.client.core.connectivity.NetworkStatus
import com.kenkoro.taurus.client.core.local.LocalContentWidth
import com.kenkoro.taurus.client.feature.login.presentation.util.PasswordState
import com.kenkoro.taurus.client.feature.login.presentation.util.SubjectState
import com.kenkoro.taurus.client.feature.shared.data.remote.dto.TokenDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginContent(
  modifier: Modifier = Modifier,
  networkStatus: NetworkStatus,
  subject: SubjectState,
  password: PasswordState,
  onLogin: suspend (subject: String, password: String) -> Result<TokenDto>,
  onEncryptAll: (String, String, String) -> Unit,
  onNavigateToOrderScreen: () -> Unit,
  onExit: () -> Unit = {},
  onInternetConnectionErrorShowSnackbar: suspend () -> SnackbarResult,
  onLoginErrorShowSnackbar: suspend () -> SnackbarResult,
  onShowErrorTitle: () -> Boolean = { false },
) {
  val contentWidth = LocalContentWidth.current
  val focusManager = LocalFocusManager.current

  val scope = rememberCoroutineScope()
  val interactionSource = remember { MutableInteractionSource() }
  var isAuthenticating by rememberSaveable {
    mutableStateOf(false)
  }

  Column(
    modifier =
      modifier
        .fillMaxSize()
        .clickable(interactionSource = interactionSource, indication = null) {
          focusManager.clearFocus()
        },
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    if (networkStatus != NetworkStatus.Available) {
      LaunchedEffect(networkStatus) { onInternetConnectionErrorShowSnackbar() }
    }

    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      LoginTextFields(
        modifier = Modifier.width(contentWidth.standard),
        networkStatus = networkStatus,
        subject = subject,
        password = password,
        isAuthenticating = isAuthenticating,
        onLoginSubmitted = { subject, password ->
          scope.launch(Dispatchers.IO) {
            isAuthenticating = true
            val result = onLogin(subject, password)
            isAuthenticating = false

            result.onSuccess {
              onEncryptAll(subject, password, it.token)
              withContext(Dispatchers.Main) { onNavigateToOrderScreen() }
            }

            result.onFailure { onLoginErrorShowSnackbar() }
          }
        },
        onExit = onExit,
        onShowErrorTitle = onShowErrorTitle,
      )
    }
  }
}