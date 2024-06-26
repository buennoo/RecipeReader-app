package com.example.recipereader.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Step(
    val id: String,
    val stepInfo: String,
) : Parcelable