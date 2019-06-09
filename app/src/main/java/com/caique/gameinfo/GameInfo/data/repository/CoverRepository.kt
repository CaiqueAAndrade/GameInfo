package com.caique.gameinfo.GameInfo.data.repository

import android.arch.lifecycle.MutableLiveData
import com.caique.gameinfo.GameInfo.data.remote.CallApi
import com.caique.gameinfo.GameInfo.data.remote.RetrofitClientInstance
import com.caique.gameinfo.GameInfo.model.CoverResponse
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.ALL_FIELDS
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.REQUESTBODY_TAG
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.SEMICOLON
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.USER_KEY
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.WHERE_GAME
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class CoverRepository {
    private val service: CallApi = RetrofitClientInstance.getRetrofitInstance()

    fun getCover(gameId: Int): MutableLiveData<ArrayList<CoverResponse>> {
        val coverResponseMutableLiveData: MutableLiveData<ArrayList<CoverResponse>> = MutableLiveData()
        val requestBody: RequestBody = RequestBody.create(MediaType.parse(REQUESTBODY_TAG),
            WHERE_GAME + gameId + SEMICOLON + ALL_FIELDS)
        service.getCovers(USER_KEY, requestBody).enqueue(object : retrofit2.Callback<ArrayList<CoverResponse>> {
            override fun onResponse(call: Call<ArrayList<CoverResponse>>, response: Response<ArrayList<CoverResponse>>) {
                coverResponseMutableLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<ArrayList<CoverResponse>>, t: Throwable) {
                coverResponseMutableLiveData.postValue(null)
            }
        })
        return coverResponseMutableLiveData
    }

    object CoverRepositoryProvider {
        fun provideCoverRepository() : CoverRepository {
            return CoverRepository()
        }
    }
}