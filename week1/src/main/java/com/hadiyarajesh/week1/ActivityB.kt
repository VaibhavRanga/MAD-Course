package com.hadiyarajesh.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ActivityB : AppCompatActivity() {
    lateinit var launchActivityB: Button
    lateinit var launchActivityC: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_b)

//        val intentExtra = intent.extras
//        val counter = intentExtra?.getInt("counter")
//        Toast.makeText(this, "Counter: $counter", Toast.LENGTH_SHORT).show()

        launchActivityB = findViewById(R.id.launchActivityB)
        launchActivityC = findViewById(R.id.launchActivityC)

        launchActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)
        }

        launchActivityC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            intent.putExtra("counter", 10)
            startActivity(intent)
        }
    }
}
