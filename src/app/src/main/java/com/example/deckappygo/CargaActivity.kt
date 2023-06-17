package com.example.deckappygo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class CargaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carga)

        Handler(Looper.getMainLooper()).postDelayed({

            //SharedPreferences (Recibir informacion)
            var prefs = getSharedPreferences("com.example.deckappygo.sharedpref", Context.MODE_PRIVATE)
            var user = prefs.getString("user", "")

            var intent = Intent(this, MainActivity::class.java)

            //Enviar datos
            //intent.putExtra("ID", "12345")

            startActivity(intent)
            finish()

        },5000)


    }
}
