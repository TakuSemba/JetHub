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
import com.takusemba.jethub.databinding.FragmentSearchReposBinding
import com.takusemba.jethub.extension.activityViewModelProvider
import com.takusemba.jethub.extension.parentViewModelProvider
import com.takusemba.jethub.ui.item.SearchReposSection
import com.takusemba.jethub.viewmodel.SearchReposViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchReposFragment : DaggerFragment() {

  companion object {

    fun newInstance() = SearchReposFragment()
  }

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val searchReposViewModel: SearchReposViewModel by lazy {
    parentViewModelProvider(viewModelFactory) as SearchReposViewModel
  }

  private val userViewModel: UserViewModel by lazy {
    activityViewModelProvider(viewModelFactory) as UserViewModel
  }

  private val searchReposSection: SearchReposSection by lazy {
    SearchReposSection(this, searchReposViewModel, userViewModel)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_search_repos, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentSearchReposBinding>(view)!!

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<ViewHolder>().apply {
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