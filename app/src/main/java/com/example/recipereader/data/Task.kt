package com.example.recipereader
import android.os.Parcelable
import com.example.recipereader.data.Step
import com.example.recipereader.data.Steps
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: String,
    val title: String,
    //val description: String,
    val steps: Steps
) : Parcelable