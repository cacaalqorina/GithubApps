package caca.id.usergithub.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import caca.id.usergithub.api.ClientRetrofit
import caca.id.usergithub.local.DatabaseFavGit
import caca.id.usergithub.local.FavoriteGit
import caca.id.usergithub.local.FavoriteGitDao
import caca.id.usergithub.model.ResponseUserDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewDetailModel(application: Application) : AndroidViewModel (application) {

    val modeluser = MutableLiveData<ResponseUserDetail>()

    private var FavoriteGitDao: FavoriteGitDao?
    private var dataFG: DatabaseFavGit?

    init {
        dataFG = DatabaseFavGit.getDatabase(application)
        FavoriteGitDao = dataFG?.FavoriteGitDao()
    }

    fun setmodeluserdetail (id: String){
        ClientRetrofit.apiInstance
            .getDetailUser(id)
            .enqueue(object : Callback<ResponseUserDetail>{
                override fun onResponse(
                    call: Call<ResponseUserDetail>,
                    response: Response<ResponseUserDetail>
                ) {
                    if (response.isSuccessful){
                        modeluser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ResponseUserDetail>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })

    }

    fun getmodeluserdetail() : LiveData<ResponseUserDetail>{
        return modeluser
    }

    fun addToFavorite (username: String, id: Int, avatarUrl:String){
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteGit(
                username,
                id,
                avatarUrl
            )
            FavoriteGitDao?.addToFavorite(user)
        }
    }

    suspend fun checkGit(id: Int) = FavoriteGitDao?.checkGit(id)

    fun removeFromFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            FavoriteGitDao?.removeFromFavorite(id)
        }
    }
}