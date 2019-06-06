package com.caique.gameinfo.GameInfo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.caique.gameinfo.GameInfo.data.repository.GameInfoRepository
import com.caique.gameinfo.GameInfo.model.GameInfoResponse

class GameInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GameInfoRepository.GameInfoRepositoryProvider.provideGameInfoRepository()

    fun getGameInfoObservable(params: String): LiveData<List<GameInfoResponse>> {
        return repository.getGames(params)
    }
}