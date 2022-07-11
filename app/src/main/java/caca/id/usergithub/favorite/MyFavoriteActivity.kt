package caca.id.usergithub.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import caca.id.usergithub.databinding.ActivityMyFavoriteBinding
import caca.id.usergithub.local.FavoriteGit
import caca.id.usergithub.main.AdapterUser
import caca.id.usergithub.main.DetailUser
import caca.id.usergithub.model.User

class MyFavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMyFavoriteBinding
    private lateinit var adapterUser: AdapterUser
    private lateinit var viewModel: MyFavoriteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterUser = AdapterUser()
        adapterUser.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(MyFavoriteViewModel::class.java)

        adapterUser.setOnItemClickCallback(object : AdapterUser.OnItemClickCallback{
            override fun OnItemClicked(data: User) {
                Intent(this@MyFavoriteActivity, DetailUser::class.java).also {
                    it.putExtra(DetailUser.EXTRA_USERNAME,data.login)
                    it.putExtra(DetailUser.EXTRA_ID,data.id)
                    it.putExtra(DetailUser.EXTRA_URL,data.avatar_url)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            user.setHasFixedSize(true)
            user.layoutManager = LinearLayoutManager(this@MyFavoriteActivity)
            user.adapter = adapterUser
        }

        viewModel.getFavoriteGit()?.observe(this,{
            if (it!=null){
                val list = mapList (it)
                adapterUser.setList(list)
            }
        })
    }

    private fun mapList(users: List<FavoriteGit>):ArrayList<User>{
        val listUsers = ArrayList<User>()
        for (user in users){
            val userMapped = User(
                user.id,
                user.login,
                user.avatar_url
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}