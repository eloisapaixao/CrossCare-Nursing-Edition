package com.example.crosscare_nursing_edition

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.crosscare_nursing_edition.databinding.JogoBinding
import org.json.JSONObject

class JogoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jogo)

        getSupportActionBar()?.hide()

        var card1 = findViewById<CardView>(R.id.cardCenter)
        var card2 = findViewById<CardView>(R.id.cardRight1)
        var card3 = findViewById<CardView>(R.id.cardLeft1)

        card1.setOnClickListener({
            val intent = Intent(this, FasesActivity::class.java)
            startActivity(intent)
        })

        card2.setOnClickListener({
            val intent = Intent(this, FasesActivity::class.java)
            startActivity(intent)
        })

        card3.setOnClickListener({
            val intent = Intent(this, FasesActivity::class.java)
            startActivity(intent)
        })
    }
}