package com.example.recipereader
import android.os.Parcelable
import com.example.recipereader.data.Steps
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: String,
    val title: String,
    val ingredients: String,
    val numOfIngredients: String,
    val numOfSteps: String,
    val steps: Steps = Steps()
) : Parcelable