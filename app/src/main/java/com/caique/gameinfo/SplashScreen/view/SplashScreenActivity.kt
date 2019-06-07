package com.caique.gameinfo.SplashScreen.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.SPLASHSCREEN_CALL_GAMES
import com.caique.gameinfo.GameInfo.viewmodel.GameInfoViewModel
import com.caique.gameinfo.R

class SplashScreenActivity : AppCompatActivity () {

    private lateinit var gameInfoViewModel: GameInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

        gameInfoViewModel = ViewModelProviders.of(this)[GameInfoViewModel::class.java]

        gameInfoViewModel.getGameInfoObservable(SPLASHSCREEN_CALL_GAMES)
            .observe(this, Observer {
                if (it != null) {
                    Log.i("Response", it.get(0).name + "")
                    gameInfoViewModel.getCoverByGameId(it.get(0).id)
                }
            })

        gameInfoViewModel.coverResponseObservable().observe(this, Observer {
            Log.i("Cover", it?.get(0)?.imageUrl + "")
        })


    }

}