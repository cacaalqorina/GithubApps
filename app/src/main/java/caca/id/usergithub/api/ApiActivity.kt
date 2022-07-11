package caca.id.usergithub.api

import caca.id.usergithub.model.ResponseUser
import caca.id.usergithub.model.ResponseUserDetail
import caca.id.usergithub.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiActivity {
    @GET("search/users")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<ResponseUser>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<ResponseUserDetail>

    @GET ("users/{username}/followers")
   fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
     fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}