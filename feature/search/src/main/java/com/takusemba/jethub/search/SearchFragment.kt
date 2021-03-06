package com.takusemba.jethub.search

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.view.updatePadding
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

  companion object {

    fun newInstance() = SearchFragment()
  }

  @Inject lateinit var recycledViewPool: RecyclerView.RecycledViewPool

  private val searchViewModel: SearchViewModel by viewModels(
    ownerProducer = { requireParentFragment() }
  )

  private val navigationViewModel: NavigationViewModel by activityViewModels()
  private val systemViewModel: SystemViewModel by activityViewModels()
  private val userViewModel: UserViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentSearchBinding.bind(view)

    val linearLayoutManager = LinearLayoutManager(context)
    val searchAdapter = SearchAdapter(userViewModel, navigationViewModel)
    val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
    dividerItemDecoration.setDrawable(
      requireNotNull(requireContext().getDrawable(R.drawable.shape_divider))
    )
    binding.recyclerView.addItemDecoration(dividerItemDecoration)
    binding.recyclerView.setRecycledViewPool(recycledViewPool)
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = searchAdapter

    binding.searchView.doOnLayout {
      val verticalMargin = binding.searchView.marginTop + binding.searchView.marginBottom
      val padding = binding.searchView.height + verticalMargin
      binding.recyclerView.updatePadding(top = padding)
      binding.recyclerView.scrollToPosition(0)
    }

    binding.searchView.setEndIconOnClickListener {
      binding.searchViewInput.setText("")
      binding.searchViewInput.clearFocus()
    }

    binding.searchViewInput.doAfterTextChanged { text ->
      searchViewModel.search(text.toString())
    }

    binding.themeSwitch.setOnClickListener {
      systemViewModel.setNightMode(!systemViewModel.isNightMode())
    }

    // show progress only while the first fetch.
    binding.progress.show()
    searchViewModel.searchedRepos.observe(owner = viewLifecycleOwner) {
      binding.progress.hide()
    }

    searchViewModel.searchedRepos.observe(owner = viewLifecycleOwner) { repositories ->
      binding.emptyLayout.visibility = if (repositories.isEmpty()) View.VISIBLE else View.GONE
      val inputWord = binding.searchViewInput.text.toString()
      val description = getString(R.string.empty_search_repositories_description, inputWord)
      binding.emptyDescription.text = description
    }

    searchViewModel.searchedRepos.observe(owner = viewLifecycleOwner) { repositories ->
      searchAdapter.submitList(repositories)
    }
  }
}
