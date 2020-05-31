package com.takusemba.jethub.ui.item

import android.view.View
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.ItemDeveloperBinding
import com.takusemba.jethub.model.SimpleDeveloper
import com.takusemba.jethub.ui.fragment.MainTabFragmentDirections
import com.takusemba.jethub.util.Keyboard
import com.xwray.groupie.viewbinding.BindableItem

data class UserItem(
  val developer: SimpleDeveloper
) : BindableItem<ItemDeveloperBinding>(
  developer.hashCode().toLong()
) {

  override fun initializeViewBinding(view: View): ItemDeveloperBinding {
    return ItemDeveloperBinding.bind(view)
  }

  override fun bind(binding: ItemDeveloperBinding, position: Int) {
    Picasso.get().load(developer.avatarUrl).into(binding.icon)
    binding.name.text = developer.login
    binding.root.setOnClickListener { view ->
      val direction = MainTabFragmentDirections.actionMainTabToDeveloperDetail(developer.login)
      view.findNavController().navigate(direction)
      Keyboard.hide(binding.root.windowToken, binding.root.context)
    }
  }

  override fun getLayout() = R.layout.item_developer
}
