package com.takusemba.jethub.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.model.SimpleDeveloper
import com.takusemba.jethub.repository.SearchDevelopersRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage searched developers data.
 */
class SearchDevelopersViewModel @ViewModelInject constructor(
  private val searchDevelopersRepository: SearchDevelopersRepository
) : ViewModel() {

  private val searchedDevelopersResult = MutableLiveData<List<SimpleDeveloper>>()

  val searchedDevelopers: LiveData<List<SimpleDeveloper>> = searchedDevelopersResult

  init {
    viewModelScope.launch {
      runCatching {
        searchDevelopersRepository.searchDevelopers("")
      }.onSuccess { developers ->
        searchedDevelopersResult.value = developers
      }
    }
  }

  fun search(query: String) {
    viewModelScope.launch {
      runCatching {
        searchDevelopersRepository.searchDevelopers(query)
      }.onSuccess { developers ->
        searchedDevelopersResult.value = developers
      }
    }
  }
}