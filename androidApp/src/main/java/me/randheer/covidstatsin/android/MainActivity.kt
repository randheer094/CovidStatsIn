package me.randheer.covidstatsin.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.ExperimentalComposeUiApi
import me.randheer.covidstatsin.android.ui.ApplicationContent
import me.randheer.covidstatsin.android.ui.theme.AppTheme

class MainActivity : AppCompatActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ApplicationContent()
            }
        }
    }
}