package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentSearchDevelopersBinding
import com.takusemba.jethub.ui.item.SearchDevelopersSection
import com.takusemba.jethub.viewmodel.SearchDevelopersViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDevelopersFragment : Fragment(R.layout.fragment_search_developers) {

  companion object {

    fun newInstance() = SearchDevelopersFragment()
  }

  private val searchDevelopersViewModel: SearchDevelopersViewModel by viewModels(
    ownerProducer = { requireParentFragment() }
  )

  private val searchDevelopersSection: SearchDevelopersSection by lazy {
    SearchDevelopersSection(this, searchDevelopersViewModel)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentSearchDevelopersBinding.bind(view)

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
      add(searchDevelopersSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        return false
      }

      override fun onQueryTextChange(newText: String): Boolean {
        searchDevelopersViewModel.search(newText)
        return true
      }
    })
  }
}