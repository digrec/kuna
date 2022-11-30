package com.digrec.kuna.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


/**
 * Created by Dejan Igrec
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KunaApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KunaApp()
}
