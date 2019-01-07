package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentPinBinding
import com.takusemba.jethub.extension.activityViewModelProvider
import com.takusemba.jethub.ui.item.PinSection
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PinFragment : DaggerFragment() {

  companion object {

    fun newInstance() = PinFragment()
  }

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val userViewModel: UserViewModel by lazy {
    activityViewModelProvider(viewModelFactory) as UserViewModel
  }

  private val pinSection: PinSection by lazy {
    PinSection(this, userViewModel)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_pin, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentPinBinding>(view)!!

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<ViewHolder>().apply {
      add(pinSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    userViewModel.pinedRepositories.observe(this) { repositories ->
      binding.emptyLayout.visibility = if (repositories.isEmpty()) View.VISIBLE else View.INVISIBLE
    }
  }
}
