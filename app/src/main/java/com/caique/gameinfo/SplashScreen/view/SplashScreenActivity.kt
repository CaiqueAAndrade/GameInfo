package com.caique.gameinfo.SplashScreen.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.caique.gameinfo.GameInfo.viewmodel.GameInfoViewModel
import com.caique.gameinfo.R
import kotlinx.android.synthetic.main.splashscreen_activity.*

class SplashScreenActivity : AppCompatActivity () {

    private lateinit var gameInfoViewModel: GameInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

        gameInfoViewModel = ViewModelProviders.of(this)[GameInfoViewModel::class.java]

        gameInfoViewModel.getGameInfoObservable("fields name, id; sort popularity desc; limit 50;")
            .observe(this, Observer {
                Log.i("Response", it?.get(0)?.name)
            })

    }

}