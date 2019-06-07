package com.caique.gameinfo.GameInfo.data.remote

import com.caique.gameinfo.GameInfo.model.CoverResponse
import com.caique.gameinfo.GameInfo.model.GameInfoResponse
import com.caique.gameinfo.GameInfo.utils.Constants
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.USER_KEY_DESCRIPTION
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*
import kotlin.collections.HashMap

interface CallApi {

    @POST(Constants.GAMES)
    fun getGames(@Header(USER_KEY_DESCRIPTION) userKey: String,
                 @Body params: RequestBody) : Call<List<GameInfoResponse>>

    @POST(Constants.COVERS)
    fun getCovers(@Header(USER_KEY_DESCRIPTION) userKey: String,
                  @Body params: RequestBody) : Call<List<CoverResponse>>
}