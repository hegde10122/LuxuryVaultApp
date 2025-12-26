package com.luxuryvault.android.presentation.luxurydetails


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent

@Composable
fun
        LuxuryItemDetailScreen(){

    val fakeItem = LuxuryItemDetailUiModel(
        id = "1",
        title = "Rolex Submariner",
        category = "Swiss Watch",
        imageUrl = "",
        subtitle = "Ultra Rare · Invite Only",
        description = "A legendary diver’s watch crafted with precision, " +
                "heritage, and unmatched exclusivity. Designed for those " +
                "who value timeless luxury."
    )

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(35.dp).padding(
            WindowInsets.systemBars.asPaddingValues()
        )
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "IMAGE",
                style = MaterialTheme.typography.titleMedium,
                color = Color.DarkGray
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = fakeItem.title,style= MaterialTheme.typography.headlineSmall)
        Text(text = fakeItem.category,style= MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))



        Text(
            text = fakeItem.subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = fakeItem.description,
            style = MaterialTheme.typography.bodyMedium
        )

    }
}