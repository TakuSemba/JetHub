package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentDeveloperDetailBinding
import com.takusemba.jethub.extension.viewModelProvider
import com.takusemba.jethub.ui.item.DeveloperDetailSection
import com.takusemba.jethub.viewmodel.DeveloperDetailViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DeveloperDetailFragment : DaggerFragment() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val developerDetailViewModel: DeveloperDetailViewModel by lazy {
    viewModelProvider(viewModelFactory) as DeveloperDetailViewModel
  }

  private val developerDetailSection: DeveloperDetailSection by lazy {
    DeveloperDetailSection(this, developerDetailViewModel)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_developer_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentDeveloperDetailBinding>(view)!!

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<ViewHolder>().apply {
      add(developerDetailSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    developerDetailViewModel.developer.observe(this, Observer { developer ->
      Picasso.get().load(developer.avatarUrl).into(binding.icon)
      binding.name.text = developer.name
      binding.description.text = developer.bio
      binding.repositoriesCount.text =
          requireContext().getString(R.string.followers_count, developer.publicRepositoriesCount)
      binding.gistsCount.text =
          requireContext().getString(R.string.gists_count, developer.publicGistsCount)
      binding.followersCount.text =
          requireContext().getString(R.string.followers_count, developer.followersCount)
      binding.followingsCount.text =
          requireContext().getString(R.string.followings_count, developer.followingCount)
    })

    binding.backArrow.setOnClickListener {
      view.findNavController().popBackStack()
    }
  }
}