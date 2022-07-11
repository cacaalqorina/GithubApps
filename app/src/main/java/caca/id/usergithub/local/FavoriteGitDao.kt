package caca.id.usergithub.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoriteGitDao {

    @Insert
    suspend fun addToFavorite(favoriteGit: FavoriteGit)

    @Query("SELECT * FROM favorite_git")
    fun getFavoriteGit(): LiveData<List<FavoriteGit>>

    @Query("SELECT count(*) FROM favorite_git WHERE favorite_git.id = :id ")
    suspend fun checkGit(id: Int ): Int

    @Query("DELETE FROM favorite_git WHERE favorite_git.id = :id")
    suspend fun removeFromFavorite(id: Int): Int
}