package com.takusemba.jethub.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * For Actvities, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
  provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
  provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the Activity.
 */
inline fun <reified VM : ViewModel> Fragment.activityViewModelProvider(
  provider: ViewModelProvider.Factory
) = ViewModelProviders.of(requireActivity(), provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the parent
 * Fragment.
 */
inline fun <reified VM : ViewModel> Fragment.parentViewModelProvider(
  provider: ViewModelProvider.Factory
) = ViewModelProviders.of(parentFragment!!, provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the parent's parent
 * Fragment.
 */
inline fun <reified VM : ViewModel> Fragment.parentParentViewModelProvider(
  provider: ViewModelProvider.Factory
) = ViewModelProviders.of(parentFragment!!.parentFragment!!, provider).get(VM::class.java)