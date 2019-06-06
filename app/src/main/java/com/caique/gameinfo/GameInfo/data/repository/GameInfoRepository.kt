package com.caique.gameinfo.GameInfo.data.repository

import android.arch.lifecycle.MutableLiveData
import com.caique.gameinfo.GameInfo.data.remote.CallApi
import com.caique.gameinfo.GameInfo.data.remote.RetrofitClientInstance
import com.caique.gameinfo.GameInfo.model.GameInfoResponse
import com.caique.gameinfo.GameInfo.utils.Constants
import retrofit2.Call
import retrofit2.Response


class GameInfoRepository() {
    private val service: CallApi = RetrofitClientInstance.getRetrofitInstance()

    fun getGames(params: String): MutableLiveData<List<GameInfoResponse>> {
        val gameInfoResponseMutableLiveData: MutableLiveData<List<GameInfoResponse>> = MutableLiveData()
        service.getGames(Constants.USER_KEY).enqueue(object : retrofit2.Callback<List<GameInfoResponse>> {
            override fun onResponse(call: Call<List<GameInfoResponse>>, response: Response<List<GameInfoResponse>>) {
                gameInfoResponseMutableLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<List<GameInfoResponse>>, t: Throwable) {
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
