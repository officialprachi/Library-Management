package com.example.librarymanagementsystem

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the splash screen
        setContentView(R.layout.splash_screen_layout)
        window.statusBarColor= Color.TRANSPARENT


        Handler(Looper.getMainLooper()).postDelayed({
            //Start your main activity after the delay
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1000)

    }
}