package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentMainTabBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainTabFragment : DaggerFragment() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_main_tab, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentMainTabBinding.bind(view)

    binding.bottomNavigation.setOnNavigationItemSelectedListener(
      BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
          R.id.feed -> {
            childFragmentManager.commit {
              replace(R.id.container, FeedFragment.newInstance())
            }
            return@OnNavigationItemSelectedListener true
          }
          R.id.repositories -> {
            childFragmentManager.commit {
              replace(R.id.container, SearchReposFragment.newInstance())
            }
            return@OnNavigationItemSelectedListener true
          }
          R.id.developers -> {
            childFragmentManager.commit {
              replace(R.id.container, SearchDevelopersFragment.newInstance())
            }
            return@OnNavigationItemSelectedListener true
          }
          R.id.pins -> {
            childFragmentManager.commit {
              replace(R.id.container, PinFragment.newInstance())
            }
            return@OnNavigationItemSelectedListener true
          }
        }
        false
      })

    if (childFragmentManager.findFragmentById(R.id.container) == null) {
      childFragmentManager.transaction {
        replace(R.id.container, FeedFragment.newInstance())
      }
    }
  }
}