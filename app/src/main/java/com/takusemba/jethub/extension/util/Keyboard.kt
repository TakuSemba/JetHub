package com.takusemba.jethub.extension.util

import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

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
