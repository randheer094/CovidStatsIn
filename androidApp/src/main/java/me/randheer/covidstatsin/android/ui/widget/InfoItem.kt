package me.randheer.covidstatsin.android.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InfoItem(
    title: String,
    value: String,
    titleColor: Color = Color.Black,
    valueColor: Color,
    modifier: Modifier
) {
    Column(modifier) {
        Text(text = title, color = titleColor, style = MaterialTheme.typography.body2)
        Text(text = value, color = valueColor, style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
fun InfoItemPreview() {
    InfoItem(
        title = "Title",
        value = "Value",
        titleColor = Color.Black,
        valueColor = Color.Green,
        modifier = Modifier.fillMaxWidth(0.5f)
    )
}