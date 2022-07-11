package caca.id.usergithub.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import caca.id.usergithub.api.ClientRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Follow : ViewModel (){
    val followersList = MutableLiveData<ArrayList<User>>()
    val followingList = MutableLiveData<ArrayList<User>>()

    fun setfollowList(id : String){
        ClientRetrofit.apiInstance
            .getFollowers(id)
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        followersList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getfollowersList(): LiveData<ArrayList<User>>{
        return followersList
    }

    fun setfollowingList(id : String){
        ClientRetrofit.apiInstance
            .getFollowing(id)
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        followersList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getfollowingList(): LiveData<ArrayList<User>>{
        return followersList
    }
}