package com.example.crosscare_nursing_edition

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CadastroActivity : AppCompatActivity() {
    val db = Firebase.firestore

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
                val createUserData = hashMapOf(
                    "nome" to this.edtNome.text.toString(),
                    "senha" to  this.edtSenha.text.toString()
                )

                db.collection("Usuario")
                    .add(createUserData)
                    .addOnSuccessListener { documentReference ->
                        // O documento foi adicionado com sucesso, e documentReference contém o ID gerado automaticamente.
                        val novoDocumentoId = documentReference.id
                    }
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