package caca.id.usergithub.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import caca.id.usergithub.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUser : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: UserViewDetailModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)

        val bundle = Bundle()


        bundle.putString(EXTRA_USERNAME, username)
        loadingShow(true)

        viewModel = ViewModelProvider(this).get(UserViewDetailModel::class.java)

        if (username != null) {
            viewModel.setmodeluserdetail(username)
        }
        viewModel.getmodeluserdetail().observe(this) {
            if (it != null) {
                binding.apply {
                    itemUsername.text = it.login
                    itemName.text = it.name
                    itemCompany.text = it.company
                    itemLocation.text = it.location
                    itemRepository.text = "${it.public_repos}"
                    itemFollowers.text = "${it.followers}"
                    itemFollowing.text = "${it.following}"
                    Glide.with(this@DetailUser)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(itemPhoto)
                    loadingShow(false)
                }
            }
        }

        var _Checked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkGit(id)
            withContext(Dispatchers.Main){
                if (count!=null){
                    if (count>0){
                        binding.toggleButton.isChecked = true
                        _Checked = true
                    }else{
                        binding.toggleButton.isChecked = false
                        _Checked = false
                    }
                }
            }

        }

        binding.toggleButton.setOnClickListener{
            _Checked = !_Checked
            if (_Checked){
                viewModel.addToFavorite(username.toString(), id, avatarUrl.toString())
            }else{
                viewModel.removeFromFavorite(id)
            }
            binding.toggleButton.isChecked =_Checked
        }

        val adapterPager = AdapterPager(this, supportFragmentManager, bundle)
        binding.apply {
            viewpager.adapter = adapterPager
            tablayout.setupWithViewPager(viewpager)
        }
    }
    private fun loadingShow (state: Boolean){
        if(state){
            binding?.ProgressBar?.visibility = View.VISIBLE
        }else{
            binding?.ProgressBar?.visibility = View.GONE
        }
    }
}