package caca.id.usergithub.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import caca.id.usergithub.api.ClientRetrofit
import caca.id.usergithub.model.ResponseUser
import caca.id.usergithub.model.ResponseUserDetail
import caca.id.usergithub.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    val userview = MutableLiveData<ArrayList<User>>()

    fun setuserviewmodel(query: String) {
        ClientRetrofit.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful) {
                        userview.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getuserviewmodel(): LiveData<ArrayList<User>> {
        return userview
    }
}