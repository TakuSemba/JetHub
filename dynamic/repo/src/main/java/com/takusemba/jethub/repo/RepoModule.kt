package com.takusemba.jethub.repo

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.image.coil.CoilImagesPlugin

@Module
@DisableInstallInCheck
class RepoModule {

  @Provides
  fun provideMarkwon(fragment: Fragment): Markwon {
    return Markwon.builder(fragment.requireContext())
      .usePlugins(
        listOf(
          CoilImagesPlugin.create(fragment.requireContext()),
          TablePlugin.create(fragment.requireContext()),
        )
      )
      .build()
  }
}
