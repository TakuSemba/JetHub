package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentMainTabBinding
import com.takusemba.jethub.extension.activityViewModelProvider
import com.takusemba.jethub.viewmodel.UserViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainTabFragment : DaggerFragment() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val userViewModel: UserViewModel by lazy {
    activityViewModelProvider(viewModelFactory) as UserViewModel
  }

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

    Log.d("MainTabFragment", "factory is ${System.identityHashCode(viewModelFactory)}")
    userViewModel.pin(2)

    childFragmentManager.transaction {
      add(R.id.container, FeedFragment.newInstance())
    }

    binding.bottomNavigation.setOnNavigationItemSelectedListener(
      BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
          R.id.feed -> {
            childFragmentManager.transaction {
              replace(R.id.container, FeedFragment.newInstance())
            }
            return@OnNavigationItemSelectedListener true
          }
          R.id.repositories -> {
            childFragmentManager.transaction {
              replace(R.id.container, SearchRepositoriesFragment.newInstance())
            }
            return@OnNavigationItemSelectedListener true
          }
          R.id.users -> {
            childFragmentManager.transaction {
              replace(R.id.container, SearchUsersFragment.newInstance())
            }
            return@OnNavigationItemSelectedListener true
          }
        }
        false
      })
  }
}