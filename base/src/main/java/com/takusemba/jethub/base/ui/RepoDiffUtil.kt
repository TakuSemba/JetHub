package com.takusemba.jethub.base.ui

import androidx.recyclerview.widget.DiffUtil
import com.takusemba.jethub.model.Repo

object RepoDiffUtil : DiffUtil.ItemCallback<Repo>() {

  override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
    return oldItem == newItem
  }
}
