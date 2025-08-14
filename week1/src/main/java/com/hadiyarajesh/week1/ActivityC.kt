package com.hadiyarajesh.week1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ActivityC : AppCompatActivity() {
    lateinit var launchActivityB: Button
    lateinit var launchActivityC: Button
    lateinit var launchActivityD: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_c)
        launchActivityB = findViewById(R.id.launchActivityB)
        launchActivityC = findViewById(R.id.launchActivityC)
        launchActivityD = findViewById(R.id.launchActivityD)

        launchActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)
        }

        launchActivityC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            val randomNumber = Random.nextInt(1, 100)
            Log.d("TAG", "randomNumber: $randomNumber")
            intent.putExtra("counter", randomNumber)
            startActivity(intent)
        }

        launchActivityD.setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            startActivity(intent)
        }

        val intentExtra = intent.extras
        val counter = intentExtra?.getInt("counter")
        Toast.makeText(this, "Counter: $counter", Toast.LENGTH_SHORT).show()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val intentExtra = intent?.extras
        val counter = intentExtra?.getInt("counter")
        Toast.makeText(this, "Counter: $counter", Toast.LENGTH_SHORT).show()
    }
}