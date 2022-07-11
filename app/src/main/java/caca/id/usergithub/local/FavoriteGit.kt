package caca.id.usergithub.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_git")
data class FavoriteGit(
    val login: String,

    @PrimaryKey
    val id: Int,
    val avatar_url: String
)
