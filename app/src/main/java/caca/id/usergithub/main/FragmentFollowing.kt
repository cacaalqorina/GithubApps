package caca.id.usergithub.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import caca.id.usergithub.R
import caca.id.usergithub.databinding.FollowerFragmentBinding
import caca.id.usergithub.model.Follow

class FragmentFollowing : Fragment (R.layout.follower_fragment) {

    private var _binding : FollowerFragmentBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: Follow
    private lateinit var adapterFollow: AdapterUser
    private lateinit var username : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FollowerFragmentBinding.bind(view)
         username = arguments?.getString(DetailUser.EXTRA_USERNAME).toString()

        adapterFollow = AdapterUser()
        binding?.apply {
            follow.adapter = adapterFollow
            follow.setHasFixedSize(true)
            follow.layoutManager = LinearLayoutManager(activity)
        }

        loadingShow(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(Follow::class.java)
        viewModel.setfollowingList(username)
        viewModel.getfollowingList().observe(viewLifecycleOwner,{
            if (it!=null){
                adapterFollow.setList(it)
                loadingShow(false)
            }
        })
    }

    private fun loadingShow (state: Boolean){
        if(state){
            binding?.ProgressBar?.visibility = View.VISIBLE
        }else{
            binding?.ProgressBar?.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}