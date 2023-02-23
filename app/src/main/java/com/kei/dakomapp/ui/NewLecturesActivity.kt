package com.kei.dakomapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kei.dakomapp.R
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class NewLecturesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_lectures)
    }
}