package com.caique.gameinfo.GameInfo.data.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.caique.gameinfo.GameInfo.data.remote.CallApi
import com.caique.gameinfo.GameInfo.data.remote.RetrofitClientInstance
import com.caique.gameinfo.GameInfo.model.GameInfoResponse
import com.caique.gameinfo.GameInfo.utils.Constants
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.util.*


class GameInfoRepository {
    private val service: CallApi = RetrofitClientInstance.getRetrofitInstance()

    fun getGames(params: String): MutableLiveData<List<GameInfoResponse>> {

        val gameInfoResponseMutableLiveData: MutableLiveData<List<GameInfoResponse>> = MutableLiveData()

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), params)

        service.getGames(Constants.USER_KEY, requestBody).enqueue(object : retrofit2.Callback<List<GameInfoResponse>> {

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
