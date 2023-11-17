package com.example.crosscare_nursing_edition

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.crosscare_nursing_edition.databinding.JogoBinding

class JogoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jogo)

        getSupportActionBar()?.hide()

        var card1 = findViewById<CardView>(R.id.cardCenter)

        card1.setOnClickListener({
            val intent = Intent(this, FasesActivity::class.java)
            startActivity(intent)
        })
    }
}