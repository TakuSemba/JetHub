package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentUserDetailBinding
import dagger.android.support.DaggerFragment

class UserDetailFragment : DaggerFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_user_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentUserDetailBinding>(view)!!
    val userId = UserDetailFragmentArgs.fromBundle(arguments!!).userId

    Toast.makeText(context, "user id is $userId", LENGTH_SHORT).show()
  }
}