package com.example.librarymanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
//import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 1001
    lateinit var buttonLogin:Button
    lateinit var buttonGoogleSignIn:Button
    lateinit var textViewForgotPassword:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        buttonLogin=findViewById(R.id.buttonLogin)
        buttonGoogleSignIn=findViewById(R.id.buttonGoogleSignIn)
        textViewForgotPassword=findViewById(R.id.textViewForgotPassword)


        buttonLogin.setOnClickListener {
            // Handle login button click
            // Here you can authenticate with your server using the provided credentials
        }

        buttonGoogleSignIn.setOnClickListener {
            // Configure Google Sign In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        textViewForgotPassword.setOnClickListener {
            // Handle forgot password click
            // Here you can implement the logic to reset the password
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            // Here you can handle the user authentication with your backend server
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            // Log.e(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }
}
