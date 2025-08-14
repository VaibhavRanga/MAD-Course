package com.hadiyarajesh.week10

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.hadiyarajesh.week10.day3_memory_leak.MemoryLeakScreen
import com.hadiyarajesh.week10.day3_memory_leak.MemoryLeakViewModel
import com.hadiyarajesh.week10.day3_memory_leak.MemoryLeakViewModelFactory
import com.hadiyarajesh.week10.ui.theme.MAD_S10Theme
import com.hadiyarajesh.week10_library.LoggingUtility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var memoryLeakViewModel: MemoryLeakViewModel

    // Avoid using the static instance for context related objects
//    companion object {
//        lateinit var textView: TextView
//    }

    val runnable = Runnable {
        Log.d("TAG", "This runnable is running in ${this::class.simpleName}")
    }

//    val myWork1 = Runnable {
//        Log.d("TAG", "myWork1 Thread is : ${Thread.currentThread().name}")
//    }
//
//    val myWork2 = Runnable {
//        Log.d("TAG", "myWork2 Thread is : ${Thread.currentThread().name}")
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        memoryLeakViewModel =
            ViewModelProvider(
                this,
                MemoryLeakViewModelFactory(this)
            )[MemoryLeakViewModel::class.java]

        setContent {
            MAD_S10Theme {
                MemoryLeakScreen(memoryLeakViewModel)
            }
        }

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 20_000)

//        val mainThreadLooper = Looper.getMainLooper()
//        val handler = Handler(mainThreadLooper)
//        Thread.sleep(3_000)
//        handler.post { myWork1.run() }
//        handler.postDelayed(3_000) { myWork2.run() }

        Toast.makeText(this, this.packageName, Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "${BuildConfig.BASE_URL}", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        LoggingUtility.debugLog("onResume called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "Destroying the ${this::class.simpleName}")
    }
}

fun main() {
    println("Hello world")
    println("Thread is : ${Thread.currentThread().name}")
}
