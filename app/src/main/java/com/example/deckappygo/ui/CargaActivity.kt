package com.example.deckappygo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.deckappygo.R

class CargaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carga)


        Handler(Looper.getMainLooper()).postDelayed({

            var intent = Intent(this, InicioActivity::class.java)

            startActivity(intent)
            finish()

        },3000)


    }
}
