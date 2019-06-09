package com.caique.gameinfo.GameInfo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.caique.gameinfo.GameInfo.data.repository.CoverRepository
import com.caique.gameinfo.GameInfo.data.repository.GameInfoRepository
import com.caique.gameinfo.GameInfo.model.CoverResponse
import com.caique.gameinfo.GameInfo.model.GameInfoResponse

class GameInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val gameInfoRepository = GameInfoRepository.GameInfoRepositoryProvider.provideGameInfoRepository()
    private val coverRepository = CoverRepository.CoverRepositoryProvider.provideCoverRepository()

    private val coverResponse: MutableLiveData<ArrayList<CoverResponse>> = MutableLiveData()


    fun coverResponseObservable() : MutableLiveData<ArrayList<CoverResponse>> {
        return coverResponse
    }

    fun getGameInfoObservable(params: String): LiveData<ArrayList<GameInfoResponse>> {
        return gameInfoRepository.getGames(params)
    }

    fun getCoverByGameId(gameId: Int) {
        val coverResponseObserver: LiveData<ArrayList<CoverResponse>> = coverRepository.getCover(gameId)

        coverResponseObserver.observeForever(object : Observer<ArrayList<CoverResponse>>{
            override fun onChanged(t: ArrayList<CoverResponse>?) {
                coverResponse.postValue(coverResponseObserver.value)
                coverResponseObserver.removeObserver(this)
            }
        })
    }

}