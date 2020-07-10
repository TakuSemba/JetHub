package com.takusemba.jethub.base.ui

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.takusemba.jethub.base.R
import com.takusemba.jethub.base.databinding.ItemRepositoryBinding
import com.takusemba.jethub.base.model.ColoredLanguage
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repository

class RepositoryViewHolder(
  private val binding: ItemRepositoryBinding,
  private val userViewModel: UserViewModel,
  private val navigationViewModel: NavigationViewModel
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(repository: Repository) {
    binding.developerIcon.load(repository.owner.avatarUrl)
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

    binding.root.setOnClickListener {
      navigationViewModel.openRepo(repository.owner.login, repository.name)
    }

    binding.root.setOnLongClickListener { v ->
      if (userViewModel.isPinned(repository)) {
        userViewModel.unpin(repository)
        Toast.makeText(
          v.context,
          R.string.unpinned_repository, Toast.LENGTH_SHORT
        ).show()
      } else {
        userViewModel.pin(repository)
        Toast.makeText(
          v.context,
          R.string.pinned_repository, Toast.LENGTH_SHORT
        ).show()
      }
      true
    }
  }
}