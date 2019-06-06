package com.caique.gameinfo.GameInfo.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.caique.gameinfo.GameInfo.model.GameInfoResponse

@Database(entities = [GameInfoResponse::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {

        private val DATABASE_NAME = "gameinfo"
        private var instance: AppDataBase? = null

        fun getAppDatabase(context: Context): AppDataBase? {
            if (instance == null) {
                synchronized(AppDataBase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        DATABASE_NAME
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}