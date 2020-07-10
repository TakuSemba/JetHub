package com.takusemba.jethub.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.takusemba.jethub.base.databinding.ItemRepositoryBinding
import com.takusemba.jethub.base.ui.RepositoryDiffUtil
import com.takusemba.jethub.base.ui.RepositoryViewHolder
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repository

class FeedChannelAdapter(
  private val userViewModel: UserViewModel,
  private val navigationViewModel: NavigationViewModel
) : ListAdapter<Repository, RepositoryViewHolder>(RepositoryDiffUtil) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
    val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return RepositoryViewHolder(binding, userViewModel, navigationViewModel)
  }

  override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}