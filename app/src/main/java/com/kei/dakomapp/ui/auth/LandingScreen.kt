package com.kei.dakomapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.kei.dakomapp.R
import com.kei.dakomapp.databinding.ActivityLandingScreenBinding
import com.kei.dakomapp.ui.MainActivity
import com.kei.dakomapp.ui.auth.LoginActivity
import com.kei.dakomapp.ui.auth.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class LandingScreen : AppCompatActivity(), View.OnClickListener {
    private lateinit var landingBinding: ActivityLandingScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        landingBinding = ActivityLandingScreenBinding.inflate(layoutInflater)
        setContentView(landingBinding.root)
        supportActionBar?.hide()

        landingBinding.btnLoginLanding.setOnClickListener(this)
        landingBinding.btnRegisterLanding.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnLoginLanding -> startActivity(Intent(LoginActivity.getLaunchService(this)))
            R.id.btnRegisterLanding -> startActivity(Intent(RegisterActivity.getLaunchService(this)))
        }
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(MainActivity.getLaunchService(this))
        }
    }
}