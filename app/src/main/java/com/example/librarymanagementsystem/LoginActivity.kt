package com.example.librarymanagementsystem

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var btn_login:Button
    private lateinit var signup:TextView
    private lateinit var username:EditText
    private lateinit var pwd:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        window.statusBarColor= Color.TRANSPARENT
                auth = FirebaseAuth.getInstance()
        // Check if user is already logged in
        btn_login=findViewById(R.id.btn_login)
        signup=findViewById(R.id.signup)
        username=findViewById(R.id.username)
        pwd=findViewById(R.id.pwd)

                // Your login button click listener
                btn_login.setOnClickListener {
                    val email = username.text.toString()
                    val password = pwd.text.toString()

                    loginUser(email, password)
                }

                // Sign up text click listener
                signup.setOnClickListener {
                    startActivity(Intent(this, SignUpActivity::class.java))
                }
            }

            private fun loginUser(email: String, password: String) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Login success, navigate to HomepageActivity
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            // Login failed, show an error message or handle accordingly
                            try {
                                throw task.exception!!
                            } catch (e: FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Invalid email or password format.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } catch (e: Exception) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Sign up failed. Please try again later.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

    }

    }


