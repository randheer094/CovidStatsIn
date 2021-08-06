package me.randheer.covidstatsin.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import me.randheer.covidstatsin.android.ui.ApplicationContent
import me.randheer.covidstatsin.android.ui.theme.AppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ApplicationContent()
            }
        }
    }
}