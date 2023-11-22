package com.example.crosscare_nursing_edition

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.KeyEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class FasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fases)

        getSupportActionBar()?.hide()
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        var palavras = mutableListOf<JSONObject>()
        val caju = this;

        try {
            val url = "http://192.168.116.46:3000/crossword"
            val requestQueue = Volley.newRequestQueue(this)

            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)

                        palavras.add(jsonObject)
                    }
                },
                { error ->//Erro ao coletar os dados!
                    val toast = Toast.makeText(
                        applicationContext,
                        "Erro: ${error.message}",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                })

            requestQueue.add(jsonArrayRequest)
        } catch (e: Exception) {
            val mensagem = "Erro ao carregar o jogo!"
            val duracao = Toast.LENGTH_SHORT // ou Toast.LENGTH_LONG

            val toast = Toast.makeText(applicationContext, mensagem, duracao)
            toast.show()
        }
        val activityRootView: View = findViewById(R.id.rl)
        val parteBaixo = findViewById<View>(R.id.rectangle_3)
        val parteJogo = findViewById<View>(R.id.jogoCoiso)
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(
            OnGlobalLayoutListener {
                if (parteBaixo.y.toDouble() == 1892.0) {
                    parteJogo.layoutParams =
                        RelativeLayout.LayoutParams(parteJogo.width, convertDpToPixel(535))
                    val param = parteJogo.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(0, 350, 0, 0)
                    parteJogo.layoutParams = param
                } else if (parteBaixo.y.toDouble() == 1019.0) {
                    parteJogo.layoutParams =
                        RelativeLayout.LayoutParams(parteJogo.width, convertDpToPixel(265))
                    val param = parteJogo.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(0, 350, 0, 0)
                    parteJogo.layoutParams = param
                }
            })

        fun verificarPalavras(): Boolean {
            for (i in 1..7) {
                var frase = ""
                for (j in 1..5) {
                    val sla = "q" + i + "" + j
                    frase += findViewById<EditText>(
                        resources.getIdentifier(
                            sla,
                            "id",
                            packageName
                        )
                    ).text
                }

                if (frase.uppercase() != palavras[i - 1].getString("palavra").uppercase())
                    return false
            }

            return true
        }

        for (i in 1..7) {
            for (j in 1..5) {
                val sla = "q" + i + "" + j
                findViewById<View>(
                    resources.getIdentifier(
                        sla,
                        "id",
                        packageName
                    )
                ).setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        findViewById<TextView>(R.id.dica).text = palavras[i - 1].getString("dica")
                    }
                })
                val etLetra = findViewById<EditText>(
                    resources.getIdentifier(
                        sla,
                        "id",
                        packageName
                    )
                )

                etLetra.addTextChangedListener(object :
                    TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        var frase = ""
                        for (k in 1..5) {
                            val sla2 = "q" + i + "" + k
                            frase += findViewById<EditText>(
                                resources.getIdentifier(
                                    sla2,
                                    "id",
                                    packageName
                                )
                            ).text
                            if (frase.uppercase() == palavras[i - 1].getString("palavra").uppercase()) {
                                for (l in 1..5) {
                                    val sla3 = "q" + i + "" + l
                                    findViewById<EditText>(
                                        resources.getIdentifier(
                                            sla3,
                                            "id",
                                            packageName
                                        )
                                    ).isFocusable = false
                                    findViewById<EditText>(
                                        resources.getIdentifier(
                                            sla3,
                                            "id",
                                            packageName
                                        )
                                    ).setBackgroundColor(Color.GRAY)
                                }
                                Toast.makeText(applicationContext, "Parabéns", Toast.LENGTH_SHORT)
                                    .show()

                                if(i!=7){
                                    val sim = "q" + (i + 1) + "" + 1
                                    findViewById<EditText>(
                                        resources.getIdentifier(
                                            sim,
                                            "id",
                                            packageName
                                        )
                                    ).requestFocus()
                                }

                                if (verificarPalavras()) {
                                    val intent = Intent(caju, ParabensActivity::class.java)
                                    startActivity(intent)
                                }
                            } else {
                                for (l in 1..5) {
                                    val sla3 = "q" + i + "" + l
                                    findViewById<EditText>(
                                        resources.getIdentifier(
                                            sla3,
                                            "id",
                                            packageName
                                        )
                                    ).setBackgroundColor(Color.RED)
                                }
                            }
                        }

                        if (etLetra.text.toString() != "") {
                            if (j != 5) {
                                val sla3 = "q" + i + "" + (j + 1)
                                findViewById<EditText>(
                                    resources.getIdentifier(
                                        sla3,
                                        "id",
                                        packageName
                                    )
                                ).requestFocus()
                            }
                        } else {
                            if (j != 1) {
                                val sla3 = "q" + i + "" + (j - 1)
                                findViewById<EditText>(
                                    resources.getIdentifier(
                                        sla3,
                                        "id",
                                        packageName
                                    )
                                ).requestFocus()
                            }
                        }
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }
                })
            }
        }

        var btnDireita = findViewById<ImageButton>(R.id.vaiDir)
        var btnEsquerda = findViewById<ImageButton>(R.id.vaiEsq)

        var linhaAtual = 1
        var colunaAtual = 1

        btnDireita.setOnClickListener {
            linhaAtual++
            colunaAtual = 1

            if (linhaAtual > 7) {
                linhaAtual = 1
            }

            val sla = "q$linhaAtual$colunaAtual"

            findViewById<EditText>(resources.getIdentifier(sla, "id", packageName)).requestFocus()
        }

        btnEsquerda.setOnClickListener {
            linhaAtual--
            colunaAtual = 1

            if (linhaAtual < 1) {
                linhaAtual = 7
            }

            val sla = "q$linhaAtual$colunaAtual"

            findViewById<EditText>(resources.getIdentifier(sla, "id", packageName)).requestFocus()
        }
    }

    fun convertDpToPixel(dp: Int): Int {
        val metrics: DisplayMetrics = Resources.getSystem().getDisplayMetrics()
        val px = dp * (metrics.densityDpi / 160f)
        return Math.round(px)
    }
}