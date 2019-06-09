package com.caique.gameinfo.GameInfo.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.caique.gameinfo.R

class GameInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gameinfo_activity)

        val intent: Intent = intent
        val gameInfoResponses = intent.getSerializableExtra("gamelist")
    }
}