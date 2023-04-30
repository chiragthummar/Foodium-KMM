/**
 * Copyright 2023 Shreyas Patil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import base.ViewModel
import base.viewModelFactory
import kotlinx.coroutines.CoroutineScope

@Composable
inline fun <VM : ViewModel<STATE>, STATE> NavStackEntry<*>.navStackViewModel(
    key: Any? = null,
    noinline provider: @DisallowComposableCalls (CoroutineScope) -> VM,
): VM {
    val extra = key?.toString() ?: "default"
    return rememberInNavStack(
        key = "viewModel-$extra",
        compute = { viewModelFactory(provider) },
        onDispose = { vm -> vm.onCleared() },
    )
}
