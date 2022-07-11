package caca.id.usergithub.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteGit::class], version = 1
)

abstract class DatabaseFavGit: RoomDatabase() {
    companion object {
        private var INSTANCE : DatabaseFavGit? = null

        fun getDatabase(cntx: Context): DatabaseFavGit?{
            if (INSTANCE == null){
                synchronized(DatabaseFavGit::class){
                    INSTANCE = Room.databaseBuilder(
                        cntx.applicationContext, DatabaseFavGit::class.java, "database_favgit"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun FavoriteGitDao(): FavoriteGitDao
}