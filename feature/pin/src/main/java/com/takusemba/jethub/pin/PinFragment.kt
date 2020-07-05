package com.takusemba.jethub.pin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.pin.databinding.FragmentPinBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PinFragment : Fragment(R.layout.fragment_pin) {

  companion object {

    fun newInstance() = PinFragment()
  }

  private val navigationViewModel: NavigationViewModel by activityViewModels()
  private val systemViewModel: SystemViewModel by activityViewModels()
  private val userViewModel: UserViewModel by activityViewModels()

  private val pinSection: PinSection by lazy {
    PinSection(this, userViewModel, navigationViewModel)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentPinBinding.bind(view)

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
      add(pinSection)
    }
    val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
    dividerItemDecoration.setDrawable(
      requireNotNull(requireContext().getDrawable(R.drawable.shape_divider)))
    binding.recyclerView.addItemDecoration(dividerItemDecoration)
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    userViewModel.pinedRepositories.observe(viewLifecycleOwner) { repositories ->
      binding.emptyLayout.visibility = if (repositories.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    binding.account.setOnClickListener {
      navigationViewModel.openAccount()
    }

    binding.themeSwitch.setOnClickListener {
      systemViewModel.setNightMode(!systemViewModel.isNightMode())
    }
  }
}
