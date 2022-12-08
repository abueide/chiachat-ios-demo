/*
 * Copyright 2020-2021 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.chiachat.app.SharedAppModules
import org.chiachat.app.ui.ComposeAppModules
import org.chiachat.app.ui.ComposeRoot
import org.chiachat.app.ui.theme.AppTheme
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.UIKit.UIViewController

internal val root = ComposeRoot()


internal val iosModule = module {
    factory(named("ioScope")) {
        CoroutineScope(Dispatchers.Default)
    }
    factory(named("vmScope")) {
        CoroutineScope(Dispatchers.Default)
    }
}
fun MainViewController() : UIViewController {
    startKoin {
      val composeModules = ComposeAppModules()
      val sharedModules = SharedAppModules()
      modules(iosModule + composeModules.all + sharedModules.all)
      allowOverride(false)
    }

    val controller:  UIViewController =  Application("ChiaChat") {
        AppTheme {
            Column {
                // To skip upper part of screen.
                root.View()
            }
        }
    }
    return controller
}

