package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentMainTabBinding
import com.takusemba.jethub.extension.add
import com.takusemba.jethub.extension.replace
import dagger.android.support.DaggerFragment

class MainTabFragment : DaggerFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_main_tab, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentMainTabBinding>(view)!!

    childFragmentManager.add(R.id.container, FeedFragment.newInstance())

    binding.bottomNavigation.setOnNavigationItemSelectedListener(
      BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
          R.id.feed -> {
            childFragmentManager.replace(
              container = R.id.container,
              fragment = FeedFragment.newInstance()
            )
            return@OnNavigationItemSelectedListener true
          }
          R.id.repositories -> {
            childFragmentManager.replace(
              container = R.id.container,
              fragment = RepositoriesFragment.newInstance()
            )
            return@OnNavigationItemSelectedListener true
          }
          R.id.users -> {
            childFragmentManager.replace(
              container = R.id.container,
              fragment = UsersFragment.newInstance()
            )
            return@OnNavigationItemSelectedListener true
          }
        }
        false
      })
  }
}