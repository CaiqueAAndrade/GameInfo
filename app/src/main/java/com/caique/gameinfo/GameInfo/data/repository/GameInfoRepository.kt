package com.caique.gameinfo.GameInfo.data.repository

import android.arch.lifecycle.MutableLiveData
import com.caique.gameinfo.GameInfo.data.remote.CallApi
import com.caique.gameinfo.GameInfo.data.remote.RetrofitClientInstance
import com.caique.gameinfo.GameInfo.model.GameInfoResponse
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.REQUESTBODY_TAG
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.USER_KEY
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response


class GameInfoRepository {
    private val service: CallApi = RetrofitClientInstance.getRetrofitInstance()


    fun getGames(params: String): MutableLiveData<ArrayList<GameInfoResponse>> {
        val gameInfoResponseMutableLiveData: MutableLiveData<ArrayList<GameInfoResponse>> = MutableLiveData()
        val requestBody: RequestBody = RequestBody.create(MediaType.parse(REQUESTBODY_TAG), params)
        service.getGames(USER_KEY, requestBody).enqueue(object : retrofit2.Callback<ArrayList<GameInfoResponse>> {
            override fun onResponse(call: Call<ArrayList<GameInfoResponse>>, response: Response<ArrayList<GameInfoResponse>>) {
                gameInfoResponseMutableLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<ArrayList<GameInfoResponse>>, t: Throwable) {
                gameInfoResponseMutableLiveData.postValue(null)
            }
        })
        return gameInfoResponseMutableLiveData
    }

    object GameInfoRepositoryProvider {
        fun provideGameInfoRepository() : GameInfoRepository {
            return GameInfoRepository()
        }
    }
}
