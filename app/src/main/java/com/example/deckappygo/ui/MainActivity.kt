package com.example.deckappygo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.deckappygo.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val authUser: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
            //Recibe datos
            //var id = intent.extras!!.getInt("ID")

            //SharedPreferences (Compartir informacion)
            var userName = "admin"
            var passwordAuthentication = "admin"

            var prefs = getSharedPreferences("com.example.deckappygo.sharedpref", Context.MODE_PRIVATE)
            prefs.edit().putString("user", userName).apply()

            //SE ROMPE
//            btnLogin = findViewById(R.id.btnLogin)
//            btnLogin.setOnClickListener {
//                val intent: Intent = Intent(this, CargaActivity::class.java)
//                startActivity(intent)
//            }

        var txtLogin = findViewById<TextView>(R.id.txtLogin)
        txtLogin.setOnClickListener {
                val intent: Intent = Intent(this, CargaActivity::class.java)
                startActivity(intent)
            }
        goTo()
    }

    private fun checkUser() {
        val firebaseUser = authUser.currentUser
        if(firebaseUser == null){
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

    fun goTo(){
        if(authUser.currentUser != null){
            startActivity(Intent(this, CargaActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}