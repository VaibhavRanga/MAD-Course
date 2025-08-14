package com.hadiyarajesh.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ActivityE : AppCompatActivity() {
    lateinit var launchActivityC: Button
    lateinit var launchActivityD: Button
    lateinit var launchActivityE: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_e)

        launchActivityC = findViewById(R.id.launchActivityC)
        launchActivityD = findViewById(R.id.launchActivityD)
        launchActivityE = findViewById(R.id.launchActivityE)

        launchActivityC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }

        launchActivityD.setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            startActivity(intent)
        }

        launchActivityE.setOnClickListener {
            val intent = Intent(this, ActivityE::class.java)
            startActivity(intent)
        }
    }
}