package caca.id.usergithub.settings

import android.content.Context
import android.content.SharedPreferences

class prefrence (context: Context) {

    private val nameGithub = "usergithub"
    private var prefGithub : SharedPreferences = context.getSharedPreferences(nameGithub, Context.MODE_PRIVATE)
    val prefEditGithub: SharedPreferences.Editor = prefGithub.edit()

    fun put(key: String, value : Boolean){
        prefEditGithub.putBoolean(key,value)
            .apply()
    }

    fun getBoolean(key : String):Boolean{
        return prefGithub.getBoolean(key, false)
    }

}