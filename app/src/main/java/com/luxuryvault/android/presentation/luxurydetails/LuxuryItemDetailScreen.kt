package com.luxuryvault.android.presentation.luxurydetails

import android.util.Log
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

@Composable
fun LuxuryItemDetailScreen(state: LuxuryItemDetailUiState, onBackClick: () -> Unit){

    when {

        state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text("Loading...", style = MaterialTheme.typography.bodyMedium)
                }
        }

        state.errorMessage != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text("Error: ${state.errorMessage}", style = MaterialTheme.typography.bodyMedium, color= Color.Red)
                }
        }

        state.item != null -> {

            val item = state.item

            Log.d("Seekho ",item.id);

            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp).padding(
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

                Text(text = item.title,style= MaterialTheme.typography.headlineSmall)
                Text(text = item.category,style= MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))



                Text(
                    text = item.subtitle,
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
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }
    }


}