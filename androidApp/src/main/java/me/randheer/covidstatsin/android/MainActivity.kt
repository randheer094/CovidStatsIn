package me.randheer.covidstatsin.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import me.randheer.covidstatsin.data.local.CovidLocalDataSource
import me.randheer.covidstatsin.data.mapper.ApiEntityMapper
import me.randheer.covidstatsin.data.remote.CovidRemoteDataSource
import me.randheer.covidstatsin.data.repo.CovidRepositoryImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "OOps"

        CovidRepositoryImpl(
            CovidRemoteDataSource(),
            CovidLocalDataSource(),
            ApiEntityMapper()
        ).test {
            tv.text = it.toString()
        }
    }
}
