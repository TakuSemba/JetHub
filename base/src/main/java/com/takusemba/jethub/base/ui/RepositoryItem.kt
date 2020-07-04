package com.takusemba.jethub.base.ui

import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.squareup.picasso.Picasso
import com.takusemba.jethub.base.R
import com.takusemba.jethub.base.databinding.ItemRepositoryBinding
import com.takusemba.jethub.base.model.ColoredLanguage
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repository
import com.xwray.groupie.viewbinding.BindableItem

data class RepositoryItem(
  val repository: Repository,
  val userViewModel: UserViewModel
) : BindableItem<ItemRepositoryBinding>(
  repository.hashCode().toLong()
) {

  override fun initializeViewBinding(view: View): ItemRepositoryBinding {
    return ItemRepositoryBinding.bind(view)
  }

  override fun bind(binding: ItemRepositoryBinding, position: Int) {
    Picasso.get().load(repository.owner.avatarUrl).into(binding.developerIcon)
    binding.developerName.text = repository.owner.login
    binding.title.text = repository.name
    binding.description.text = repository.description
    binding.description.visibility = if (repository.description.isBlank()) {
      View.GONE
    } else {
      View.VISIBLE
    }
    val language = ColoredLanguage.of(repository.language)
    binding.languageName.text = language.title
    binding.languageIcon.setImageResource(language.icon)
    binding.starCount.text = repository.starsCount.toString()

    binding.root.setOnLongClickListener { v ->
      if (userViewModel.isPinned(repository)) {
        userViewModel.unpin(repository)
        Toast.makeText(v.context,
          R.string.unpinned_repository, LENGTH_SHORT).show()
      } else {
        userViewModel.pin(repository)
        Toast.makeText(v.context,
          R.string.pinned_repository, LENGTH_SHORT).show()
      }
      false
    }
  }

  override fun getLayout() = R.layout.item_repository
}
