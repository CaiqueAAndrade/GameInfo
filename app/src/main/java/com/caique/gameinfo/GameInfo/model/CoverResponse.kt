package com.caique.gameinfo.GameInfo.model

import com.google.gson.annotations.SerializedName


class CoverResponse(@SerializedName("game") val id: Int,
                    @SerializedName("url") val imageUrl: String)