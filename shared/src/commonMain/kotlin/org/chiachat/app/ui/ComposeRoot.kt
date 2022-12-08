package org.chiachat.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.chiachat.app.ui.services.NavigationService
import org.chiachat.app.ui.services.ThemeService
import org.chiachat.app.ui.theme.AppTheme
import org.chiachat.app.ui.theme.ThemeResources
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

fun loadResourcesDefault(resources: MutableStateFlow<ThemeResources>) {
  CoroutineScope(Dispatchers.Default).launch { resources.value = ThemeResources() }
}

internal class ComposeRoot : KoinComponent {
  val navigationService: NavigationService by inject()
  val themeService: ThemeService by inject()

  @Composable
  fun View() {
    val darkMode by themeService.darkMode.collectAsState()
    AppTheme(darkMode) {
      Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
        navigationService.currentView()
      }
    }
  }
}
