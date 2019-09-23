package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentDeveloperDetailBinding
import com.takusemba.jethub.extension.activityViewModelProvider
import com.takusemba.jethub.extension.viewModelProvider
import com.takusemba.jethub.ui.item.DeveloperDetailSection
import com.takusemba.jethub.viewmodel.DeveloperDetailViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DeveloperDetailFragment : DaggerFragment() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val developerDetailViewModel: DeveloperDetailViewModel by lazy {
    viewModelProvider(viewModelFactory) as DeveloperDetailViewModel
  }

  private val userViewModel: UserViewModel by lazy {
    activityViewModelProvider(viewModelFactory) as UserViewModel
  }

  private val developerDetailSection: DeveloperDetailSection by lazy {
    DeveloperDetailSection(this, developerDetailViewModel, userViewModel)
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

    developerDetailViewModel.developer.observe(this) { developer ->
      Picasso.get().load(developer.avatarUrl).into(binding.icon)
      binding.name.text = developer.login
      binding.description.text = developer.bio
      binding.toolbarTitle.text = developer.login
      binding.repositoriesCount.text =
        requireContext().getString(R.string.followers_count, developer.publicRepositoriesCount)
      binding.gistsCount.text =
        requireContext().getString(R.string.gists_count, developer.publicGistsCount)
      binding.followersCount.text =
        requireContext().getString(R.string.followers_count, developer.followersCount)
      binding.followingsCount.text =
        requireContext().getString(R.string.followings_count, developer.followingCount)
    }

    binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, offset ->
      if (binding.collapsingToolbar.height + offset < 2 * ViewCompat.getMinimumHeight(
          binding.collapsingToolbar)) {
        binding.toolbarTitle.animate().alpha(1f).setDuration(100).start()
      } else {
        binding.toolbarTitle.animate().alpha(0f).setDuration(100).start()
      }
    })

    binding.backArrow.setOnClickListener {
      view.findNavController().popBackStack()
    }
  }
}