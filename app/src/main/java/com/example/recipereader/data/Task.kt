package com.example.recipereader
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//enum class IMPORTANCE {
//    LOW, NORMAL, HIGH
//}

@Parcelize
data class Task(
    val id: String,
    val title: String,
    val description: String,
//    val importance: IMPORTANCE = IMPORTANCE.NORMAL
) : Parcelable