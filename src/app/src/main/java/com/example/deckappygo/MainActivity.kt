package com.example.deckappygo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({

            //Recibe datos
            //var id = intent.extras!!.getInt("ID")

            //SharedPreferences (Compartir informacion)
            var userName = "admin"
            var passwordAuthentication = "admin"

            var prefs = getSharedPreferences("com.example.deckappygo.sharedpref", Context.MODE_PRIVATE)
            prefs.edit().putString("user", userName).apply()

            var intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            finish()

        },5000)
    }
}