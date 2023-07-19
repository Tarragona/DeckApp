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

<<<<<<< Updated upstream
    private lateinit var btn_Google: Button

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
=======
    private lateinit var btn_Google: SignInButton

    companion object{
        private const val RC_SIGN_IN = 423

    }
>>>>>>> Stashed changes

    private companion object{
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        googleLogin()
    }

<<<<<<< Updated upstream

        var googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        btn_Google = findViewById(R.id.btn_Google)

        btn_Google.setOnClickListener {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, 100)
=======
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
>>>>>>> Stashed changes
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){

            startActivity(Intent(this@LoginActivity, CargaActivity::class.java))
            finish()
        }
        else{

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

<<<<<<< Updated upstream
        if (requestCode == 100) {
            Log.d(TAG,"Se logueo.")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            } catch (e: Exception) {
                Log.d(TAG,"Error al loguear. ${e.message}")
=======
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

>>>>>>> Stashed changes
            }
        }

    }
<<<<<<< Updated upstream

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?){
        Log.d(TAG,"FIREBASE AUTH ACCOUNT")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {authResult ->
                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseUser!!.uid
                val email = firebaseUser.email

                if (authResult.additionalUserInfo!!.isNewUser){
                    //Crear
                    Toast.makeText(this@LoginActivity, "Cuenta Creada...", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@LoginActivity, "Cuenta Existente...", Toast.LENGTH_LONG).show()
                }
                startActivity(Intent(this, CargaActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this@LoginActivity, "Login fallo...", Toast.LENGTH_LONG).show()
            }
    }
=======
>>>>>>> Stashed changes
}