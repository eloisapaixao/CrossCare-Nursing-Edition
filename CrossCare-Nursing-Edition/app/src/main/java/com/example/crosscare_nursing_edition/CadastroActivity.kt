package com.example.crosscare_nursing_edition

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class CadastroActivity : AppCompatActivity() {
    lateinit var btnEntrar: Button
    lateinit var edtEmail: EditText
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
        this.edtEmail = findViewById(R.id.etEmail)
        this.edtSenha = findViewById(R.id.etSenha)

        this.btnEntrar.setOnClickListener(){
            try{
                val url = "http://192.168.116.46:3000/cadastro"
                val requestQueue = Volley.newRequestQueue(this)
                val jsonObject = JSONObject()
                jsonObject.put("email", edtEmail.text.toString())
                jsonObject.put("senha", edtSenha.text.toString())

                val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,
                    url,
                    jsonObject,
                    { response ->
                        val intent = Intent(this, JogoActivity::class.java)
                        startActivity(intent)
                    },
                    { error ->//Erro ao coletar os dados!
                        val toast = Toast.makeText(applicationContext, "Erro: ${error.message}", Toast.LENGTH_LONG)
                        toast.show()
                    })

                requestQueue.add(jsonObjectRequest)
            }
            catch (e: Exception){
                val mensagem = "Erro ao cadastrar!"
                val duracao = Toast.LENGTH_SHORT // ou Toast.LENGTH_LONG

                val toast = Toast.makeText(applicationContext, mensagem, duracao)
                toast.show()
            }
        }
    }
}