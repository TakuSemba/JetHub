package com.takusemba.jethub.util

import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

/**
 * Keyboard Utility
 */
object Keyboard {

  fun show(context: Context) {
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
  }

  fun hide(windowToken: IBinder, context: Context) {
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(windowToken, 0)
  }
}
