package com.takusemba.jethub.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.add(@IdRes container: Int, fragment: Fragment) {
  this.beginTransaction().add(container, fragment).commit()
}

fun FragmentManager.remove(fragment: Fragment) {
  this.beginTransaction().remove(fragment).commit()
}

fun FragmentManager.replace(
  @IdRes container: Int,
  fragment: Fragment,
  addToBackStack: Boolean = true
) {
  val transition = this.beginTransaction().replace(container, fragment)
  if (addToBackStack) transition.addToBackStack(null)
  transition.commit()
}