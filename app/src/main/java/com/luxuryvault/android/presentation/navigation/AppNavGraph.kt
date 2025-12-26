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


@Composable
fun AppNavGraph(navController: NavHostController){

    NavHost(navController = navController, startDestination = "list"){

        composable(route = "list") {
            LuxuryItemsListScreen(
                state = LuxuryItemListUiState(items = listOf(
                    LuxuryItemUiModel(
                        id = "1",
                        title = "Diamond Encrusted Watch",
                        category = "Watches",
                        subtitle = "Limited Edition",
                        imageUrl = ""
                    ),
                    LuxuryItemUiModel(
                        id = "2",
                        title = "Gold-Plated Supercar",
                        category = "Automobiles",
                        subtitle = "Custom Build",
                        imageUrl = ""
                    ),
                    LuxuryItemUiModel(
                        id = "3",
                        title = "Private Island",
                        category = "Real Estate",
                        subtitle = "Maldives",
                        imageUrl = ""
                    )
                )),
                onItemClick = {}, onAddClick = {}
            )

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