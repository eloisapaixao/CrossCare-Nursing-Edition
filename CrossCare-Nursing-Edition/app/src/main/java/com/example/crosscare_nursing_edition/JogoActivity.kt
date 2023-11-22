package com.example.crosscare_nursing_edition

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class JogoActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jogo)

        getSupportActionBar()?.hide()

        var card1 = findViewById<Button>(R.id.btn_comecar)
        var card2 = findViewById<Button>(R.id.btn_comecar1)
        var card3 = findViewById<Button>(R.id.btn_comecar2)

        card1.setOnClickListener {
            val intent = Intent(this, FasesActivity::class.java)
            startActivity(intent)
        }

        card2.setOnClickListener {
            val intent = Intent(this, FasesActivity::class.java)
            startActivity(intent)
        }

        card3.setOnClickListener {
            val intent = Intent(this, FasesActivity::class.java)
            startActivity(intent)
        }
    }
}