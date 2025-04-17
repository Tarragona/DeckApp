package com.example.deckappygo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deckappygo.R
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse

class LoginActivity : AppCompatActivity() {

    private lateinit var btn_Google: SignInButton

    companion object{
        private const val RC_SIGN_IN = 423

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        googleLogin()
    }

    fun googleLogin(){

        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        btn_Google = findViewById(R.id.btn_Google)
        btn_Google.setOnClickListener {
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(false)
                    .build(),
                RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this,"Bienvenid@ ${user!!.displayName}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,InicioActivity::class.java))
                finish()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Toast.makeText(this,"Ocurrio un error ${response!!.error!!.errorCode}",Toast.LENGTH_SHORT).show()

            }
        }

    }
}