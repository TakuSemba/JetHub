package com.takusemba.jethub.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.core.UserViewModel
import com.takusemba.jethub.search.databinding.FragmentSearchBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

  companion object {

    fun newInstance() = SearchFragment()
  }

  private val searchViewModel: SearchViewModel by viewModels(
    ownerProducer = { requireParentFragment() }
  )

  private val userViewModel: UserViewModel by activityViewModels()

  private val searchSection: SearchSection by lazy {
    SearchSection(this, searchViewModel, userViewModel)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentSearchBinding.bind(view)

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
      add(searchSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        return false
      }

      override fun onQueryTextChange(newText: String): Boolean {
        searchViewModel.search(newText)
        return true
      }
    })
  }
}