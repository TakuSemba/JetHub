package com.takusemba.jethub.pin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.pin.databinding.FragmentPinBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PinFragment : Fragment(R.layout.fragment_pin) {

  companion object {

    fun newInstance() = PinFragment()
  }

  @Inject lateinit var recycledViewPool: RecyclerView.RecycledViewPool

  private val navigationViewModel: NavigationViewModel by activityViewModels()
  private val systemViewModel: SystemViewModel by activityViewModels()
  private val userViewModel: UserViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentPinBinding.bind(view)

    val linearLayoutManager = LinearLayoutManager(context)
    val pinAdapter = PinAdapter(userViewModel, navigationViewModel)
    val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
    dividerItemDecoration.setDrawable(
      requireNotNull(requireContext().getDrawable(R.drawable.shape_divider))
    )
    with(binding.recyclerView) {
      addItemDecoration(dividerItemDecoration)
      setRecycledViewPool(recycledViewPool)
      layoutManager = linearLayoutManager
      adapter = pinAdapter
    }

    userViewModel.pinedRepositories.observe(viewLifecycleOwner) { repositories ->
      binding.emptyLayout.visibility = if (repositories.isEmpty()) View.VISIBLE else View.GONE
    }

    binding.themeSwitch.setOnClickListener {
      systemViewModel.setNightMode(!systemViewModel.isNightMode())
    }

    userViewModel.pinedRepositories.observe(viewLifecycleOwner) { repositories ->
      pinAdapter.submitList(repositories)
    }
  }
}
