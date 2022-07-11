package caca.id.usergithub.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import caca.id.usergithub.R
import caca.id.usergithub.databinding.ActivityMainBinding
import caca.id.usergithub.favorite.MyFavoriteActivity
import caca.id.usergithub.model.User
import caca.id.usergithub.settings.MySettings
import caca.id.usergithub.settings.MySettings.Companion.MODE
import caca.id.usergithub.settings.prefrence

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    private lateinit var Modelview : UserViewModel
    private lateinit var adapterUser: AdapterUser

    private val prefGithub by lazy { prefrence(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when(prefGithub.getBoolean(MODE)){
            true->{

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false ->{

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }


        binding.btnFavorite.setOnClickListener {
            val view = Intent(this@MainActivity, MyFavoriteActivity::class.java)
            startActivity(view)
        }

        binding.btnSetting.setOnClickListener {
            val view = Intent(this@MainActivity, MySettings::class.java)
            startActivity(view)
        }

        adapterUser = AdapterUser()
        adapterUser.notifyDataSetChanged()
        adapterUser.setOnItemClickCallback(object : AdapterUser.OnItemClickCallback{
            override fun OnItemClicked(data: User) {
                Intent(this@MainActivity,DetailUser::class.java).also {
                    it.putExtra(DetailUser.EXTRA_USERNAME,data.login)
                    it.putExtra(DetailUser.EXTRA_ID,data.id)
                    it.putExtra(DetailUser.EXTRA_URL,data.avatar_url)
                    startActivity(it)
                }
            }

        })
        Modelview = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        binding.apply {
            user.layoutManager = LinearLayoutManager(this@MainActivity)
            user.setHasFixedSize(true)
            user.adapter = adapterUser

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(etquery: String?): Boolean {
                    if (etquery != null && etquery.isNotEmpty()) {
                        userseacrh()
                    }
                    return true
                }

                override fun onQueryTextChange(textnew: String?): Boolean = false
            })
        }

        Modelview.getuserviewmodel().observe(this,{
            if (it!=null){
                adapterUser.setList(it)
                loadingShow(false)
                binding.user.visibility =View.VISIBLE
            }
        })
    }

    private fun userseacrh(){
        binding.apply {
            val query = searchView.query.toString()
            if (query.isEmpty()) return
            binding.user.visibility = View.GONE
            loadingShow(true)
            Modelview.setuserviewmodel(query)
        }
    }

    private fun loadingShow (state: Boolean){
        if(state){
            binding.ProgressBar.visibility = View.VISIBLE
        }else{
            binding.ProgressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.choice,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Myfavorite -> {
                Intent(this,MyFavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.MyThema ->{
                Intent(this,MySettings::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}