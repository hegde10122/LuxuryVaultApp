package com.luxuryvault.android.presentation.luxurylist

/*
UI representation of a single luxury item shown in the list.

This model is strictly for the presentation layer.
It does NOT represent a database entity or domain model.

* Why this exists:
 * - UI requirements change frequently
 * - Domain models should remain stable
 * - Mapping will be done later by a ViewModel

* */
data class LuxuryItemUiModel(

    val id: String,
    val title: String,
    val category:String,
    val imageUrl:String,
    val subtitle:String
)

/**
 *
Purpose:

Exactly what UI needs
Nothing more
Nothing less

Used by:

Composables
UI state
ViewModel

Never used by:

Room
Retrofit
Domain use cases
 * */