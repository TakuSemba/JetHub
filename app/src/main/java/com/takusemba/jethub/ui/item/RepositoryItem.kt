package com.takusemba.jethub.ui.item

import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.ItemRepositoryBinding
import com.takusemba.jethub.model.Repository
import com.xwray.groupie.databinding.BindableItem

data class RepositoryItem(
  val repository: Repository
) : BindableItem<ItemRepositoryBinding>(
  repository.hashCode().toLong()
) {

  override fun bind(binding: ItemRepositoryBinding, position: Int) {
    binding.title.text = repository.name
    binding.description.text = repository.description
    binding.languageName.text = repository.language.title
    binding.languageIcon.setImageResource(repository.language.icon)
    binding.starCount.text = repository.starsCount.toString()
    binding.forkCount.text = repository.forksCount.toString()
  }

  override fun getLayout() = R.layout.item_repository
}
