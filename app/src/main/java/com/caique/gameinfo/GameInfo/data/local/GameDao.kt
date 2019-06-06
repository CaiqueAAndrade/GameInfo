package com.caique.gameinfo.GameInfo.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.caique.gameinfo.GameInfo.model.GameInfoResponse

@Dao
interface GameDao {

    @Query("SELECT * FROM gameinforesponse")
    fun getGameDao(): LiveData<List<GameInfoResponse>>

    @Insert
    fun addGameDao(vararg gameInfoResponse: LiveData<List<GameInfoResponse>>)

    @Query("delete from gameinforesponse")
    fun deleteAll()

}