package com.takusemba.jethub.repo

import androidx.fragment.app.Fragment
import com.takusemba.jethub.di.RepoModuleDependencies
import dagger.BindsInstance
import dagger.Component

/**
 * Hilt does not support injection for Dynamic Feature Module.
 * Therefore, this is a alternative way to archive injection for now.
 * https://developer.android.com/training/dependency-injection/hilt-multi-module#dfm
 */
@Component(dependencies = [RepoModuleDependencies::class])
interface RepoComponent {

  fun inject(fragment: RepoFragment)

  @Component.Builder
  interface Builder {

    fun fragment(@BindsInstance fragment: Fragment): Builder

    fun appDependencies(dependencies: RepoModuleDependencies): Builder

    fun build(): RepoComponent
  }
}