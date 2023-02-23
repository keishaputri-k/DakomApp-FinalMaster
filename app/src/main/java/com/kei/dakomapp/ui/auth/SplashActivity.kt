package com.kei.dakomapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kei.dakomapp.R
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler().postDelayed({
            startActivity(
                Intent(this,
                    LandingScreen::class.java)
            )
            finish()
        }, SPLASH_TIME_OUT)
    }
}