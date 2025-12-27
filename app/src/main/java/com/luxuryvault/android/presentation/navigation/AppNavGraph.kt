package com.luxuryvault.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.luxuryvault.android.presentation.addeditluxury.AddEditLuxuryItemScreen
import com.luxuryvault.android.presentation.imagesearch.ImageSearchScreen
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailScreen
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemsListScreen
import androidx.navigation.compose.composable
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemListUiState
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemsListRoute


@Composable
fun AppNavGraph(navController: NavHostController){

    NavHost(navController = navController, startDestination = "list"){

        composable("list") {
            LuxuryItemsListRoute(onItemSelected = {}, onAddClick = {})
        }


        composable("detail") {
            LuxuryItemDetailScreen()
        }

        composable("add") {
            AddEditLuxuryItemScreen()
        }

        composable("image_search") {
            ImageSearchScreen()
        }
    }

}