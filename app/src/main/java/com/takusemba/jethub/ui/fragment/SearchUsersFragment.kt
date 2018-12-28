package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentSearchUsersBinding
import com.takusemba.jethub.extension.parentViewModelProvider
import com.takusemba.jethub.ui.item.SearchUsersSection
import com.takusemba.jethub.viewmodel.SearchUsersViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchUsersFragment : DaggerFragment() {

  companion object {

    fun newInstance() = SearchUsersFragment()
  }

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val searchUsersViewModel: SearchUsersViewModel by lazy {
    parentViewModelProvider(viewModelFactory) as SearchUsersViewModel
  }

  private val searchUsersSection: SearchUsersSection by lazy {
    SearchUsersSection(this, searchUsersViewModel)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_search_users, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentSearchUsersBinding>(view)!!

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<ViewHolder>().apply {
      add(searchUsersSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        return false
      }

      override fun onQueryTextChange(newText: String): Boolean {
        searchUsersViewModel.search(newText)
        return true
      }
    })
  }
}