package com.takusemba.jethub.base.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

// https://github.com/chrisbanes/tivi/blob/8d15aab2a14a37354cf2231f816c481cf6326f73/common-ui-compose/src/main/java/app/tivi/common/compose/FlowWithLifecycle.kt
@Composable
fun <T> rememberFlowWithLifecycle(
  flow: Flow<T>,
  lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): Flow<T> = remember(flow, lifecycle) {
  flow.flowWithLifecycle(
    lifecycle = lifecycle,
    minActiveState = minActiveState
  )
}

@Composable
fun <T> StateFlow<T>.collectAsLifecycleAwareState(
  lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): State<T> {
  return rememberFlowWithLifecycle(
    flow = this,
    lifecycle = lifecycle,
    minActiveState = minActiveState
  ).collectAsState(initial = value)
}
