package com.caique.gameinfo.GameInfo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class GameInfoResponse(@PrimaryKey @SerializedName("id") val id: Int,
                       @SerializedName("name") val name: String,
                       var imageUrl: String) : Serializable

