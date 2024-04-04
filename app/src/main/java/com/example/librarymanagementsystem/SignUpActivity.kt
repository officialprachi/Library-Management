package com.example.librarymanagementsystem

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import HomepageActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var btn_signup:Button
    private  lateinit var email:EditText
    private lateinit var pwd:EditText
    private lateinit var confirmpwd:EditText
    private lateinit var Name:EditText
private lateinit var loginhere:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signp_activity)
        window.statusBarColor= Color.TRANSPARENT
        btn_signup=findViewById(R.id.btn_signup)
        email=findViewById(R.id.email)
        pwd=findViewById(R.id.pwd)
        confirmpwd =findViewById(R.id.confirmpwd)
        Name=findViewById(R.id.Name)
        loginhere=findViewById(R.id.loginhere)

                // Initialize Firebase Auth
                auth = FirebaseAuth.getInstance()

                btn_signup.setOnClickListener {
                    val email = email.text.toString()
                    val password = pwd.text.toString()
                    val confirmpassword = confirmpwd.text.toString()
                    if (password != confirmpassword) {
                    // Display a toast message indicating password mismatch
                    Toast.makeText(
                        this@SignUpActivity,
                        "Passwords do not match.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Call Firebase custom authentication method
                    signUp(email, password)
                }
                }
        loginhere.setOnClickListener {
            // Define the intent to navigate to the Login Activity
            val intent = Intent(this, LoginActivity::class.java)

            // Start the Login Activity
            startActivity(intent)
        }

            }

            private fun signUp(email: String, password: String) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign up success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            Toast.makeText(
                                this@SignUpActivity,
                                "Sign up successful.",
                                Toast.LENGTH_SHORT
                            ).show()
                            // You can proceed with additional actions here, like navigating to another activity
                            val intent = Intent(this, HomepageActivity::class.java)

                            // Start the Homepage Activity
                            startActivity(intent)
                        } else {
                            // If sign up fails, display a message to the user.
                            try {
                                throw task.exception!!
                            } catch (e: FirebaseAuthUserCollisionException) {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Email is already registered.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } catch (e: FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Invalid email or password format.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } catch (e: Exception) {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Sign up failed. Please try again later.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }



            }
        }



