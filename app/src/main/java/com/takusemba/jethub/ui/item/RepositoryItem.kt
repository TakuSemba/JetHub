package com.takusemba.jethub.ui.item

import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.ItemRepositoryBinding
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.databinding.BindableItem

data class RepositoryItem(
  val repository: Repository,
  val userViewModel: UserViewModel
) : BindableItem<ItemRepositoryBinding>(
  repository.hashCode().toLong()
) {

  override fun bind(binding: ItemRepositoryBinding, position: Int) {
    binding.title.text = repository.name
    binding.description.text = repository.description
    binding.description.visibility = if (repository.description.isBlank()) {
      View.GONE
    } else {
      View.VISIBLE
    }
    binding.languageName.text = repository.language.title
    binding.languageIcon.setImageResource(repository.language.icon)
    binding.starCount.text = repository.starsCount.toString()
    binding.forkCount.text = repository.forksCount.toString()

    binding.root.setOnLongClickListener { v ->
      if (userViewModel.isPinned(repository)) {
        userViewModel.unpin(repository)
        Toast.makeText(v.context, R.string.unpinned_repository, LENGTH_SHORT).show()
      } else {
        userViewModel.pin(repository)
        Toast.makeText(v.context, R.string.pinned_repository, LENGTH_SHORT).show()
      }
      false
    }
  }

  override fun getLayout() = R.layout.item_repository
}
