package com.example.composewithhiltmvvmroom.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composewithhiltmvvmroom.R

@Preview
@Composable
fun PreviewLayout(){
    ItemLayout(R.drawable.ic_launcher_background, "Title","Description")
}

@Composable
fun ItemLayout(imgId: Int, title: String, description: String) {
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp,
        pressedElevation = 8.dp,
        draggedElevation = 16.dp),
        modifier = Modifier.padding(8.dp))
    {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp).fillMaxWidth()) {
            Image(
                painter = painterResource(imgId),
                contentDescription = "",
                modifier = Modifier.size(48.dp).padding(4.dp)
            )
            Column {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium
                    )
                Text(
                    description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Thin
                )
            }
        }
    }
}