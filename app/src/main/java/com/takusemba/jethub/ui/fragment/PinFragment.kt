package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentPinBinding
import com.takusemba.jethub.ui.item.PinSection
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PinFragment : Fragment(R.layout.fragment_pin) {

  companion object {

    fun newInstance() = PinFragment()
  }

  private val userViewModel: UserViewModel by activityViewModels()

  private val pinSection: PinSection by lazy {
    PinSection(this, userViewModel)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentPinBinding.bind(view)

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
      add(pinSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    userViewModel.pinedRepositories.observe(this) { repositories ->
      binding.emptyLayout.visibility = if (repositories.isEmpty()) View.VISIBLE else View.INVISIBLE
    }
  }
}
