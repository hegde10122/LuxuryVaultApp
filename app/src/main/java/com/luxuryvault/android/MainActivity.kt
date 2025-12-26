package com.luxuryvault.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.luxuryvault.android.presentation.addeditluxury.AddEditLuxuryItemScreen
import com.luxuryvault.android.presentation.imagesearch.ImageSearchScreen
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailScreen
import com.luxuryvault.android.presentation.navigation.AppNavGraph
import com.luxuryvault.android.ui.theme.LuxuryVaultAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                //AddEditLuxuryItemScreen
               // ImageSearchScreen
               // LuxuryItemDetailScreen()

                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}

