package com.luxuryvault.android.presentation.addeditluxury

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddEditLuxuryItemScreen() {
    // Implementation for adding/editing luxury items goes here

    Column(modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues())
        .padding(16.dp)
        , verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(text = "Add/Edit Luxury Item", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Category") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Subtitle / Tag") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = {},modifier = Modifier.fillMaxWidth()){Text("Select Image")}
        Spacer(modifier = Modifier.weight(6f))
        Button(onClick = {},modifier = Modifier.fillMaxWidth()){Text("Save")}

    }
}