package com.caique.gameinfo.GameInfo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class GameInfoResponse(@PrimaryKey @SerializedName("id") val id: Int,
                       @SerializedName("name") val name: String,
                       val imageUrl: String)

