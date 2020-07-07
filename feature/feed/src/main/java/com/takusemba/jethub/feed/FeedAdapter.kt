package com.takusemba.jethub.feed

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.takusemba.jethub.base.model.ColoredLanguage

class FeedAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

  private val languages = ColoredLanguage.POPULAR_LANGUAGES

  fun getTitle(position: Int): String {
    return languages[position].title
  }

  override fun getItemCount(): Int {
    return languages.size
  }

  override fun createFragment(position: Int): Fragment {
    return FeedChannelFragment.newInstance(languages[position].title)
  }
}
