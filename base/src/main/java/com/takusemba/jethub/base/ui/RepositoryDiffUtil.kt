package com.takusemba.jethub.base.ui

import androidx.recyclerview.widget.DiffUtil
import com.takusemba.jethub.model.Repository

object RepositoryDiffUtil : DiffUtil.ItemCallback<Repository>() {

  override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
    return oldItem == newItem
  }
}
