package com.example.composewithhiltmvvmroom.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composewithhiltmvvmroom.R
import com.example.mvvm_hilt_db_retrofit_room.utils.BottomNavActions


@Composable
fun BottomNavigationWithHover(
    onAddButtonClicked: ()-> Unit,
    onBottomNavClicked: (String)-> Unit)
{
    Box(modifier = Modifier.fillMaxSize()) {
        // Navigation buttons at the bottom
        BottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
//                .padding(bottom = 16.dp)
            ,
            onBottomNavClicked,
        )

        // Hover button
        FloatingActionButton(
            onClick = { onAddButtonClicked() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(y = (-65).dp, x = (-12).dp), // Position it slightly above the bottom navigation
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add, // Replace with your icon
                contentDescription = "Hover Action"
            )
        }
    }
}

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, onBottomNavClicked: (String) -> Unit) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationButton("All", R.drawable.task_all) { onBottomNavClicked(BottomNavActions.ALL.toString()) }
        NavigationButton("Pending", R.drawable.task_pending) { onBottomNavClicked(BottomNavActions.PENDING.toString()) }
        NavigationButton("Completed", R.drawable.task_completed) { onBottomNavClicked(BottomNavActions.COMPLETED.toString()) }
    }
}

@Composable
fun NavigationButton(
    label: String,
//    icon: ImageVector,
    icon: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClick, modifier = Modifier.height(28.dp)) {
//            Icon(imageVector = icon, contentDescription = label)
            Icon(painter = painterResource(icon) , contentDescription = label)
        }
        Text(text = label, style = MaterialTheme.typography.bodySmall)
    }
}
