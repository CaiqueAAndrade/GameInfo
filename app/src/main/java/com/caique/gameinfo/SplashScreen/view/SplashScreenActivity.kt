package com.caique.gameinfo.SplashScreen.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.caique.gameinfo.GameInfo.model.GameInfoResponse
import com.caique.gameinfo.GameInfo.utils.Constants.Companion.SPLASHSCREEN_CALL_GAMES
import com.caique.gameinfo.GameInfo.viewmodel.GameInfoViewModel
import com.caique.gameinfo.R
import kotlinx.android.synthetic.main.splashscreen_activity.*

class SplashScreenActivity : AppCompatActivity () {

    private lateinit var gameInfoViewModel: GameInfoViewModel
    private var gameInfoResponses: List<GameInfoResponse> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

        gameInfoViewModel = ViewModelProviders.of(this)[GameInfoViewModel::class.java]

        subscribe()
    }

    private fun subscribe() {
        getGameList()
        getCoverList()
    }

    private fun getGameList() {
        gameInfoViewModel.getGameInfoObservable(SPLASHSCREEN_CALL_GAMES)
            .observe(this, Observer {
                if (it != null) {
                    gameInfoResponses = it
                    for (gameInfoResponse: GameInfoResponse in it) {
                        gameInfoViewModel.getCoverByGameId(gameInfoResponse.id)
                    }
                } else {
                    connectionError()
                }
            })
    }

    private fun getCoverList() {
        gameInfoViewModel.coverResponseObservable().observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                for (gameInfoResponse: GameInfoResponse in gameInfoResponses) {
                    if (it.get(0).id == gameInfoResponse.id) {
                        gameInfoResponse.imageUrl = it.get(0).imageUrl
                    }
                }
            }
        })
    }

    private fun connectionError() {
        lottie_splashscreen_animation.setAnimation("404.json")
        lottie_splashscreen_animation.playAnimation()
        lottie_splashscreen_animation.loop(false)
    }

}