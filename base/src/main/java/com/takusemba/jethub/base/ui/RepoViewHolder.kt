package com.takusemba.jethub.base.ui

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.takusemba.jethub.base.R
import com.takusemba.jethub.base.databinding.ItemRepoBinding
import com.takusemba.jethub.base.model.ColoredLanguage
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repo

class RepoViewHolder(
  private val binding: ItemRepoBinding,
  private val userViewModel: UserViewModel,
  private val navigationViewModel: NavigationViewModel
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(repo: Repo) {
    binding.developerIcon.load(repo.owner.avatarUrl)
    binding.developerName.text = repo.owner.login
    binding.title.text = repo.name
    binding.description.text = repo.description
    binding.description.visibility = if (repo.description.isBlank()) {
      View.GONE
    } else {
      View.VISIBLE
    }
    val language = ColoredLanguage.of(repo.language)
    binding.languageName.text = language.title
    binding.languageIcon.setImageResource(language.icon)
    binding.starCount.text = repo.starsCount.toString()

    binding.root.setOnClickListener {
      navigationViewModel.openRepo(repo.owner.login, repo.name)
    }

    binding.root.setOnLongClickListener { v ->
      if (userViewModel.isPinned(repo)) {
        userViewModel.unpin(repo)
        Toast.makeText(
          v.context,
          R.string.unpinned_repository, Toast.LENGTH_SHORT
        ).show()
      } else {
        userViewModel.pin(repo)
        Toast.makeText(
          v.context,
          R.string.pinned_repository, Toast.LENGTH_SHORT
        ).show()
      }
      true
    }
  }
}