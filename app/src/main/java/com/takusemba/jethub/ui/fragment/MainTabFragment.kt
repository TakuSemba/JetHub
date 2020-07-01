package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentMainTabBinding
import com.takusemba.jethub.feed.FeedFragment
import com.takusemba.jethub.pin.PinFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainTabFragment : Fragment(R.layout.fragment_main_tab) {

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
      childFragmentManager.commit {
        replace(R.id.container, FeedFragment.newInstance())
      }
    }
  }
}