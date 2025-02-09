package com.example.deckappygo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deckappygo.R
import com.example.deckappygo.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var btn_Google: Button
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    private companion object{
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
        private const val RC_SIGN_IN = 423
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.btnGoogle.setOnClickListener {
            Log.d(TAG,"Apretar el boton")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }


    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            // START PROFILE ACTIVITY
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
        else{

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            Log.d(TAG,"Se logueo. sorprendido")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }
            catch (e: Exception){
                Log.d(TAG,"Error al loguear ${e.message}")
            }
        }

    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG,"FIREBASE AUTH ACCOUNT")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->

                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseUser!!.uid
                val email = firebaseUser!!.email

                if(authResult.additionalUserInfo!!.isNewUser){
                    Toast.makeText(this@LoginActivity,"Logeado con exito: \n Se creo el usuario correctamente",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@LoginActivity,"Logeado con exito",Toast.LENGTH_SHORT).show()

                }
                // START PROFILE ACTIVITY
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()

            }
            .addOnFailureListener { e ->
                Log.d(TAG,"No se loegueo. Se rompio todo ${e.message}")
                Toast.makeText(this@LoginActivity,"No se logueo pa. fijate",Toast.LENGTH_SHORT).show()
            }


    }

//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//
//
//
//        //googleLogin()
//    }
//
//
//    fun googleLogin(){
//        val providers = arrayListOf(
//            AuthUI.IdpConfig.GoogleBuilder().build())
//
//        binding.btnGoogle.setOnClickListener{
//            startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//                    .setIsSmartLockEnabled(false)
//                    .build(),
//                RC_SIGN_IN)
//
////
////        btn_Google = findViewById(R.id.btn_Google)
////        btn_Google.setOnClickListener {
////            startActivityForResult(
////                AuthUI.getInstance()
////                    .createSignInIntentBuilder()
////                    .setAvailableProviders(providers)
////                    .setIsSmartLockEnabled(false)
////                    .build(),
////                RC_SIGN_IN)
//        }
//    }
//
//    private fun checkUser() {
//        val firebaseUser = firebaseAuth.currentUser
//        if(firebaseUser != null){
//
//            startActivity(Intent(this@LoginActivity, CargaActivity::class.java))
//            finish()
//        }
//        else{
//
//        }
//
//    }
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 100) {
//            Log.d(TAG,"Se logueo.")
//            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = accountTask.getResult(ApiException::class.java)
//                firebaseAuthWithGoogleAccount(account)
//            } catch (e: Exception) {
//                Log.d(TAG, "Error al loguear. ${e.message}")
//            }
//            if (requestCode == RC_SIGN_IN) {
//                val response = IdpResponse.fromResultIntent(data)
//                if (resultCode == RESULT_OK) {
//                    // Successfully signed in
//                    val user = FirebaseAuth.getInstance().currentUser
//                    Toast.makeText(this,"Bienvenid@ ${user!!.displayName}", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this,InicioActivity::class.java))
//                    finish()
//                } else {
//                    // Sign in failed. If response is null the user canceled the
//                    // sign-in flow using the back button. Otherwise check
//                    // response.getError().getErrorCode() and handle the error.
//                    // ...
//                    Toast.makeText(this,"Ocurrio un error ${response!!.error!!.errorCode}",Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?){
//        Log.d(TAG,"FIREBASE AUTH ACCOUNT")
//
//        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
//        firebaseAuth.signInWithCredential(credential)
//            .addOnSuccessListener {authResult ->
//                val firebaseUser = firebaseAuth.currentUser
//                val uid = firebaseUser!!.uid
//                val email = firebaseUser.email
//
//                if (authResult.additionalUserInfo!!.isNewUser){
//                    //Crear
//                    Toast.makeText(this@LoginActivity, "Cuenta Creada...", Toast.LENGTH_LONG).show()
//                }
//                else{
//                    Toast.makeText(this@LoginActivity, "Cuenta Existente...", Toast.LENGTH_LONG).show()
//                }
//                startActivity(Intent(this, CargaActivity::class.java))
//                finish()
//            }
//            .addOnFailureListener {
//                Toast.makeText(this@LoginActivity, "Login fallo...", Toast.LENGTH_LONG).show()
//            }
//    }
}

