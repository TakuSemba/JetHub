package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentSearchReposBinding
import com.takusemba.jethub.ui.item.SearchReposSection
import com.takusemba.jethub.viewmodel.SearchReposViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchReposFragment : DaggerFragment(R.layout.fragment_search_repos) {

  companion object {

    fun newInstance() = SearchReposFragment()
  }

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val searchReposViewModel: SearchReposViewModel by viewModels(
    ownerProducer = { requireParentFragment() },
    factoryProducer = { viewModelFactory }
  )

  private val userViewModel: UserViewModel by activityViewModels { viewModelFactory }

  private val searchReposSection: SearchReposSection by lazy {
    SearchReposSection(this, searchReposViewModel, userViewModel)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentSearchReposBinding.bind(view)

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
      add(searchReposSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        return false
      }

      override fun onQueryTextChange(newText: String): Boolean {
        searchReposViewModel.search(newText)
        return true
      }
    })
  }
}