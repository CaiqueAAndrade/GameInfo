package com.caique.gameinfo.GameInfo.data.remote

import com.caique.gameinfo.GameInfo.model.GameInfoResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*
import kotlin.collections.HashMap

interface CallApi {

    @POST("games")
    fun getGames(@Header("user-key") userKey: String,
                 @Body params: RequestBody) : Call<List<GameInfoResponse>>
}