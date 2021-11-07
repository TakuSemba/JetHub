package com.takusemba.jethub.developer

import androidx.fragment.app.Fragment
import com.takusemba.jethub.di.DeveloperAppModuleDependencies
import dagger.BindsInstance
import dagger.Component

/**
 * Hilt does not support injection for Dynamic Feature Module.
 * Therefore, this is a alternative way to archive injection for now.
 * https://developer.android.com/training/dependency-injection/hilt-multi-module#dfm
 */
@Component(dependencies = [DeveloperAppModuleDependencies::class])
interface DeveloperComponent {

  fun inject(fragment: DeveloperFragment)

  @Component.Builder
  interface Builder {

    fun fragment(@BindsInstance fragment: Fragment): Builder

    fun appDependencies(dependencies: DeveloperAppModuleDependencies): Builder

    fun build(): DeveloperComponent
  }
}
