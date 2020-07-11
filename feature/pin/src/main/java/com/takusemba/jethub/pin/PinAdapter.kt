package com.takusemba.jethub.pin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.takusemba.jethub.base.databinding.ItemRepoBinding
import com.takusemba.jethub.base.ui.RepoDiffUtil
import com.takusemba.jethub.base.ui.RepoViewHolder
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repo

class PinAdapter(
  private val userViewModel: UserViewModel,
  private val navigationViewModel: NavigationViewModel
) : ListAdapter<Repo, RepoViewHolder>(RepoDiffUtil) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
    val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return RepoViewHolder(binding, userViewModel, navigationViewModel)
  }

  override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}
