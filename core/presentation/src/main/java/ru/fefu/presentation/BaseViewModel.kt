package ru.fefu.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

open class BaseViewModel : ViewModel() {

    protected fun <T1, T2, T3, T4, R> combineState(
        flow: StateFlow<T1>,
        flow2: StateFlow<T2>,
        flow3: StateFlow<T3>,
        flow4: StateFlow<T4>,
        scope: CoroutineScope,
        sharingStarted: SharingStarted = SharingStarted.Eagerly,
        transform: (T1, T2, T3, T4) -> R
    ): StateFlow<R> =
        combine(flow, flow2, flow3, flow4, transform)
            .stateIn(
                scope,
                sharingStarted,
                transform.invoke(flow.value, flow2.value, flow3.value, flow4.value)
            )
}