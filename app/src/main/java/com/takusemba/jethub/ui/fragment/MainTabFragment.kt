package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentMainTabBinding
import com.takusemba.jethub.feed.FeedFragment
import com.takusemba.jethub.pin.PinFragment
import com.takusemba.jethub.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainTabFragment : Fragment(R.layout.fragment_main_tab) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentMainTabBinding.bind(view)

    binding.bottomNavigation.setOnItemSelectedListener { item ->
      when (item.itemId) {
        R.id.feed -> {
          childFragmentManager.commit {
            replace(R.id.container, FeedFragment.newInstance())
          }
          true
        }
        R.id.search -> {
          childFragmentManager.commit {
            replace(R.id.container, SearchFragment.newInstance())
          }
          true
        }
        R.id.pin -> {
          childFragmentManager.commit {
            replace(R.id.container, PinFragment.newInstance())
          }
          true
        }
        else -> {
          false
        }
      }
    }

    if (childFragmentManager.findFragmentById(R.id.container) == null) {
      childFragmentManager.commit {
        replace(R.id.container, FeedFragment.newInstance())
      }
    }
  }
}
