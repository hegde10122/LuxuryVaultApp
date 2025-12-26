package com.luxuryvault.android.presentation.luxurylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LuxuryItemsListScreen(
    state: LuxuryItemListUiState,
    onItemClick: (LuxuryItemUiModel) -> Unit,
    onAddClick:() -> Unit
) {

Box(modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()))

    when {
        state.isLoading -> {
            LoadingView()
        }

        state.errorMessage != null -> {
            ErrorView(message = state.errorMessage)
        }

        else -> {
            LuxuryItemList(
                items = state.items,
                onItemClick = onItemClick
            )
        }
    }

}

//Loading
@Composable
fun LoadingView() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

//Error
@Composable
private fun ErrorView(message:String){

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
         Text(text = message,color= Color.Red)
    }
}

@Composable
private fun LuxuryItemList(
    items: List<LuxuryItemUiModel>,
    onItemClick: (LuxuryItemUiModel) -> Unit,
) {

    LazyColumn {
        items(items){
            item -> LuxuryItemRow(item = item,onClick = {onItemClick(item)})
        }
    }
}

@Composable
private fun LuxuryItemRow(
    item: LuxuryItemUiModel,
    onClick: () ->Unit
){

    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

        Box(modifier = Modifier.size(72.dp).background(Color.LightGray).clip(CircleShape))
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)){
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Text(text = item.category, style = MaterialTheme.typography.bodyMedium)
            Text(text = item.subtitle, style = MaterialTheme.typography.bodySmall)
        }

        //Trailing affordance
        Text(text=">",style= MaterialTheme.typography.titleLarge, color = Color.Gray)

    }

}