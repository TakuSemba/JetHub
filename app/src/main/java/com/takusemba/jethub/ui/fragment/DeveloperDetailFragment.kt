package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentDeveloperDetailBinding
import dagger.android.support.DaggerFragment

class DeveloperDetailFragment : DaggerFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_developer_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentDeveloperDetailBinding>(view)!!
    val userId = DeveloperDetailFragmentArgs.fromBundle(arguments!!).userId

    Toast.makeText(context, "developer id is $userId", LENGTH_SHORT).show()
  }
}