package com.takusemba.jethub.repo

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.takusemba.jethub.repository.RepoRepository
import javax.inject.Inject

/**
 * Dagger-Hilt does not work with DFM.
 * This is an alternative way to archive injection.
 * https://github.com/google/dagger/issues/1865
 * https://developer.android.com/training/dependency-injection/hilt-multi-module#dfm
 */
class RepoViewModelProviderFactory @Inject constructor(
  private val fragment: Fragment,
  private val repoRepository: RepoRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    val args = RepoFragmentArgs.fromBundle(fragment.requireArguments())
    return RepoViewModel(args.ownerName, args.repoName, repoRepository) as T
  }
}