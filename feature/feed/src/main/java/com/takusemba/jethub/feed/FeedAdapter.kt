package com.takusemba.jethub.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.takusemba.jethub.base.Language
import com.takusemba.jethub.base.UserViewModel
import com.takusemba.jethub.feed.databinding.ItemFeedBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class FeedAdapter(
  private val userViewModel: UserViewModel,
  private val feedViewModel: FeedViewModel,
  private val fragment: FeedFragment
) : PagerAdapter() {

  private val languages = Language.POPULAR_LANGUAGES

  override fun instantiateItem(group: ViewGroup, position: Int): Any {
    val inflater = LayoutInflater.from(group.context)
    val binding = ItemFeedBinding.inflate(inflater, group, false)

    val context = binding.root.context
    val language = languages[position]
    val feedRepoSection = FeedRepoSection(language, fragment, feedViewModel, userViewModel)

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
      add(feedRepoSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    group.addView(binding.root)
    return binding.root
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return languages[position].title
  }

  override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
    collection.removeView(view as View)
  }

  override fun getCount(): Int {
    return languages.size
  }

  override fun isViewFromObject(view: View, anything: Any): Boolean {
    return view === anything
  }
}