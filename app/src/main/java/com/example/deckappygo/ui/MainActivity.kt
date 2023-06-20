package com.example.deckappygo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.deckappygo.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var btnLogin: Button
    private lateinit var txtLogin: TextView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)




        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()



        //Handler(Looper.getMainLooper()).postDelayed({

            //Recibe datos
            //var id = intent.extras!!.getInt("ID")

            //SharedPreferences (Compartir informacion)
            var userName = "admin"
            var passwordAuthentication = "admin"

            var prefs = getSharedPreferences("com.example.deckappygo.sharedpref", Context.MODE_PRIVATE)
            prefs.edit().putString("user", userName).apply()



            /*btnLogin = findViewById(R.id.btnLogin)
            btnLogin.setOnClickListener {
                val intent: Intent = Intent(this, InicioActivity::class.java)
                startActivity(intent)
            }*/

            txtLogin = findViewById(R.id.txtLogin)
            txtLogin.setOnClickListener {
                val intent: Intent = Intent(this, InicioActivity::class.java)
                startActivity(intent)
            }

            //var intent = Intent(this, MainActivity::class.java)

            //startActivity(intent)
            //finish()



    }

    private fun checkUser(){
        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser == null){
//            //Usuario no logueado
//            startActivity(Intent(this, MainActivity::class.java))
//        }
    }
}