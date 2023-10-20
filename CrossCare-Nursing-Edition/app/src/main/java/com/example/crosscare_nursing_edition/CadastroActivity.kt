package com.example.crosscare_nursing_edition

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class CadastroActivity : AppCompatActivity() {
    lateinit var btnEntrar: Button
    lateinit var edtNome: EditText
    lateinit var edtSenha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastrar)

        getSupportActionBar()?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        this.btnEntrar = findViewById(R.id.btnEntrar)
        this.edtNome = findViewById(R.id.etEmail)
        this.edtSenha = findViewById(R.id.etSenha)

        this.btnEntrar.setOnClickListener (){
            try{
                val url = "http://192.168.107.46:3000/cadastro"

                val queue: RequestQueue = MyVolley.getRequestQueue()
                
                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    Response.Listener { response ->
                        val intent = Intent(this, JogoActivity::class.java)
                        startActivity(intent)
                    },
                    Response.ErrorListener { error ->
                        // TODO: Handle error
                    }
                )

                // Access the RequestQueue through your singleton class.
                MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
            }
            catch (e: Exception){
                val mensagem = "Você clicou no botão!"
                val duracao = Toast.LENGTH_SHORT // ou Toast.LENGTH_LONG

                val toast = Toast.makeText(applicationContext, mensagem, duracao)
                toast.show()
            }
        }
    }
}