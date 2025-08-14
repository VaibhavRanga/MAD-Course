package com.hadiyarajesh.week2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hadiyarajesh.week2.day3_dependency_injection.DiViewModel
import com.hadiyarajesh.week2.ui.theme.MAD_S10Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    lateinit var usersText: TextView

    //    @Inject
//    lateinit var diViewModel: DiViewModel
    private val diViewModel: DiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersText = findViewById(R.id.users)
//        val viewModel = UserViewModel()

//        val viewModel = ViewModelProvider(this).get(UserViewModel::class)
//
//        lifecycleScope.launch {
//            viewModel.users.collect {
//                Log.d("TAG", "Users: $it")
//                usersText.text = it.toString()
//            }
//        }

        usersText.text = diViewModel.getData()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MAD_S10Theme {
        Greeting("Android")
    }
}
