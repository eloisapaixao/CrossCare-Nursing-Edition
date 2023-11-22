package com.example.crosscare_nursing_edition

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ParabensActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parabens)

        getSupportActionBar()?.hide()

        val btnC = findViewById<Button>(R.id.btnContinuar)

        btnC.setOnClickListener({
            val intent = Intent(this, JogoActivity::class.java)
            startActivity(intent)
        })
    }
}