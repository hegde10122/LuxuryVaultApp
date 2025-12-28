package com.luxuryvault.android.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.luxuryvault.android.presentation.addeditluxury.AddEditLuxuryItemScreen
import com.luxuryvault.android.presentation.imagesearch.ImageSearchScreen
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailScreen
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemsListScreen
import androidx.navigation.compose.composable
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailRoute
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemListUiState
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemsListRoute


@Composable
fun AppNavGraph(navController: NavHostController){

    NavHost(navController = navController, startDestination = "list"){

        composable("list") {
            LuxuryItemsListRoute(onItemSelected = {
                item ->
                navController.navigate("detail/${item}")


            }, onAddClick = {})
        }


        composable("detail/{id}") {

                backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id") ?: return@composable

            Log.d("AppNavGraph", "Navigated to detail screen with itemId: $itemId")

            LuxuryItemDetailRoute(onBack = {navController.popBackStack()}, itemId = itemId)
        }

        composable("add") {
            AddEditLuxuryItemScreen()
        }

        composable("image_search") {
            ImageSearchScreen()
        }
    }

}

/**
What a Route is responsible for

A Route composable:
creates / obtains the ViewModel
collects StateFlow
maps VM events → UI callbacks
forwards pure state to the screen
 */