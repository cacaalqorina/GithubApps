package caca.id.usergithub.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import caca.id.usergithub.local.DatabaseFavGit
import caca.id.usergithub.local.FavoriteGit
import caca.id.usergithub.local.FavoriteGitDao

class MyFavoriteViewModel(application: Application):AndroidViewModel(application) {

    private var FavoriteGitDao: FavoriteGitDao?
    private var dataFG: DatabaseFavGit?

    init {
        dataFG = DatabaseFavGit.getDatabase(application)
        FavoriteGitDao = dataFG?.FavoriteGitDao()
    }

    fun getFavoriteGit(): LiveData<List<FavoriteGit>>?{
        return FavoriteGitDao?.getFavoriteGit()
    }
}