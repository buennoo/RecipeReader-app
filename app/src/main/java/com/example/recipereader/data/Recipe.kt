package com.example.recipereader.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: String,
    val title: String,
    val ingredients: List<String>,
    val steps: List<String>,
) : Parcelable