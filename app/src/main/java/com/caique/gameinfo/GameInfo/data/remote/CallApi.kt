package com.caique.gameinfo.GameInfo.data.remote

import com.caique.gameinfo.GameInfo.model.GameInfoResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface CallApi {

    @POST("games/")
    fun getGames(@Header("user-key") userKey: String) : Call<List<GameInfoResponse>>
}