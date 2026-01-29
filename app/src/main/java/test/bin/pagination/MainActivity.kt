package test.bin.pagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            App()
        }
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }
}