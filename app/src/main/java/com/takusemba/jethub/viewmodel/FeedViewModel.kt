package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.extension.map
import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.FeedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FeedViewModel @Inject constructor(
  private val feedRepository: FeedRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  val mutableHotReposMap = MutableLiveData<Map<Language, List<Repository>>>()

  fun hotRepos(language: Language): LiveData<List<Repository>> {
    return mutableHotReposMap.map { map -> map[language] ?: emptyList() }
  }

  init {
    launch {
      runCatching {
        mutableMapOf<Language, List<Repository>>().also { map ->
          Language.POPULAR_LANGUAGES.forEach { language ->
            map[language] = feedRepository.getHotRepos(language)
          }
        }
      }.onSuccess { map ->
        mutableHotReposMap.value = map
      }.onFailure {
        println("failed")
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}