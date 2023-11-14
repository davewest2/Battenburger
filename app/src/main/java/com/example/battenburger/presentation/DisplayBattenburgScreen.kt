package com.example.battenburger.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.battenburger.domain.DisplayBattenburgViewModel

@Composable
fun DisplayBattenburgScreen(navController: NavController){
    val viewModel = DisplayBattenburgViewModel()
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(100.dp))

        AsyncImage(
            model = viewModel.battenburg,
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(100.dp))

        Button(onClick = {

        })
        {
            Text(
                textAlign = TextAlign.Center,
                text = "Save it!")
        }
    }
}




