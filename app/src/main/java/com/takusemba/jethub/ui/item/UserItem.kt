package com.takusemba.jethub.ui.item

import com.squareup.picasso.Picasso
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.ItemUserBinding
import com.takusemba.jethub.model.User
import com.xwray.groupie.databinding.BindableItem

data class UserItem(
  val user: User
) : BindableItem<ItemUserBinding>(
  user.hashCode().toLong()
) {

  override fun bind(binding: ItemUserBinding, position: Int) {
    val context = binding.root.context
    Picasso.get().load(user.avatarUrl).into(binding.icon)
    binding.name.text = user.name
    binding.description.text = user.bio
    binding.followers.text = context.getString(R.string.followers_count, user.followersCount)
    binding.followers.text = context.getString(R.string.followings_count, user.followingCount)
  }

  override fun getLayout() = R.layout.item_user
}
