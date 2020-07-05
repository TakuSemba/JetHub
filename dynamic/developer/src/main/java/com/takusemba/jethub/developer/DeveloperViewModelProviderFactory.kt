package com.takusemba.jethub.developer

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.repository.DeveloperRepository
import javax.inject.Inject

/**
 * Dagger-Hilt does not work with DFM.
 * This is an alternative way to archive injection.
 * https://github.com/google/dagger/issues/1865
 * https://developer.android.com/training/dependency-injection/hilt-multi-module#dfm
 */
class DeveloperViewModelProviderFactory @Inject constructor(
  private val fragment: Fragment,
  private val developerRepository: DeveloperRepository,
  private val errorHandler: ErrorHandler
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    val args = DeveloperFragmentArgs.fromBundle(fragment.requireArguments())
    return DeveloperViewModel(args.name, developerRepository, errorHandler) as T
  }
}