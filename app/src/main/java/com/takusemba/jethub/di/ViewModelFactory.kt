package com.takusemba.jethub.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * This Factory creates ViewModel.
 * The scope of the ViewModel is determined by the lifecycle added to `ViewModelProviders.of(lifecycle, factory)`
 */
class ViewModelFactory @Inject constructor(
  private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    val found = creators.entries.find { modelClass.isAssignableFrom(it.key) }
    val creator = found?.value
      ?: throw IllegalArgumentException("unknown model class $modelClass")
    try {
      @Suppress("UNCHECKED_CAST")
      return creator.get() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}
