package com.takusemba.jethub.ui.item

import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.ItemDeveloperDetailHeaderBinding
import com.takusemba.jethub.model.Developer
import com.xwray.groupie.databinding.BindableItem

data class DeveloperDetailHeaderItem(
  val developer: Developer
) : BindableItem<ItemDeveloperDetailHeaderBinding>(
  developer.hashCode().toLong()
) {

  override fun bind(binding: ItemDeveloperDetailHeaderBinding, position: Int) {
    val context = binding.root.context
    Picasso.get().load(developer.avatarUrl).into(binding.icon)
    binding.name.text = developer.name
    binding.description.text = developer.bio
    val reposCount = context.getString(R.string.followers_count, developer.publicRepositoriesCount)
    binding.repositoriesCount.text = reposCount
    val gistsCount = context.getString(R.string.gists_count, developer.publicGistsCount)
    binding.gistsCount.text = gistsCount
    val followersCount = context.getString(R.string.followers_count, developer.followersCount)
    binding.followersCount.text = followersCount
    val followingsCount = context.getString(R.string.followings_count, developer.followingCount)
    binding.followingsCount.text = followingsCount
    binding.backArrow.setOnClickListener { view ->
      view.findNavController().popBackStack()
    }
  }

  override fun getLayout() = R.layout.item_developer_detail_header
}
