package com.example.crosscare_nursing_edition

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom


class FasesActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fases)

        getSupportActionBar()?.hide()
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

            fun convertDpToPixel(dp: Int): Int {
                val metrics: DisplayMetrics = Resources.getSystem().getDisplayMetrics()
                val px = dp * (metrics.densityDpi / 160f)
                return Math.round(px)
            }
        val activityRootView: View = findViewById(R.id.rl)
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(
            OnGlobalLayoutListener {

                if(findViewById<View>(R.id.rectangle_3).y.toDouble() == 1920.0){
                    findViewById<View>(R.id.jogoCoiso).layoutParams = RelativeLayout.LayoutParams(findViewById<View>(R.id.jogoCoiso).width, convertDpToPixel(535))
                    val param = findViewById<View>(R.id.jogoCoiso).layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(0,350,0,0)
                    findViewById<View>(R.id.jogoCoiso).layoutParams = param
                }else if(findViewById<View>(R.id.rectangle_3).y.toDouble() == 1047.0){
                    findViewById<View>(R.id.jogoCoiso).layoutParams = RelativeLayout.LayoutParams(findViewById<View>(R.id.jogoCoiso).width, convertDpToPixel(255))
                    val param = findViewById<View>(R.id.jogoCoiso).layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(0,350,0,0)
                    findViewById<View>(R.id.jogoCoiso).layoutParams = param
                }
            })
    }
}