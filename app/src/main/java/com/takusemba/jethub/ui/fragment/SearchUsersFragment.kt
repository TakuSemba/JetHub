package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentUsersBinding
import dagger.android.support.DaggerFragment

class SearchUsersFragment : DaggerFragment() {

  companion object {

    fun newInstance() = SearchUsersFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_search_users, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentUsersBinding>(view)!!

    binding.button.setOnClickListener {
      findNavController().navigate(R.id.userFragment)
    }
  }
}