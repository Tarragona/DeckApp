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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deckappygo.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val authUser: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
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

            txtLogin = findViewById(R.id.txtLogin)
            txtLogin.setOnClickListener {
                val intent: Intent = Intent(this, CargaActivity::class.java)
                startActivity(intent)
            }
=======
        goTo()
>>>>>>> Stashed changes

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