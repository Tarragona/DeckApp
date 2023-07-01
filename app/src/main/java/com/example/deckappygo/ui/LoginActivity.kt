package com.example.deckappygo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deckappygo.R
import com.example.deckappygo.ui.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var googleSignInCliente: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInCliente = GoogleSignIn.getClient(this, googleSignInOptions)
        firebaseAuth = FirebaseAuth.getInstance()
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val intent = googleSignInCliente.signInIntent
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            } catch (e: Exception) {
                Log.d("DEMO", "activityResult: ${e.message}")
            }
        }

    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?){
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseUser!!.uid
                val email = firebaseUser.email

//                if (authResult.additionalUserInfo!!.isNewUser){
//                    //Crear
//                    Toast.makeText(this@LoginActivity, "Cuenta Creada...", Toast.LENGTH_LONG).show()
//                }
//                else{
//                    Toast.makeText(this@LoginActivity, "Cuenta Existente...", Toast.LENGTH_LONG).show()
//                }
                startActivity(Intent(this, CargaActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this@LoginActivity, "Login fallo...", Toast.LENGTH_LONG).show()
            }
    }
}