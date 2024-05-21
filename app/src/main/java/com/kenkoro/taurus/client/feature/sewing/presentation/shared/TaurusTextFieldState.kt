package com.kenkoro.taurus.client.feature.sewing.presentation.shared

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

open class TaurusTextFieldState(
  private val validator: (String) -> Boolean = { true },
  private val errorFor: (String) -> String = { "" },
) {
  var text by mutableStateOf("")
  var isFocusedOnce by mutableStateOf(false)
  var isFocused by mutableStateOf(false)
  private var displayErrors by mutableStateOf(false)

  open val isValid: Boolean
    get() = validator(text)

  fun onFocusChange(focused: Boolean) {
    isFocused = focused
    if (focused) isFocusedOnce = true
  }

  fun enableShowErrors() {
    if (isFocusedOnce) displayErrors = true
  }

  fun showErrors() = !isValid && displayErrors

  open fun getError(): String? {
    return if (showErrors()) {
      errorFor(text)
    } else {
      null
    }
  }
}

fun textFieldStateSaver(state: TaurusTextFieldState) =
  listSaver<TaurusTextFieldState, Any>(
    save = { listOf(it.text, it.isFocusedOnce) },
    restore = { saved ->
      state.apply {
        text = saved[0] as String
        isFocusedOnce = saved[1] as Boolean
      }
    },
  )