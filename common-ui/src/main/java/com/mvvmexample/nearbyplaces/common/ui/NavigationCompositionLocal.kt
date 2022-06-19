/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mvvmexample.nearbyplaces.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.mvvmexample.nearbyplaces.common.ui.graph.common.Screen


val LocalEventProviderContext = staticCompositionLocalOf<EventLocalProvider> {
    error("EventProviderContext not provided")
}

fun interface EventLocalProvider {
    fun emit(event: ComposeNestedEvent)
}

sealed class ComposeNestedEvent {

    object NavigateUp : ComposeNestedEvent()

    class NavigationToScreen(val screen: Screen, vararg navArgs: Any?) : ComposeNestedEvent() {
        val args = navArgs
    }
}

fun ComposeNestedEvent.NavigationToScreen.route(): String {
    val regex = """\{(.*?)\}""".toRegex()
    var route = screen.route
    regex.findAll(screen.route).forEachIndexed { index, value ->
        route = route.replace(value.value, args[index].toString())
    }
    return route
}

@Composable
fun OnComposeEvent(
    callback: (composeNestedEvent: ComposeNestedEvent) -> Unit,
    callSuper: Boolean? = false,
    content: @Composable () -> Unit
) {
    val localCompositionEventProviderContext =
        if (callSuper == true) LocalEventProviderContext.current else null

    CompositionLocalProvider(
        LocalEventProviderContext provides EventLocalProvider {
            callback(it)
            localCompositionEventProviderContext?.emit(it)
        },
        content = content
    )
}

fun EventLocalProvider.navigate(screen: Screen, vararg navArgs: Any?) {
    emit(
        ComposeNestedEvent.NavigationToScreen(screen, navArgs)
    )
}

fun EventLocalProvider.navigateUp() {
    emit(ComposeNestedEvent.NavigateUp)
}
